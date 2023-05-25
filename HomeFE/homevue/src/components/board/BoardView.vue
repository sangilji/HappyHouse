<template>
  <b-container class="bv-example-row mt-3">
    
    <b-row class="mb-1">
      <b-col class="text-left">
        <img src="@/assets/board/boardlist.png" alt="boardlist" class="board-button" @click="moveList">
      </b-col>
      <b-col class="text-right" v-if="userInfo&& userInfo.id == article.memberid">
        <img src="@/assets/board/boardmodify.png" alt="boardlist" class="board-button" @click="moveModifyArticle" style="margin:0 1.5rem;">
        <img src="@/assets/board/boarddelete.png" alt="boardlist" class="board-button" @click="deleteArticle">
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h1>${article.announcementid}.
          ${article.title} </h1>
          <div><h5>작성자: ${article.userId}</h5></div>
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
</template>

<script>
// import moment from "moment";
import { getArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
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
    let param = this.$route.params.announcementid;
    getArticle(
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
        name: "boardmodify",
        params: { announcementid: this.article.announcementid },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { announcementid: this.article.announcementid },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
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
