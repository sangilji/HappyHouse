<template>
    <div>
    <house-header></house-header>
    <board-view></board-view>
    <house-footer></house-footer>
    </div>
    <!-- <house-footer></house-footer> -->
</template>
<script>
import HouseHeader from '@/components/HouseHeader.vue';
import BoardView from "@/components/board/BoardView.vue";
import HouseFooter from "@/components/layout/HouseFooter.vue";

export default {
    name: "BoardWriteView",
    components: {
    HouseHeader,
    BoardView,
    HouseFooter,
    }
};
</script>
<style scoped>

</style>
