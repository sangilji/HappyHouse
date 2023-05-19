<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col cols="11">
        <b-card class="text-center mt-3" id="login-box">
          <b-form class="text-left">
            <div class="font1">간편하게 회원가입하고</div>
            <div class="font2">다양한 서비스를 이용하세요!</div>

            <!-- <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert> -->
            <b-form-group
              label="아이디"
              label-for="userId"
              class="small-gray-font"
            >
              <b-form-input
                id="userId"
                v-model="user.userId"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="비밀번호"
              label-for="password"
              class="small-gray-font"
            >
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="name" class="small-gray-font">
              <b-form-input
                id="name"
                v-model="user.name"
                required
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
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <div class="d-flex justify-content-center" style="padding: 20px">
              <label
                class="small-gray-font"
                style="font-weight: bolder; margin-right: 80px"
                ><input
                  type="radio"
                  v-model="user.role"
                  name="authority"
                  value="ROLE_USER"
                />일반회원</label
              >
              <label class="small-gray-font" style="font-weight: bolder"
                ><input
                  type="radio"
                  name="authority"
                  v-model="user.role"
                  value="ROLE_LANLORD"
                />임대인</label
              >
            </div>
            <img
              class="join"
              alt="signUp"
              src="../../assets/login/signup.png"
              @click="confirm"
            />
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
import { mapState, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";
export default {
  name: "HouseJoin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: null,
        password: null,
        name: null,
        email: null,
        address: null,
        number: null,
        role: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isJoin", "isJoinError"]),
  },
  methods: {
    ...mapActions(memberStore, ["joinConfirm"]),
    ...mapMutations(memberStore, ["SET_IS_JOIN"]),
    async confirm() {
      await this.joinConfirm(this.user);
      // console.log("1. confirm() token >> " + token);
      if (this.isJoin) {
        alert("회원가입이 완료되었습니다.");
        this.SET_IS_JOIN(false);
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
#login-box {
  /* position: absolute; */
  background: #e2ebed;
  border-radius: 50px;
  margin: 4rem 0rem;
  padding: 80px;
  width: 1000px;
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
.join {
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
input[type="radio"] {
  width: 1.3rem;
  height: 1.3rem;
  border-radius: 50%;
  border: 1px solid #999;
  appearance: none;
  cursor: pointer;
  transition: background 0.2s;
  margin: 0 1rem;
}

input[type="radio"]:checked {
  background: #231656;
  border: none;
}
</style>