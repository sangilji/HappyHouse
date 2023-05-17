<template>
  <div>
    <house-header></house-header>
    <house-join></house-join>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import HouseJoin from "../../components/user/HouseJoin.vue";
export default {
  name: "LoginView",
  components: {
    HouseFooter,
    HouseHeader,
    HouseJoin,
  },
};
</script>

<style>
</style>