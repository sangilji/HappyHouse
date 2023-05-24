<template>
  <div style="width:100%">
    <house-header></house-header>
  <board-write></board-write>
  <house-footer></house-footer>

  </div>
  
</template>

<script>
// import BoardInputItem from "@/components/board/BoardInputItem";
import HouseHeader from '@/components/HouseHeader.vue';
import HouseFooter from "@/components/layout/HouseFooter.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";

export default {

  name: "FreeBoardModify",
  data() {
    return {};
  },

  components: {
    HouseHeader,
    HouseFooter,
    BoardWrite,
  },
};
</script>

<style>

  </style>
