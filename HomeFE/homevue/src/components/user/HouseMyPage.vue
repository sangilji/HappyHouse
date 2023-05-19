<template>
  <b-container class="bv-example-row mt-3" v-if="userInfo">
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-jumbotron id="login-box">
          <b-form class="text-left">
            <div class="font">마이 페이지</div>

            <hr class="my-3" />
            <b-container class="mt-3">
              <b-form-group
                label="아이디"
                label-for="userId"
                class="small-gray-font"
                ><b-form-input
                  id="userId"
                  required
                  v-model="user.userId"
                  disabled
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="이름"
                label-for="name"
                class="small-gray-font"
              >
                <b-form-input
                  id="name"
                  v-model="user.name"
                  required
                  :disabled="isModify"
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="이메일"
                label-for="email"
                class="small-gray-font"
              >
                <b-form-input
                  id="email"
                  v-model="user.email"
                  required
                  :disabled="isModify"
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="주소"
                label-for="address"
                class="small-gray-font"
              >
                <b-form-input
                  id="address"
                  v-model="user.address"
                  required
                  :disabled="isModify"
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="전화번호:"
                label-for="number"
                class="small-gray-font"
              >
                <b-form-input
                  id="number"
                  v-model="user.number"
                  required
                  :disabled="isModify"
                ></b-form-input>
              </b-form-group>
            </b-container>
            <hr class="my-4" />
            <b-row v-if="isModify">
              <b-button variant="primary" @click="modify()" class="mr-1 butt"
                >정보수정</b-button
              >
              <b-button variant="danger" @click="deleteMember()" class="butt mr-1"
                >회원탈퇴</b-button
              >
            </b-row>
            <b-row v-else>
              <b-button variant="primary" @click="cancel()" class="mr-1 butt"
                >되돌리기</b-button
              >
              <b-button variant="danger" @click="confirm()" class="butt mr-1"
                >수정하기</b-button
              >
            </b-row>
          </b-form>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  data() {
    return {
      user: null,
      isModify: true,
    };
  },
  created() {
    this.user = { ...this.userInfo };
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["modifyUser","getUserInfo"]),
    modify() {
      this.isModify = false;
    },
    cancel() {
      console.log(this.userInfo);
      this.user = { ...this.userInfo };
      console.log(this.user);
      this.isModify = true;
    },
    async confirm() {
      await this.modifyUser(this.user);
        await this.getUserInfo(sessionStorage.getItem("access-token"));
        this.user = { ...this.userInfo };
        alert("회원 정보 수정을 완료했습니다.");
        this.cancel();
      
    }
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
}
.font {
  font-size: 50px;
  text-align: center;
  font-weight: bolder;
  color: #231656;
}
.small-gray-font {
  font-size: 20px;
  color: #a8a9a9;
}
.butt {
  width: 190px;
  margin: 2rem 19.3rem 0rem;
}
</style>
