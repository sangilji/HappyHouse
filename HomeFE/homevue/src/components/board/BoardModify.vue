<template>
  <div>
  <house-header></house-header>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h1>글 수정</h1>
      </b-col>
    </b-row>
    <board-input-item type="modify" />
  </b-container>
  <house-footer></house-footer>
  </div>
</template>

<script>
import HouseHeader from '@/components/HouseHeader.vue';
import BoardInputItem from "@/components/board/BoardInputItem";
import HouseFooter from "@/components/layout/HouseFooter.vue";

export default {

  name: "BoardModify",
  data() {
    return {};
  },

  components: {
    HouseHeader,
    BoardInputItem,
    HouseFooter,
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
