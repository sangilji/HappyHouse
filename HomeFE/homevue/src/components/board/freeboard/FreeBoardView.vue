<template>
  <div>
    <house-header></house-header>
    <house-banner></house-banner>
  <b-container class="bv-example-row mt-3">
    
    <b-row class="mb-1">
      <b-col class="text-left">
        <img src="@/assets/board/boardlist.png" alt="boardlist" class="board-button" @click="moveList">
      </b-col>
      <b-col class="text-right" v-if="userInfo.id == article.memberid">
        <img src="@/assets/board/boardmodify.png" alt="boardlist" class="board-button" @click="moveModifyArticle" style="margin:0 1.5rem;">
        <img src="@/assets/board/boarddelete.png" alt="boardlist" class="board-button" @click="deleteArticle">
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h1>${article.boardid}.
          ${article.title} </h1>
          <div><h5>작성자: ${article.memberid}</h5></div>
          <div><h5>작성시간: ${article.regtime}</h5></div>
          <div><h5>조회수: ${article.hit}</h5>`
          "
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message" style="font-size:25px; font-weight: 500;"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
  <house-footer></house-footer>

</div>
</template>

<script>
// import moment from "moment";
import { freeGetArticle } from "@/api/freeboard";
import { mapState } from "vuex";
import HouseHeader from '@/components/HouseHeader.vue';
import HouseFooter from "@/components/layout/HouseFooter.vue";
import HouseBanner from "@/components/layout/HouseBanner.vue";

const memberStore = "memberStore";

export default {
  name: "FreeBoardDetail",
  components: {
    HouseHeader,
    HouseFooter,
    HouseBanner,
  },
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.boardid;
    freeGetArticle(
      param,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "freeboardmodify",
        params: { boardid: this.article.boardid },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "freeboarddelete",
          params: { boardid: this.article.boardid },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "freeboardlist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style>
h1 {
		color: #231656;
		font-weight: 900;
		margin: 1.0rem 0 1.5rem;
	}
  h5 {
		color: #383838;
		font-weight: 800;
	}
</style>
