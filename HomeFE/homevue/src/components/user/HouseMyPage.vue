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
            <b-row v-if="isModify" class="d-flex justify-content-center">
              <img src="@/assets/login/before-modify-btn.png" style="margin:0 1.0rem 0" @click="modify()">
              
              <img src="@/assets/login/delete-user-btn.png" @click="deleteButton()">

            </b-row>
            <b-row v-else class="d-flex justify-content-center">
              <div >
              <img src="@/assets/login/reset-btn.png" style="margin:0 1.0rem 0" @click="cancel()">
              <img src="@/assets/login/modify-btn.png" @click="confirm()">
            </div>
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
    ...mapActions(memberStore, ["modifyUser", "getUserInfo", "deleteMember"]),
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
    },
    async deleteButton() {
      this.$swal("아이디를 삭제하시겠습니까?", {
        icon: "warning",
        buttons: {
          confirm: "삭제",
          cancel: "취소",
        },
      }).then((result) => {
        if (result != null) {
          this.deleteMember(this.userInfo.userId);

          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          if (this.$route.path !== "/") this.$router.push({ name: "main" });

          this.$swal("삭제되었습니다.", { icon: "success" });
        }
      });
     
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
