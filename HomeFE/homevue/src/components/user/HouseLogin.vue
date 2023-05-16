<template>
  <div class="container my-5">
    <b-card id="loginForm">
      <b-form-group
        label-cols-lg="12"
        label="간편하게 로그인하고 다양한 서비스를 이용하세요!"
        label-size="lg"
        label-align-sm="center"
        label-class="font-weight-bold pt-2"
        class="mb-0"
      >
        <b-form-group
          label="아이디"
          label-for="userid"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="userid" v-model="userid"></b-form-input>
        </b-form-group>
        <br />
        <b-form-group
          label="비밀번호"
          label-for="password"
          label-cols-sm="3"
          label-align-sm="right"
        >
          <b-form-input id="password" v-model="password"></b-form-input>
        </b-form-group>
        <br />
        <br />
        <br />
        <b-row class="justify-content-md-center">
          <b-col><p>아이디 찾기</p></b-col>
          <b-col><p>비밀번호 찾기</p></b-col>
          <b-col><p>회원가입</p></b-col>
          
        </b-row>
        <b-button>버튼</b-button>
      </b-form-group>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "HouseLogin",
  data() {
    return {
      userid: "",
      password: "",
      role: "",
    };
  },
};
</script>

<style scoped>
#loginForm {
  background: #e2ebed;
}
</style>