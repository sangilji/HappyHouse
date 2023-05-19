<template>
  <div>
    <h1>내가 찜한 매물</h1>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <div class="house-box">
      <img src="@/assets/house/house-img.png" alt="home" class="img-box">
      <h2>강남 아이파크원</h2>
    </div>
    <!-- </b-col>
    <b-col cols="10" class="align-self-center">  --> 

    </b-col>
  </b-row>
</div>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    // ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
.house-box{
  width:450px;
  height: 600px;
  margin:0 1.5rem 0;
  background-color:#E2EBED;
}
.img-box{
  margin:1.0rem 1.0rem 0;
  width:400px;
  height: 250px;
}
h2{
    color: #231656;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
}
h1 {
		color: #231656;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
	}
</style>
