<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <find-password></find-password>
    <house-footer></house-footer>
  </div>
</template>

<script>
import HouseFooter from "../../components/layout/HouseFooter.vue";
import HouseHeader from "../../components/HouseHeader.vue";
import FindPassword from "@/components/user/findPassword.vue";
import HouseBanner from "@/components/layout/HouseBanner.vue";

export default {
  name: "HouseFindPassword",
  components: {
    HouseFooter,
    HouseHeader,
    FindPassword,
    HouseBanner,
  },
};
</script>

<style scoped>
</style>