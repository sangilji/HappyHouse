<template>
  <div  class="about">
    <house-header></house-header>
    <house-main></house-main>
  </div>
</template>

<script>
// @ is an alias to /src
import HouseHeader from '@/components/HouseHeader.vue';
import HouseMain from "../components/HouseMain.vue";
export default {
  name: 'HouseMainView',
  components: {
    HouseHeader,
    HouseMain,
  }
}
</script>