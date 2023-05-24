<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <house-login></house-login>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import HouseLogin from "../../components/user/HouseLogin.vue";
import HouseBanner from "@/components/layout/HouseBanner.vue";
export default {
  name: "LoginView",
  components: {
    HouseFooter,
    HouseHeader,
    HouseLogin,
    HouseBanner,
  },
};
</script>

<style>
</style>