<template>
	<div>
        <house-header></house-header>
        <h1>내가 찜한 매물</h1>
        <div class="d-flex justify-content-center">
          <div class="row">
        <house-list-item v-for="interest in interests" :key="interest.aptCode" v-bind="interest"></house-list-item>
      
        </div></div>
        <house-footer></house-footer>

		</div>
</template>

<script>
import { mapState } from "vuex";
import HouseHeader from '@/components/HouseHeader.vue';
import HouseFooter from "@/components/layout/HouseFooter.vue";
import HouseListItem from "@/components/house/HouseListItem";
import { getInterests } from "@/api/interests";

const memberStore = "memberStore";
export default {
    name: "HeartView",
    data(){
      return{
        interests:[],
        fields:[
        { key: "apartmentName", label: "아파트이름", tdClass: "tdClass" },
        { key: "address", label: "아파트주소", tdClass: "tdAddress" },
        { key: "aptCode", label: "아파트코드", tdClass: "tdClass" },
        { key: "housecomment", label: "아파트설명", tdClass: "tdClass" }
        ]
      };
    },
    computed:{
      ...mapState(memberStore, ["userInfo"]),
    // message() {
    //   if (this.article.content) return this.article.content.split("\n").join("<br>");
    //   return "";
    // },
  },
  created(){
    let param=this.$route.params.id;
    getInterests(
      param,
      ({data})=>{
        this.interests=data;
      },
      (error)=>{
        console.log(error);
      }
    );
  },
    components: {
    HouseHeader,
        HouseFooter,
        HouseListItem,
    }
}
</script>

<style scoped>
	
h1 {
		color: #494949;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
    text-align: center;
	}
	
</style>
