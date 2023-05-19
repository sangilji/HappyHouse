<template>
	<div>
        <house-header></house-header>
        <div> 
            <b-container >
    <house-list-item></house-list-item>
  </b-container>
  <!-- <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container> -->


        </div>

        <house-footer></house-footer>

		</div>
</template>

<script>
// import { mapState, mapActions,mapGetters } from "vuex";
import HouseHeader from '@/components/HouseHeader.vue';
import HouseFooter from "@/components/layout/HouseFooter.vue";
import HouseListItem from "@/components/house/HouseListItem";

// const memberStore = "memberStore";
export default {
    name: "HeartView",
    components: {
    HouseHeader,
        HouseFooter,
        HouseListItem,
    }
}
</script>

<style scoped>
	
	
</style>
