<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <div class="container my-5" style="border: 1px solid rgb(233, 233, 233); padding:1.0rem 1.0rem 1.0rem">
      <div class="row g-2">    
        <div class="col-lg-2 col-md-6"></div>
        
        
        <div id="searchBox" class="card">
        <div>
          <div class="p-2">
            <div class="d-flex">
              <i class="fa fa-search"></i>
              <h5 class="ps-2" style="font-weight:900;">부동산 이름 검색(아파트 코드 검색용)</h5>
            </div>
          </div>
          
        </div>
        <div class="input-group pb-2 px-3">
          <input
            @keyup.enter="onKeywordSearch"
            type="text"
            v-model="inputKeyword"
            class="form-control d-inline-block"
            placeholder="건물명 또는 동을 입력하세요"
          />
          <button
            @click="onKeywordSearch"
            class="btn d-inline-block"
            type="button" style="background-color:#231656;"
          >
            <i class="bi bi-search" style="color:white;"></i>
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
              
              <b-col style="margin: auto">
                <h4 class="m-0" v-if="currentIndex!=null && currentIndex>=0">
                  {{ houseList[currentIndex].apartmentName }}
                </h4>
              </b-col>

              <!-- <HeartBtn v-if="isAuth&&level==2" class="px-1" :enabled="houseList[curIndex].bookmark" @changeHeartBtn="onBookmarkHouse" /> -->
            </div>
            <!-- contents -->
            <div class="px-3">
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">주소</div>
                <div v-if="currentIndex!=null && currentIndex>=0">
                  {{ houseList[currentIndex].address }}
                  {{ houseList[currentIndex].dongName }}
                  {{ houseList[currentIndex].jibun }}
                </div>
              </div>
              <div class="d-flex py-2">
                <div class="text-secondary w-25">건축년도</div>
                <div v-if="currentIndex!=null && currentIndex>=0">
                  {{ houseList[currentIndex].buildYear }}년
                </div>
              </div>
            </div>
          </div>
          <!-- 실거래가 -->
          <div class="bg-white mb-2">
            <div class="border-bottom"><h5 class="p-3 m-0" >실거래가</h5></div>
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
            </div></div></div>
            <div>
          <div class="bg-white mb-2">
            <div class="border-bottom">
              <h3 class="p-3 m-0" style="font-weight: 900;">아파트 정보</h3>
              <img src="@/assets/close-btn.png" @click="closeList" style="margin:0 0 1.0rem;">
            </div>
            <div>
              <table class="w-100">
                <tbody class="px-2">
                  <tr
                    v-for="(item, index) in houseList"
                    :key="index"
                    class="border-bottom list-group-item-action"
                    @click="insertAptCode(index)"
                  >
                    
                    <td>
                      
                      <h5 style="font-weight: 900; color:#231656">
                        {{ item.apartmentName }}
                      </h5>
                      <div>
                        {{ item.address }}
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div></div>     
    

        <div class="col-lg-3 col-md-6" style="margin: 3.0rem 0 0; ">
          <div>
            <input stlye="" v-model="house.aptCode" type="text" class="small-input" placeholder="아파트코드" />
          </div>
        </div>
        <div class="col-lg-3 col-md-6" style="margin: 3.0rem 0 0;">
          <div class="form-floating">
            <input v-model="house.dealAmount" class="small-input" placeholder="실거래가" />
          </div>
        </div>
        
        <div class="col-lg-3 col-md-6" style="margin: 3.0rem 0 0;">
          <div class="form-floating">
            <input v-model="house.area" type="text" class="small-input" placeholder="면적(평)" />
          </div>
        </div>
        <div class="col-lg-3 col-md-6" style="margin: 3.0rem 0 0;">
          <div class="form-floating">
            <input v-model="house.buildYear" type="text" class="small-input" placeholder="거래 년도" />
          </div>
        </div>
        <div class="col-lg-3 col-md-6" style="margin: 3.0rem 0 0;">
          <div class="form-floating">
            <input v-model="house.floor" type="text" class="small-input" placeholder="층수" />
          </div>
        </div>
        <div class="col-12">
          <div class="my-3">
            <h5 class="form-label ms-3 mb-3" style="font-weight:900;">매물 설명 <sup class="text-danger">*</sup></h5>
            <input type="text" v-model="house.housecomment" class="big-input">
          </div>
        </div>

        <div class="col-12">
          <img src="@/assets/complete-btn.png" @click.prevent="ongoingInsert" style="margin:0 1.0rem 0;">          
          <router-link :to="{name:'home'}">
            <img src="@/assets/cancel-btn.png" style="margin:0 1.0rem 0;">
            </router-link>
        </div>
      </div>
      
    </div>
    <house-footer></house-footer>
  </div>
