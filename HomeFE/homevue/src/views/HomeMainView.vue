<template>
  <div  class="about">
    <house-header></house-header>
    <house-main></house-main>
    <board-main></board-main>
    <house-footer></house-footer>
  </div>
</template>

<script>
// @ is an alias to /src
import HouseHeader from '@/components/HouseHeader.vue';
import HouseMain from "../components/HouseMain.vue";
import BoardMain from "../components/board/BoardMain.vue";
import HouseFooter from "../components/layout/HouseFooter.vue";
export default {
  name: 'HouseMainView',
  components: {
    HouseHeader,
    HouseMain,
    BoardMain,
    HouseFooter,
  }
}
</script>