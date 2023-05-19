<template>
  <div>
    <house-header></house-header>
    <b-container fluid>
      <b-row>
        <div class="col-3">
          <form action="searchByDong" method="post">
            <div class="input-group mb-2">
              <input
                class="form-control"
                type="text"
                name="dong"
                placeholder="동을 입력하세요"
              />
              <button
                class="btn btn-outline-secondary"
                id="button-newsletter"
                type="submit"
              >
                검색
              </button>
            </div>
          </form>
        </div>
        <div class="col-9">
          <select-sido @select-sido="selectSido"></select-sido>
          <select-gugun
            :sidoCode="sidoCode"
            @select-gugun="selectGugun"
          ></select-gugun>
        </div>
      </b-row>

      <div class="row">
        <div class="col-3">
        </div>
        <div class="col-9">
          <the-kakao-map :chargers="chargerList"></the-kakao-map>
        </div>
      </div>
    </b-container>
  </div>
</template>

<script>
import HouseHeader from "../../components/HouseHeader.vue";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";
import TheKakaoMap from "@/components/TheKakaoMap.vue";
export default {
  name: "AptMapview",
  components: {
    HouseHeader,
    SelectSido,
    SelectGugun,
    TheKakaoMap,
  },
  data() {
    return {
      sidoCode: null,
      chargerList: [],
    };
  },
  methods: {
    selectSido(sidoCode) {
      this.sidoCode = sidoCode;
    },
    selectGugun(gugunCode) {
      console.log("구군바꼈으니 충전소 찾으러 가자!!!", gugunCode);
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;

      const params = {
        pageNo: 1,
        numOfRows: 30,
        zscode: gugunCode,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      // if (gugunCode) params.zscode = gugunCode;
      // else params.zcode = this.sidoCode;

      //   electricChargerStationList(
      //     params,
      //     ({ data }) => {
      //       this.chargerList = data.items[0].item;
      //     },
      //     (error) => {
      //       console.log(error);
      //     }
      //   );
    },
  },
};
</script>

<style></style>