</template>

<script>
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import { writeHouseDeal } from "@/api/house";
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import { mapState, mapActions, mapGetters, mapMutations } from "vuex";
import HouseBanner from "../layout/HouseBanner.vue";

Vue.use(CKEditor);
const dealInfoStore = "dealInfoStore";

export default {
  name: "HouseCreate",
  components: {
    HouseHeader,
    HouseFooter,
    HouseBanner,
  },
  data() {
    return {
      selectSido: null,
      selectGuName: null,
      selectDongName: null,
      eventFrom: "",
      listVisible: false,
      dealInfo: [],
      inputKeyword: "",
      detail: false,
      increment: 0.5,
      reviewInsertModal: null,
      house: {
        aptCode: "",
        buildYear: "",
        dealAmount:"",
        area: "",
        floor: "",
        housecomment: "",
      },
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
  },
  watch: {
    currentIndex() {
      if (this.currentIndex == null) {
        return;
      }
      if (!this.detail) {
        this.detail = true;
      }
      this.getHouseDeal();
      this.getReview(this.houseList[this.currentIndex].aptCode);
    },
  },
  methods: {
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
      "SET_CURRENT_INDEX",
    ]),

    async onKeywordSearch() {
      if (this.inputKeyword == "") {
        this.$swal("키워드를 입력하세요.", { icon: "error" });
      } else {
        await this.getHouseListByKeyword(this.inputKeyword);

        if (this.houseList.length == 0) {
          this.listVisible = false;
          return;
        } else {
          this.detail = false;
        }
        if (!this.listVisible) {
          this.listVisible = true;
        }
      }
    },

    async getHouseDeal() {
      await this.getDealInfo();
    },
    closeList() {
      this.listVisible = false;
    },
    allButton() {
      this.detail = false;
    },
    insertAptCode(index) {
      this.house.aptCode = this.houseList[index].aptCode;
      this.listVisible = false;
    },
    ongoingInsert() {
      let param = {
        aptCode: this.house.aptCode,
        dealAmount: this.house.dealAmount,
        area: this.house.area,
        buildYear: this.house.buildYear,
        floor: this.house.floor,
        housecomment:this.house.housecomment,
      };
      writeHouseDeal(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          this.$swal(msg,{icon:'success'});
          this.$router.push({ name: "home" }).catch((error)=>console.log(error));
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },

  computed: {
    ...mapState(dealInfoStore, [
      "currentIndex",
      "sidos",
      "guguns",
      "dongs",
      "houseList",
      "fromMainKeyword",
      "houseDealInfo",
    ]),
  },
};
</script>

<style scope>
*{
  font-family: 'NanumGothic';
}

.big-input {
  width: 90%;
  height: 532px;
  font-size: 20px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
.small-input {
  width: 100%;
  height: 52px;
  font-size: 20px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
.houseImg {
  background-repeat: none;
  background-position: center;
  background-size: cover;
  width: 200px;
  height: 200px;
}
#wrapper {
  position: relative;
}
#searchBox {
  width: 500px;
  padding: 10px;
  z-index: 100;
  background-color: rgb(233, 233, 233);
  margin: 1.0rem 1.0rem 1.0rem;
  overflow-y: auto;
  border-color: rgb(233, 233, 233);;
}
#showList {
  position: absolute;

  top: 180px;
  bottom: 20px;
  left: 20px;

  width: 500px;
  padding: 10px;

  z-index: 100;
  background-color: rgba(0, 0, 0, 0.4);
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

.bi-plus-circle {
  font-size: 1.5rem;
}
.bi-plus-circle:hover {
  color: dodgerblue;
}
</style>
