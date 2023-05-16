<template>
<div>
<h1>어떤 집을 찾고 계세요?</h1>

    <div class="search">
    <i class="fas fa-search"></i>
    <input type="text">
    <i class="fas fa-keyboard"></i>
    <i class="fas fa-microphone"></i>
    </div>
    <input type="button" class="box" value="검색">
    </div>	
</template>

<script>
// import http from "@/router/axios-common.js";
	export default {
		methods: {
			
		},
	};
</script>

<style scoped>
	.weegle {
    width: 400px;
    display: block;
    text-align: center;
    margin: auto;
}

.search {
    position: relative;
    text-align: center;
    margin: 0 auto;
}
input {
    width: 60%;
    border-radius: 20px;
    border: 1px solid #bbb;
    margin: 10px 0;
    padding: 10px 12px;
}
.fa-search {
    position: absolute;
    left: 15px;
    top: 22px;
    margin: 0;
}
.fa-keyboard {
    position: absolute;
    right: 60px;
}

.fa-microphone {
    position: relative;
    right: 50px;
    margin: 0;
    color: blue;
}
.box {
    display: inline;
    width: auto;
    border-radius: 0;
    margin: auto;
    text-align: center;
    color: #bbb;
}
</style>
