<template>
  <div>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자" label-for="memberid" description="작성자를 입력하세요.">
          <b-form-input
            id="memberid"
            :disabled="true"
            v-model="userInfo.id"
            required
            :placeholder="userInfo.id.toString()"
            :value="userInfo.id"></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목" label-for="subject" description="제목을 입력하세요.">
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" style="" class="m-1" v-if="this.type === 'register'">글작성</b-button>
        <b-button type="submit" class="m-1" v-else>글수정</b-button>
        <b-button type="reset"  class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</div>
</template>

<script>
import { freeWriteArticle, freeModifyArticle, freeGetArticle } from "@/api/freeboard";
import { mapState, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
  name: "FreeBoardInputItem",
  data() {
    return {
      article: {
        boardid: 0,
        memberid: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  computed: {
	...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.boardid;
      freeGetArticle(
        param,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) this.$swal(msg,{icon:'success'});
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.boardid = 0;
      this.article.title = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      let param = {
        memberid: this.userInfo.id,
        title: this.article.title,
        content: this.article.content,
      };
      freeWriteArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          this.$swal(msg,{icon:'success'});
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        boardid: this.article.boardid,
        memberid: this.userInfo.id,
        title: this.article.title,
        content: this.article.content,
      };
      freeModifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          this.$swal(msg,{icon:'success'});
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "freeboardlist" });
    },
  },
};
</script>

<style></style>
