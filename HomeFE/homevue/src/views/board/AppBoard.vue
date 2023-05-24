<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <board-main></board-main>
    <house-footer></house-footer>
  </div>
  <!-- <house-footer></house-footer> -->
</template>
<script>
import HouseHeader from '@/components/HouseHeader.vue';
import BoardMain from "@/components/board/BoardMain.vue";
import HouseFooter from "@/components/layout/HouseFooter.vue";
import HouseBanner from '@/components/layout/HouseBanner.vue';
// import HouseFooter from "@/components/layout/HouseFooter.vue";
export default {
  name: "AppBoard",
  components: {
    HouseHeader,
    BoardMain,
    HouseFooter,
    // HouseFooter,
    HouseBanner,
  }
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
</style>
