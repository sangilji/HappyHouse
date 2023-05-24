<template>
    <div>
      <house-header></house-header>
      <house-banner></house-banner>
      <board-write></board-write>
      <house-footer></house-footer>
    </div>
    <!-- <house-footer></house-footer> -->
  </template>
  <script>
  import HouseHeader from '@/components/HouseHeader.vue';
  import BoardWrite from "@/components/board/BoardWrite.vue";
import HouseBanner from '@/components/layout/HouseBanner.vue';
  import HouseFooter from "@/components/layout/HouseFooter.vue";
  
export default {
    name: "BoardWriteView",
    components: {
      HouseHeader,
      BoardWrite,
      HouseFooter,
      HouseBanner
    }
  };
  </script>
  <style scoped>
 
  </style>
  