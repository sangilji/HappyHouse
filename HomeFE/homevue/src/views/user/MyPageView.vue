<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <house-my-page></house-my-page>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import HouseMyPage from "@/components/user/HouseMyPage.vue";
import HouseBanner from "@/components/layout/HouseBanner.vue";

export default {
  name: "LoginView",
  components: {
    HouseFooter,
    HouseHeader,
    HouseMyPage,
    HouseBanner,
  },
};
</script>

<style>
</style>