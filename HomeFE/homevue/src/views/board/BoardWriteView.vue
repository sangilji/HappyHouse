<template>
    <div>
      <house-header></house-header>
      <board-write></board-write>  
    </div>
    <!-- <house-footer></house-footer> -->
  </template>
  <script>
  import HouseHeader from '@/components/HouseHeader.vue';
  import BoardWrite from "@/components/board/BoardWrite.vue";
  
  // import HouseFooter from "@/components/layout/HouseFooter.vue";
  export default {
    name: "BoardWriteView",
    components: {
      HouseHeader,
      BoardWrite,
      // HouseFooter,
    }
  };
  </script>
  <style scoped>
 
  </style>
  