<template>
  <div class="block-header py-3 border-top">
    <div class="container">
      <div>
        <div class="col">
          <div id="wrap">
            <footer>
              <nav style="margin: 1rem 1rem">
                <br />
                <a>이용약관</a> | <a>개인정보처리방침</a> |
                <a>사이트맵</a>
              </nav>
              <p>
                <span>고객센터 02-3429-5100 | 평일 상담시간 09:00 ~ 18:00</span
                ><br />
                <span
                  >본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재,
                  복사, 배포 등을 금합니다.</span
                ><br />
                <span>개발자 지상일 & 유다은</span><br />
                <span>Copyright &copy; HAPPYHOUSE All Rights Reserved.</span>
              </p>
            </footer>
          </div>
        </div>
        <div class="col text-end">
          <i class="bi bi-facebook" @click="shareFacebook"></i>&nbsp;&nbsp;
          <i class="bi bi-instagram" @click="shareOnInstagram"></i>
          <img
          id="kakao-link-btn"
            src="@/assets/kakao.png"
            style="width: 60px; margin: 0 0 0.7rem"
            class="bi"
            @click="sendkakao"
          />
          <i class="bi bi-twitter" @click="shareOnTwitter"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  head() {
    return {
      script: [{ src: "//developers.kakao.com/sdk/js/kakao.min.js" }],
    };
  },
  methods: {
    shareOnInstagram() {
      const url = "https://www.instagram.com/";

      window.open(url, "_blank");
    },
    shareOnTwitter() {
      const url = "https://twitter.com/intent/tweet";
      const text =
        "안전하게 나의 집을 찾는 방법! HAPPY HOUSE에서 쉽고 편하게 시작하세요!"; // 공유할 텍스트
      const sendUrl = "https://www.naver.com";
      const hashtags = "SSAFY_9기_FINAL_PROJECT_부동산"; // 해시태그
      const twitterUrl = `${url}?text=${encodeURIComponent(
        text
      )}&hashtags=${hashtags}&url=${sendUrl}`;

      window.open(twitterUrl, "_blank");
    },
    shareFacebook() {
      var sendUrl = "https://www.naver.com"; // 전달할 URL
      window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
    },
    sendkakao() {
      window.Kakao.Link.createScrapButton({
        container: "#kakao-link-btn",
        requestUrl: "http://localhost:8080/home",
        templateId: 65364,
      });
    },
  },
};
</script>

<style scoped>
.bi {
  font-size: 1.7rem;
}
.bi:hover {
  color: dodgerblue;
}
.container {
  font-size: 0.9rem;
  color: #808080;
}
footer {
  width: 100%;
  height: 90px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
footer a {
  display: inline-block;
  margin: 0 20px 10px 20px;
  color: #808080;
}
footer a:visited {
  color: #808080;
}
footer p {
  margin-top: 0;
  margin-bottom: 0;
}
footer p span {
  display: inline-block;
  margin-left: 20px;
}
</style>
