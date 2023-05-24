<template>
<div>
  <div>
    <b-card class="board-box">
      <div class="text-dark">
        <h1>뉴스</h1>
        <b-table striped hover :items="newsList" :fields="fields">
          <template #cell(title)="data" >
            <div class="overflow-hidden">
            <a :href="data.item.url" >{{ data.item.title }}</a>
          </div>
          </template>
          <template #cell(time)="data">
            <div class="overflow-hidden">
            <p>{{ data.item.time }}</p>
          </div>
          </template>
        </b-table>
      </div>
    </b-card>
  </div>
</div>

  </template>
  
  <script>
  import { parsing } from "@/api/news";
  
  export default {
    name: "NewsItem",
    data() {
      return {
        newsList: [],
        fields: [
        { key: "title", label: "제목", tdClass: "tdClass" },
        { key: "time", label: "작성시간", tdClass: "tdSubject" },
        { key: "press", label: "신문사", tdClass: "tdMemberId" },
      ],
    };
    },
    mounted() {
      this.fetchNews();
    },
    methods: {
      async fetchNews() {
        try {
          const keyword = "부동산";
          const informations = await parsing(keyword);
          this.newsList = informations.slice(0,5);
        } catch (error) {
          console.error(error);
        }
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
