<template>
  <div>
  <house-header></house-header>
  <house-banner></house-banner>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h1>글 작성</h1>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
  <house-footer></house-footer>
  </div>
</template>

<script>
import BoardInputItem from "@/components/board/freeboard/FreeBoardInputItem";
import HouseHeader from "@/components/HouseHeader.vue";
import HouseBanner from "@/components/layout/HouseBanner.vue";
import HouseFooter from "@/components/layout/HouseFooter.vue";
export default {

  name: "FreeBoardWrite",
  data() {
    return {};
  },

  components: {
    BoardInputItem,
    HouseHeader,
    HouseFooter,
    HouseBanner
  },
};
</script>

<style>
h1{
		color: #231656;
		font-weight: 900;
		margin: 1.0rem 0 1.0rem;
	}
  </style>
