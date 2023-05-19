<template>
  <div>
    <house-header></house-header>
    <house-my-page></house-my-page>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import HouseMyPage from "@/components/user/HouseMyPage.vue";
export default {
  name: "LoginView",
  components: {
    HouseFooter,
    HouseHeader,
    HouseMyPage,
  },
};
</script>

<style>
</style>