<template>
  <div>
    <house-header></house-header>
    <find-password></find-password>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import FindPassword from "@/components/user/findPassword.vue";
export default {
  name: "HouseFindPassword",
  components: {
    HouseFooter,
    HouseHeader,
    FindPassword,
  },
};
</script>

<style scoped>
</style>