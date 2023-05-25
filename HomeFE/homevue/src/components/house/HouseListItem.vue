<template>
  <div>
    <div class="col-6">
      <div class="text-center align-self-center">
        <div class="house-box">
          <b-row class="text-center align-self-center" @click="goHouse">
            <b-col>
              <img
                src="@/assets/house/house-img.png"
                alt="home"
                class="img-box"
              />
              <h2>{{ apartmentName }}</h2>
              <h3>{{ address }}</h3>
              <h4>🍎{{ housecomment }}🍎</h4>
            </b-col>
          </b-row>
          <b-row class="text-center align-self-center">
            <b-col>
              <div v-if="userInfo">
                <heart-btn
                  v-if="isInterest"
                  :enabled="interestList[index]"
                  :index="index"
                  @changeHeartBtn="deleteHeart"
                ></heart-btn>
                <heart-btn
                  v-else
                  :enabled="houseList[index].flag"
                  :index="index"
                  @changeHeartBtn="deleteHeart"
                ></heart-btn>
              </div>
            </b-col>
          </b-row>
          <b-row @click="goHouse"
            sm="5"
            md="5"
            lg="5"
            class="text-center"
            align-v="center"
            style="height: 6%"
          >
            <b-col> </b-col>
          </b-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import HeartBtn from "../icon/HeartBtn.vue";
import { houseDeal } from "@/api/item/item";
const memberStore = "memberStore";
const interestStore = "interestStore";
export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  components: {
    HeartBtn,
  },
  created() {
  },
  props: {
    houseList: Array,
    memberid: String,
    aptCode: String,
    dongCode : String,
    apartmentName: String,
    address: String,
    housecomment: String,
    flag: Boolean,
    index: Number,
    isInterest: Boolean,
  },
  methods: {
    // ...mapActions(houseStore, ["detailHouse"]),
    ...mapActions(interestStore, ["addInterest", "getInterests"]),
    async goHouse() {
      await this.$emit('moveHouseDetail',{aptCode : this.aptCode, dongCode : this.dongCode})
    },
    async deleteHeart({ enabled, index }) {
      const params = {
        aptCode: this.aptCode,
        id: this.userInfo.id,
      };
      await this.addInterest(params);
    },
  },
  computed: {
    ...mapState(interestStore, ["interestList"]),
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
* {
  font-family: "NanumGothic";
}
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
.house-box {
  width: 450px;
  height: 600px;
  margin: 1.2rem 1.5rem 1.2rem;
  background-color: #e2ebed;
}
.img-box {
  margin: 1rem 1rem 0;
  width: 400px;
  height: 250px;
}
h2 {
  color: #231656;
  font-weight: 900;
  margin: 2.5rem 0 1.5rem;
}
h1 {
  color: #231656;
  font-weight: 900;
  margin: 2.5rem 0 1.5rem;
}
h4 {
  margin: 7%;
  color: #9e9e9e;
}
h3 {
  color: #9e9e9e;
}
.heart {
  /* margin:1.0rem 1.0rem 0; */
  width: 43px;
  bottom: 20px;
  position: absolute;
}
</style>
