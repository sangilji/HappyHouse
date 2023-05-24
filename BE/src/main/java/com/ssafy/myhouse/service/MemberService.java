package com.ssafy.myhouse.service;

import com.ssafy.myhouse.mapper.MemberMapper;
import com.ssafy.myhouse.vo.LoginMemberDto;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.JoinMemberDto;
import com.ssafy.myhouse.vo.ModifyMemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final JavaMailSender emailSender;


    public boolean save(JoinMemberDto memberDto) throws Exception {
        if (duplicatedMember(memberDto)) {
            return false;
        }
        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .number(memberDto.getNumber())
                .role(memberDto.getRole())
                .build();

        memberMapper.join(member);
        return true;
    }

    private boolean duplicatedMember(JoinMemberDto joinMemberDto) {
        if (isExistsByUserId(joinMemberDto)) {
            return true;
        }
        if (isExistsByEmail(joinMemberDto)) {
            return true;
        }
        return false;
    }

    private boolean isExistsByUserId(JoinMemberDto joinMemberDto) {
        return memberMapper.existsByUserId(joinMemberDto.getUserId());
    }

    private boolean isExistsByEmail(JoinMemberDto joinMemberDto) {
        return memberMapper.existsByEmail(joinMemberDto.getEmail());
    }

    public int delete(String userId) throws Exception {
        return memberMapper.deleteByUserId(userId);
    }

    public Member findOne(String userId) {
        return memberMapper.findByUserId(userId);
    }

    public Member findById(int id) {
        return memberMapper.findById(id);
    }

    public int update(ModifyMemberDto memberDto) {

        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .number(memberDto.getNumber())
                .build();
        return memberMapper.updateMember(member);
    }

    public void saveRefreshToken(String userid, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", refreshToken);
        memberMapper.saveRefreshToken(map);
    }

    public Object getRefreshToken(String userid) throws Exception {
        return memberMapper.getRefreshToken(userid);
    }

    public void deleRefreshToken(String userid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", null);
        memberMapper.deleteRefreshToken(map);
    }

    public Member login(LoginMemberDto memberDto) {
        if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
            return null;
        return memberMapper.login(memberDto);
    }

    public String findPassword(Member member) {
        String epw = createKey();
        ModifyMemberDto memberDto = memberMapper.findPassword(member.getUserId());
        try {
            if (memberDto != null && memberDto.getEmail().equals(member.getEmail())) {
                // 랜덤 PW로 변경
                final String newPassword = epw;
                memberDto.setPassword(newPassword);
                Member newPasswordMember = Member.builder()
                        .userId(memberDto.getUserId())
                        .password(memberDto.getPassword())
                        .name(memberDto.getName())
                        .email(memberDto.getEmail())
                        .build();

                if (memberMapper.newPassword(newPasswordMember) == 1 && sendMessage(newPasswordMember)) {
                    return "success";
                } else {
                    return "fail";
                }
            } else {
                return "notFound";
            }
        } catch(Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

    public boolean sendMessage(Member memberDto) throws Exception{
        MimeMessage message = createMessage(memberDto);
        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            return false;
        }
        return true;
    }

    private MimeMessage createMessage(Member userDto) throws Exception{

        MimeMessage message = emailSender.createMimeMessage();
        final String subject = "Happy House 계정 패스워드 초기화 정보입니다.";
        String msg = "<div style='border: 1px solid black; padding: 10px; font-family: verdana;'>";
        msg += "<h2>안녕하세요. <span style='color: blue;'>" + userDto.getName() + "</span>님.</h2>";
        msg += "<p>초기화된 비밀번호를 전송해 드립니다. 비밀번호를 변경하여 사용하세요.</p>";
        msg += "<p>임시 비밀번호 : <span style='color: blue;'>" + userDto.getPassword() + "</span></p></div>";

        message.addRecipients(Message.RecipientType.TO,userDto.getEmail());
        message.setSubject(subject);
        message.setText(msg,"utf-8","html");
        message.setFrom(new InternetAddress("wltkddlf0515@gmail.com","happyhouse"));

        return message;
    }
}
