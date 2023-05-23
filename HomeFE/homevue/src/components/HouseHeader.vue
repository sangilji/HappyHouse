<template>
  <div>
    <div>
      <b-navbar toggleable="lg" type="white">
        <router-link :to="{ name: 'home' }">
          <b-navbar-brand style="margin: 0rem 3rem 0rem; font-weight: bolder">
            <img src="@/assets/header/logo.png" style="width: 150px" />
          </b-navbar-brand>
        </router-link>
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="collapse navbar-collapse justify-content-end">
            <b-nav-item>
              <router-link :to="{ name: 'map' }">
                <h2 class="heart-box">지도</h2>
              </router-link>
            </b-nav-item>
            <b-nav-item v-if="userInfo">
              <router-link :to="{ name: 'interests', params: { id: userInfo.id } }">
                <h2 class="heart-box" id="heart">찜</h2>
              </router-link>
            </b-nav-item>
            <b-nav-item v-else>
              <router-link :to="{ name: 'interests', params: { id: null } }">
                <h2 class="heart-box" id="heart">찜</h2>
              </router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link :to="{ name: 'boardlist' }">
                <h2 class="board-box" id="board">공지사항</h2>
              </router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link :to="{ name: 'mypage' }">
                <h2 class="mypage-box" id="mypage">마이페이지</h2>
              </router-link>
            </b-nav-item>
            <b-nav-item v-if="userInfo && userInfo.role == 'ROLE_LANLORD'">
              <router-link :to="{ name: 'createHouse' }">
                <h2 class="mypage-box" id="createHouse">매물 등록</h2>
              </router-link>
            </b-nav-item>

            <!-- after login -->
            <div class="d-flex justify-content-center" v-if="userInfo">
              <b-nav-item>
                <h2 style="margin:2.9rem 0 0">{{ userInfo.name }} 님</h2>
              </b-nav-item>
              <b-nav-item @click="onClickLogout">
                <img class="logout" alt="logout" src="../assets/logout.png" />
              </b-nav-item>
            </div>

            <!-- before login -->
            <div class="d-flex justify-content-center" v-else>
              <b-nav-item>
                <router-link :to="{ name: 'join' }">
                  <img
                    class="signup"
                    alt="signUp"
                    src="../assets/signup-button.png"
                    @click="signup"
                  />
                </router-link>
              </b-nav-item>
              <b-nav-item>
                <router-link :to="{ name: 'login' }">
                  <img class="login" alt="login" src="../assets/login-button.png" />
                </router-link>
              </b-nav-item>
            </div>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>

    <div></div>
    <div class="banner">
      <router-link :to="{ name: 'home' }">
        <img center src="../assets/header/Banner.png" class="img-fluid" alt="Responsive image" />
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters, mapMutations } from "vuex";
const memberStore = "memberStore";
const interestStore = "interestStore";
export default {
  name: "HouseHeader",
  date() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    ...mapMutations(interestStore,["SET_INTEREST_LIST"]),
    onClickLogout() {
      this.userLogout(this.userInfo.userid);
      this.SET_INTEREST_LIST(null);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path !== "/") this.$router.push({ name: "main" });
    },
    home() {
      if (this.$route.path !== "/home") this.$router.push({ name: "home" });
    },
    map() {
      if (this.$route.path !== "/map") this.$router.push({ name: "map" });
    },

    board() {
      if (this.$route.path !== "/board/list") this.$router.push({ name: "boardlist" });
    },
    mypage() {
      if (this.$route.path !== "/mypage") this.$router.push({ name: "mypage" });
    },
    signup() {
      if (this.$route.path !== "/join") this.$router.push({ name: "join" });
    },
    login() {
      if (this.$route.path !== "/login") this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
h1 {
  color: #231656;
  font-weight: 900;
  margin: 2.5rem 0 1.5rem;
}
h2 {
  color: #231656;
  font-weight: 900;
  margin: 2.5rem 0 1.5rem;
}
.left-box {
  position: absolute;
  top: 20px;
  left: 210px;
}

.signup {
  width: 130px;
}
.login {
  width: 120px;
}
.logout {
  width: 120px;
  margin: 2.4rem 0;
}
.myname {
  width: 120px;
}
</style>
