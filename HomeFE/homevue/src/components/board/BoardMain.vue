<template>
	<div>
		<div>
<b-card class="board-box">
    <div class="text-dark">
    <h2>공지사항 </h2>
	<b-button style="background-color: #e2e2e2; color:black" @click="goBoard()">더보기</b-button>
	<b-button style="background-color: #e2e2e2; color:black; margin:10px;" @click="moveWrite()">글쓰기</b-button>
	<hr>
	<b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
		<colgroup>
        <col style="width: 10%" />
        <col style="width: 20%" />
        <col style="width: 40%" />
        </colgroup>
        <template #cell(subject)="data">
            <router-link :to="{ name: 'boardview', params: { subject: data.item.subject } }">
            {{ data.item.subject }}
            </router-link>
        </template>
        </b-table>
    </div>
</b-card>
</div>
	</div>	
</template>

<script>
// import http from "@/router/axios-common.js";
import { listArticle } from "@/api/board";
export default {
	name: "BoardList",
	data() {
    return {
    articles: [],
    fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
    ],
    };
},
created() {
    let param = {
    pg: 1,
    spp: 20,
    key: null,
    word: null,
    };
    listArticle(
    param,
    ({ data }) => {
        this.articles = data;
    },
    (error) => {
        console.log(error);
    }
    );
},
methods: {
    moveWrite() {
    this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
    this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
    });
	},
    goBoard() {
        if (this.$route.path !== "/board/list") this.$router.push({name: "boardlist"});
		
	},
},
};
</script>

<style scoped>
	.board-box{
		background-color: #FBFBFB;
		text-align:center; margin: 2.0rem 5.0rem 6.5rem;
	}
	h2{
		color: #4E4E4E;
		font-weight: 900;
		margin: 1.0rem 0 1.0rem;
	}
	.more-button{
		position: relative;
		text-align: center;
		font-size:25px;
		width:100px;
		height: 60px;
		background-color:#e2e2e2;
		font-weight: 600;
	}
</style>
