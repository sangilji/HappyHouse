<template>
	<div>
		<div>
<b-card class="board-box">
    <div class="text-dark">
    <h1>공지사항 </h1>
    <div style="float:right;">
	<router-link v-if="currentPath !== '/boardlist'" :to="{name:'boardlist'}">
			<img class="button" alt="goBoard" src="@/assets/board/add-read.png"/>
		</router-link>
    <router-link :to="{name:'boardwrite'}">
			<img class="button" alt="moveWrite" src="@/assets/board/write.png"/>
		</router-link>
    </div>
	<hr>
    
	<b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
		<colgroup>
        <col style="width: 10%" />
        <col style="width: 20%" />
        <col style="width: 40%" />
        </colgroup>
        <template #cell(title)="data">
            <router-link :to="{ name: 'boardview', params: { announcementid: data.item.announcementid } }">
            {{ data.item.title }}
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
        currentPath:'',
        articles: [],
        fields: [
        { key: "announcementid", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "memberid", label: "작성자", tdClass: "tdMemberId" },
    ],
    };
},
    created() {
        this.currentPath = this.$route.path;
    let param = {
    pg: 1,
    spp: 20,
    key: null,
    word: null,
    };
    listArticle(
    param,
        ({ data }) => {
            if (this.$route.path !== "/home") {
                this.articles = data;
            } else {
                this.articles = data.slice(0, 5);
            }
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
        params: { announcementid: article.announcementid },
    });
	},
    goBoard() {
        if (this.$route.path !== "/boardlist") this.$router.push({name: "boardlist"});
		
	},
},
};
</script>

<style scoped>
	.board-box{
		background-color: #FBFBFB;
		text-align:center; margin: 2.0rem 5.0rem 6.5rem;
	}
	h1{
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
    .button{
        width:150px;
        margin:2.0rem 1.0rem 2.0rem;
    }
</style>
