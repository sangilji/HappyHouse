<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-card class="text-center" id="login-box">
          <b-form class="text-left">
            <div class="font2">비밀번호 찾기</div>

            <!-- <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert> -->
            <b-form-group
              label="아이디"
              label-for="userid"
              class="small-gray-font"
            >
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="이메일"
              label-for="email"
              class="small-gray-font"
            >
              <b-form-input
                type="text"
                id="email"
                v-model="user.email"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <div class="text-center">
              <span class="font-purple" @click="movePage">로그인</span>
              <span class="font-purple" @click="confirm">비밀번호 찾기</span>
              <div></div>
            </div>
            <!-- <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button> -->
            <!-- <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button> -->
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index";
const http = apiInstance();

export default {
  name: "FindPassword",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        email: null,
      },
    };
  },
  methods: {
    async confirm() {
      await http
        .post("user/findPassword", {
          userId: this.user.userid,
          email: this.user.email,
        })
        .then(() => {
          this.$swal(
            "비밀번호가 초기화되었습니다.",
            `${this.user.email}(으)로 임시비밀번호를 보냈습니다. 로그인 후 비밀번호를 변경해주세요.`,
            { icon: "success" }
          ).then(() => this.$router.push("/"));
        })
        .catch((error) => {
          console.log("FindPW: error : ");
          console.log(error);

          if (error.response.status == "404") {
            this.$swal(
              "요청하신 사용자 정보가 존재하지 않습니다. 정보를 다시 한 번 확인하세요.",
              { icon: "error" }
            );
          } else {
            this.$swal("서버에 문제가 발생하였습니다.", { icon: "error" });
          }
        });
    },
    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
#login-box {
  /* position: absolute; */
  background: #e2ebed;
  border-radius: 50px;
  margin: 4rem 0rem;
  padding: 80px;
  width: 100%;
}
.font1 {
  font-size: 50px;
  text-align: center;
  color: #231656;
}
.font2 {
  font-size: 50px;
  text-align: center;
  font-weight: bolder;
  color: #231656;
}
.login {
  width: 190px;
  margin: 2rem 19.3rem 0rem;
}
.small-gray-font {
  font-size: 20px;
  color: #a8a9a9;
}
.font-purple {
  color: #231656;
  font-weight: bolder;
  font-size: 20px;
  margin: 1rem 2rem 1rem;
}
</style>