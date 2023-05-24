<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <house-join></house-join>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import HouseJoin from "../../components/user/HouseJoin.vue";
import HouseBanner from "@/components/layout/HouseBanner.vue";

export default {
  name: "LoginView",
  components: {
    HouseFooter,
    HouseHeader,
    HouseJoin,
    HouseBanner,
  },
};
</script>

<style>
</style>