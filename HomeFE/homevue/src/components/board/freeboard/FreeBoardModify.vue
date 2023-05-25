<template>
  <div style="width:100%">
  <free-board-write></free-board-write>
  </div>
  
</template>

<script>
// import BoardInputItem from "@/components/board/BoardInputItem";

import FreeBoardWrite from "@/components/board/freeboard/FreeBoardWrite.vue";

export default {

  name: "FreeBoardModify",
  data() {
    return {};
  },

  components: {
    FreeBoardWrite,
  },
};
</script>

<style>

  </style>
