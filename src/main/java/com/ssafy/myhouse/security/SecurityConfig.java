package com.ssafy.myhouse.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록 됨.
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder encodedPwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        CustomUsernamePasswordAuthenticationFilter authenticationFilter = getAuthenticationFilter(authenticationManager);
        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/manger/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .logout()	// 로그아웃 사용
                .logoutSuccessUrl("/")	//로그아웃 시 가지는 페이지
                .invalidateHttpSession(true)	// 세션 초기화
                .deleteCookies("JSESSIONID");
//                .usernameParameter("userId")
//                .loginProcessingUrl("/login")// login 주소가 호출이 되면 시큐리티가 로그인 진행해줌
//                .defaultSuccessUrl("/");


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        authFilter.setFilterProcessesUrl("/login");
        authFilter.setAuthenticationManager(authenticationManager);
        authFilter.setUsernameParameter("userId");
        authFilter.setPasswordParameter("password");
        return authFilter;
    }
}
