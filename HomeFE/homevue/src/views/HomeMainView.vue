<template>
  <div>
    <house-header></house-header>
    <house-main></house-main>
    <div class="container-fluid">
      <div class="d-flex flex-wrap justify-content-center">
        <div class="col-md-6">
          <!-- 첫 번째 컴포넌트 -->
          <board-main></board-main>
        </div>
        <div class="col-md-6">
          <!-- 두 번째 컴포넌트 -->
          <news-item></news-item>
        </div>
      </div>
    </div>
    <house-footer></house-footer>
  </div>
</template>

<script>
// @ is an alias to /src
import HouseHeader from "@/components/HouseHeader.vue";
import HouseMain from "../components/HouseMain.vue";
import BoardMain from "../components/board/BoardMain.vue";
import HouseFooter from "../components/layout/HouseFooter.vue";
import NewsItem from "@/components/News.vue";

export default {
  name: "HouseMainView",
  components: {
    HouseHeader,
    HouseMain,
    BoardMain,
    HouseFooter,
    NewsItem,
  },
};
</script>