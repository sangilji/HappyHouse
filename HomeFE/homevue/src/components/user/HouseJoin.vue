<template>

<b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="11">
        <b-card class="text-center mt-3" id="login-box">
          <b-form class="text-left">
            <div class="font1">간편하게 회원가입하고</div>
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
            <b-form-group label="이름" label-for="username" class="small-gray-font">
              <b-form-input
                id="username"
                v-model="user.userpwd"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일" label-for="email" class="small-gray-font">
              <b-form-input
                id="email"
                v-model="user.email"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소" label-for="address" class="small-gray-font">
              <b-form-input
                id="address"
                v-model="user.address"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="number" class="small-gray-font">
              <b-form-input
                id="number"
                v-model="user.number"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <div class="d-flex justify-content-center" style="padding:20px;">
            <label class="small-gray-font" style="font-weight: bolder; margin-right:80px;"><input type="radio" name="authority" value="public">일반회원</label>
            <label class="small-gray-font" style="font-weight: bolder;"><input type="radio" name="authority" value="landlord" >임대인</label>
            </div>
			<img class="login" alt="signUp" src="../../assets/login/signup.png" @click="confirm"/>
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
        username: null,
        email: null,
        address: null,
        number: null,
        role: null,
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
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "login" });
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
input[type="radio"] {
    width: 1.3rem;
    height: 1.3rem;
    border-radius: 50%;
    border: 1px solid #999;
    appearance: none;
    cursor: pointer;
    transition: background 0.2s;
    margin: 0 1.0rem;
  }

  input[type="radio"]:checked {
    background: #231656;
    border: none;
  }
</style>