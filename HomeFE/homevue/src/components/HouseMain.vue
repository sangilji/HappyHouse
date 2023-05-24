<template>
  <div>
    <h1>어떤 집을 찾고 계세요?</h1>

    <div class="search">
      <i class="fas fa-search"></i>
      <input type="text" @keyup.enter="search" v-model="keyword" />
      <i class="fas fa-keyboard"></i>
      <i class="fas fa-microphone"></i>
      <img
        src="../assets/main/search-button.png"
        alt="searchButton"
        class="search-button"
        @click="search"
      />

      <h1>추천 매물</h1>
      <div class="d-flex justify-content-center" style="text-align: -webkit-center;">
        <div class="row">
          <house-list-item
            v-for="(house, index) in houseList"
            :key="index"
            v-bind="house"
            :index="index"
            :houseList="houseList"
            :isInterest="false"
          ></house-list-item>
        </div>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import HouseListItem from "@/components/house/HouseListItem";
import { getHouseList } from "@/api/house";
export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },

  data() {
    return {
      keyword: "",
      houseList: [],
      temp: [],
      fields: [
        { key: "apartmentName", label: "아파트이름", tdClass: "tdClass" },
        { key: "address", label: "아파트주소", tdClass: "tdAddress" },
        { key: "aptCode", label: "아파트코드", tdClass: "tdClass" },
        { key: "housecomment", label: "아파트설명", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    const tmp = [];
    getHouseList(
      ({ data }) => {
        for (let i = 0; i <= 5; i++) {
          let randomIdx = Math.floor(Math.random() * data.length);
          tmp.push(data[randomIdx]);
        }
        tmp.forEach((house) => {
          this.interestList.forEach((interest) => {
            if (house.aptCode == interest.aptCode) {
              house.flag = true;
              return;
            }
          });
        });
        this.houseList = tmp;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  async mounted() {},
  methods: {
    ...mapMutations("dealInfoStore", ["SET_KEYWORD"]),
    search() {
      if (this.keyword == "") {
        this.$swal("키워드를 입력하세요.", { icon: "warning" });
        return;
      }
      this.SET_KEYWORD(this.keyword);
      this.$router.push("/map").catch((error) => console.log(error));
    },
    houseinfo() {},
  },
  computed: {
    ...mapState("interestStore", ["interestList"]),
  },
};
</script>

<style scoped>
.house-title {
  color: #231656;
  font-weight: bolder;
  font-size: 22px;
}
.house-price {
  color: #000000;
  font-weight: bolder;
  margin: 1rem 0 1.5rem;
}
.house-address {
  color: #9e9e9e;
  font-weight: bolder;
  margin: 1rem 0 1.5rem;
}
.house-info {
  color: #9e9e9e;
  font-weight: bolder;
  margin: 1rem 0 1.5rem;
}
.weegle {
  width: 400px;
  display: block;
  text-align: center;
  margin: auto;
}
h1 {
  color: #231656;
  font-weight: 900;
  margin: 2.5rem 0 1.5rem;
}
.search {
  position: relative;
  text-align: center;
  margin: 0 auto;
}
input {
  width: 60%;
  border-radius: 20px;
  border: 1px solid #bbb;
  margin: 10px 0;
  padding: 10px 12px;
}
.fa-search {
  position: absolute;
  left: 15px;
  top: 22px;
  margin: 0;
}
.fa-keyboard {
  position: absolute;
  right: 60px;
}
.search-button {
  width: 8%;
  margin: 0 1rem 0;
}

.fa-microphone {
  position: relative;
  right: 50px;
  margin: 0;
  color: blue;
}
.box {
  display: inline;
  width: auto;
  border-radius: 0;
  margin: auto;
  text-align: center;
  color: #bbb;
}
</style>
