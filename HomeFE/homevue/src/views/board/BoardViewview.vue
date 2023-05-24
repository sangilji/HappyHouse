<template>
    <div>
    <house-header></house-header>
    <house-banner></house-banner>
    <board-view></board-view>
    <house-footer></house-footer>
    </div>
    <!-- <house-footer></house-footer> -->
</template>
<script>
import HouseHeader from '@/components/HouseHeader.vue';
import BoardView from "@/components/board/BoardView.vue";
import HouseFooter from "@/components/layout/HouseFooter.vue";
import HouseBanner from '@/components/layout/HouseBanner.vue';
export default {
    name: "BoardWriteView",
    components: {
    HouseHeader,
    BoardView,
    HouseFooter,
    HouseBanner,
    }
};
</script>
<style scoped>

</style>
