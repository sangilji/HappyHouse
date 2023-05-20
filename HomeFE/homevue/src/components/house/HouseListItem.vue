<template>
  <div>
    <div class="col-6">
    <div class="text-center align-self-center">
      <div class="house-box" @click="goHouse">
      <img src="@/assets/house/house-img.png" alt="home" class="img-box">
      <h2>{{apartmentName}}</h2>
      <h3>{{ address }}</h3>
      <h4>🍎{{ housecomment }}🍎</h4>
      <div v-if="flag==true" style="width:50px;">
      <img src="@/assets/interests/full-heart.png" class="heart">
      </div>
    </div>
  </div>
</div></div>
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
    memberid: String,
    aptCode: String,
    apartmentName: String,
    address: String,
    housecomment:String,
    flag:Boolean,
  },
  methods: {
    // ...mapActions(houseStore, ["detailHouse"]),
    goHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      // this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
*{
  font-family: 'NanumGothic';
}
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
.house-box{
  width:450px;
  height: 600px;
  margin:1.2rem 1.5rem 1.2rem;
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
  h4{
    margin:7%;
    color:#9E9E9E;
  }
  h3{
    color:#9E9E9E;
  }
  .heart{
    /* margin:1.0rem 1.0rem 0; */
    width:43px;
    bottom:20px;
    position: absolute;
  }
</style>
