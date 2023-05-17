<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h1>글 작성</h1>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
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
