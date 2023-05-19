<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="11">
        <b-card class="text-center mt-3" id="login-box">
          <b-form class="text-left">
            <div class="font1">간편하게 로그인하고</div>
            <div class="font2">다양한 서비스를 이용하세요!</div>


            <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
            <b-form-group label="아이디" label-for="userid" class="small-gray-font">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="userpwd" class="small-gray-font">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <div class="d-flex justify-content-center">
              <div class="font-purple">아이디 찾기</div>
              <div class="font-purple">비밀번호 찾기</div>
              <div class="font-purple" @click="movePage">회원가입</div>
            </div>
			<img class="login" alt="signUp" src="../../assets/login/login.png" @click="confirm"/>
            <!-- <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button> -->
            <!-- <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button> -->
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
  name: "HouseLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
        role : null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scoped>

#login-box{
  /* position: absolute; */
background: #E2EBED;
border-radius: 50px;
margin: 4.0rem 0rem;
padding:80px;
width:1000px;
}
.font1{
  font-size:50px;
  text-align: center;
  color: #231656;
}
.font2{
  font-size:50px;
  text-align: center;
  font-weight: bolder;
  color: #231656;
}
.login{
  width:190px;
  margin: 2.0rem 19.3rem 0rem;
}
.small-gray-font{
  font-size:20px;
  color:#A8A9A9;
}
.font-purple{
  color:#231656;
  font-weight: bolder;
  font-size:20px;
  margin: 1.0rem 2.0rem 1.0rem;
}
</style>