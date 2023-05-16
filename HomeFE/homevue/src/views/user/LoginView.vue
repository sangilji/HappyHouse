<template>
  <div>
    <house-header></house-header>
    <house-login></house-login>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import HouseLogin from "../../components/user/HouseLogin.vue";
export default {
  name: "LoginView",
  components: {
    HouseFooter,
    HouseHeader,
    HouseLogin,
  },
};
</script>

<style>
</style>