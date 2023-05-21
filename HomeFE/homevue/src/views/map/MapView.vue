<template>
  <div>
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
                <router-link
                  :to="{ name: 'interests', params: { id: userInfo.id } }"
                >
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

              <!-- after login -->
              <div class="d-flex justify-content-center" v-if="userInfo">
                <b-nav-item>
                  <h2>{{ userInfo.name }} 님</h2>
                </b-nav-item>
                <b-nav-item @click="onClickLogout">
                  <img class="logout" alt="logout" src="@/assets/logout.png" />
                </b-nav-item>
              </div>

              <!-- before login -->
              <div class="d-flex justify-content-center" v-else>
                <b-nav-item>
                  <router-link :to="{ name: 'join' }">
                    <img
                      class="signup"
                      alt="signUp"
                      src="@/assets/signup-button.png"
                    />
                  </router-link>
                </b-nav-item>
                <b-nav-item>
                  <router-link :to="{ name: 'login' }">
                    <img
                      class="login"
                      alt="login"
                      src="@/assets/login-button.png"
                    />
                  </router-link>
                </b-nav-item>
              </div>
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </div>
    </div>
    <div id="wrapper">
      <the-kakao-map></the-kakao-map>
      <div id="searchBox" class="card">
        <div>
          <div class="p-2">
            <div class="d-flex">
              <i class="fa fa-search"></i>
              <h6 class="ps-2">검색 방법을 선택하세요</h6>
            </div>
            <div class="py-1 px-2 d-flex">
              <div class="form-check pe-3">
                <input
                  value="D"
                  v-model="searchType"
                  class="form-check-input"
                  type="radio"
                  id="searchByDong"
                />
                <label class="form-check-label" for="searchByDong"
                  >동 검색</label
                >
              </div>
              <div class="form-check">
                <input
                  value="K"
                  v-model="searchType"
                  class="form-check-input"
                  type="radio"
                  id="searchByKeyword"
                />
                <label class="form-check-label" for="searchByKeyword"
                  >키워드 검색</label
                >
              </div>
            </div>
          </div>
          <div
            v-if="searchType == 'D'"
            class="pb-2 d-flex justify-content-evenly"
          >
            <div class="col-lg-4 col-md-6 col-sm-6">
              <b-form-select
                v-model="selectSido"
                @change="changeSido"
                :options="sidos"
              ></b-form-select>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <b-form-select
                v-model="selectGuName"
                @change="onGuMenuChange"
                :options="guguns"
              ></b-form-select>
            </div>

            <div class="col-lg-4 col-md-6 col-sm-6">
              <b-form-select
                v-model="selectDongName"
                @change="onDongMenuChange"
                :options="dongs"
              ></b-form-select>
            </div>
          </div>
        </div>
        <div v-if="searchType == 'K'" class="input-group pb-2 px-3">
          <input
            @keyup.enter="onKeywordSearch"
            type="text"
            v-model="inputKeyword"
            class="form-control d-inline-block"
            placeholder="건물명 또는 동을 입력하세요"
          />
          <button
            @click="onKeywordSearch"
            class="btn btn-primary d-inline-block"
            type="button"
          >
            <i class="bi bi-search"></i>
          </button>
        </div>
      </div>
      <div v-if="listVisible" id="showList" class="card p-0 bg-secondary">
        <div v-if="detail">
          <div class="bg-white mb-2">
            <div
              class="text-center text-white py-2"
              style="background-color: #b0c4de"
              @click="allButton()"
            >
              <h6>전체보기</h6>
            </div>
          </div>
          <!-- 아파트 정보 요약 -->
          <div class="bg-white mb-2">
            <div
              class="p-3 border-bottom d-flex justify-content-between align-items-center"
            >
              <h4 class="m-0">{{ houseList[currentIndex].apartmentName }}</h4>
              <!-- <HeartBtn v-if="isAuth&&level==2" class="px-1" :enabled="houseList[curIndex].bookmark" @changeHeartBtn="onBookmarkHouse" /> -->
            </div>
            <!-- contents -->
            <div class="px-3">
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">주소</div>
                <div>
                  {{ houseList[currentIndex].dongName }}
                  {{ houseList[currentIndex].jibun }}
                </div>
              </div>
              <div class="d-flex py-2">
                <div class="text-secondary w-25">건축년도</div>
                <div>{{ houseList[currentIndex].buildYear }}</div>
              </div>
            </div>
          </div>
          <!-- 실거래가 -->
          <div class="bg-white mb-2">
            <div class="border-bottom"><h5 class="p-3 m-0">실거래가</h5></div>
            <div>
              <table class="w-100">
                <thead class="bg-secondary text-white">
                  <tr>
                    <td class="ps-3 py-1">거래일</td>
                    <td>거래가격</td>
                    <td>면적</td>
                    <td>층수</td>
                  </tr>
                </thead>
                <tbody class="px-2">
                  <tr
                    v-for="(item, index) in houseDealInfo"
                    :key="index"
                    class="border-bottom"
                  >
                    <td class="ps-3 py-2">{{ item.dealYear }}</td>
                    <td>{{ item.dealAmount }}</td>
                    <td>{{ item.area }}</td>
                    <td>{{ item.floor }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- 매물정보 -->
        </div>
        <div v-else>
          <div class="bg-white mb-2">
            <div class="border-bottom"><h5 class="p-3 m-0">아파트 정보</h5></div>
            <div>
              <table class="w-100">
                <thead class="bg-secondary text-white">
                  <tr>
                    <td class="ps-3 py-1">아파트</td>
                    <td>건축년도</td>
                    <td>주소</td>
                  </tr>
                </thead>
                <tbody class="px-2">
                  <tr
                    v-for="(item, index) in houseList"
                    :key="index"
                    class="border-bottom"
                    @click="detailButton(index)"
                  >
                    <td class="ps-3 py-2">{{ item.apartmentName }}</td>
                    <td>{{ item.buildYear }}</td>
                    <td>{{ item.dongName }} {{ item.jibun }}</td>
                    
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TheKakaoMap from "@/components/TheKakaoMap.vue";
import { mapState, mapActions, mapGetters, mapMutations } from "vuex";
const memberStore = "memberStore";
const dealInfoStore = "dealInfoStore";

export default {
  name: "AptMapview",
  components: {
    TheKakaoMap,
  },
  data() {
    return {
      searchType: "D",
      selectSido: null,
      selectGuName: null,
      selectDongName: null,
      eventFrom: "",
      listVisible: false,
      dealInfo: [],
      inputKeyword: "",
      detail: false,
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
    console.log(this.sidos);
  },
  watch: {
    currentIndex() {
      if (!this.detail) {
        this.detail = true;
      }
      this.getHouseDeal();
    },
    searchType(val) {
      console.log("val=" + val);
      if (val === "D") {
        this.CLEAR_SIDO_LIST();
        this.CLEAR_GUGUN_LIST();
        this.CLEAR_DONG_LIST();
      } else if (val === "K") {
        console.log("watch K");
      }
    },
  },

  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    ...mapActions(dealInfoStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseListByDong",
      "getHouseListByKeyword",
      "getDealInfo",
    ]),
    ...mapMutations(dealInfoStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_CURRENT_INDEX"
    ]),
    allButton(){
      this.detail = false;
    },
    detailButton(index){
      this.detail = true;
      this.SET_CURRENT_INDEX(index);
    },
    onClickLogout() {
      console.log(this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path !== "/") this.$router.push({ name: "main" });
    },
    async getHouseDeal() {
      await this.getDealInfo();
    },
    changeSido() {
      this.CLEAR_GUGUN_LIST();
      this.selectGuName = null;
      this.CLEAR_DONG_LIST();
      this.selectDongName = null;
      if (this.selectSido) {
        this.getGugun(this.selectSido);
      }
    },
    onGuMenuChange() {
      this.CLEAR_DONG_LIST();
      if (this.selectGuName !== null) {
        this.selectDongName = null;
        this.getDong({
          sidoCode: this.selectSido,
          gugunCode: this.selectGuName,
        });
      }
    },
    async onDongMenuChange() {
      if (this.selectDongName !== null) {
        this.eventFrom = "dong";
        await this.getHouseListByDong(
          this.selectSido + this.selectGuName + this.selectDongName
        );
      }
      console.log(this.houseList.length);
      if (this.houseList.length!=0 &&!this.listVisible) {
        this.listVisible = true;
      }
      this.$swal(`${this.houseList.length}건 검색 완료`, { icon: "success" });
    },
    async onKeywordSearch() {
      if (this.inputKeyword == "") {
        this.$swal("키워드를 입력하세요.", { icon: "error" });
      } else {
        await this.getHouseListByKeyword(this.inputKeyword);
      }
    },
    initSearchByDongBox() {
      this.getGugun(11);
    },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(dealInfoStore, [
      "currentIndex",
      "sidos",
      "guguns",
      "dongs",
      "houseList",
      "fromMainKeyword",
      "houseDealInfo",
    ]),
    ...mapGetters(["checkUserInfo"]),
  },
};
</script>

<style scoped>
#wrapper {
  position: relative;
}
#searchBox {
  position: absolute;

  top: 20px;
  /* bottom : 10px; */
  left: 20px;

  width: 500px;
  /* height: 400px; */
  padding: 10px;

  z-index: 100;
  /* background-color:rgba(255, 244, 244, 0.8); */
  /* opacity: 0.5; */
  background-color: rgba(255, 255, 255, 0.7);

  overflow-y: auto;
}
#showList {
  position: absolute;

  top: 180px;
  bottom: 20px;
  left: 20px;

  width: 500px;
  padding: 10px;

  z-index: 100;
  /* background-color:rgba(255, 244, 244, 0.8); */
  /* opacity: 0.5; */

  overflow-y: auto;
}
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
