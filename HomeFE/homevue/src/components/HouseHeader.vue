<template>
	<header>
		<nav>
		<div>
		<h2 class="left-box" @click="home">Happy House</h2>
		<h1 class="map-box" id="map" @click="map">지도</h1>
		<h1 class="heart-box" id="heart" @click="heart">찜</h1>
		<h1 class="board-box" id="board" @click="board">공지사항</h1>
		<h1 class="mypage-box" id="mypage" @click="mypage">마이페이지</h1>
		<img class="signup" alt="signUp" src="../assets/signup-button.png" @click="signup"/>
		<img class="login" alt="login" src="../assets/login-button.png" @click="login"/>
		</div>
		<div class="banner" @click="home">
		<img  alt="House logo" src="../assets/header/Banner.png" />
		</div>
	</nav>
	</header>
	
</template>

<script>
// import http from "@/router/axios-common.js";
	export default {
		methods: {
			home() {		
				if (this.$route.path !== '/home') this.$router.push("/home");
			},
			map() {
				this.$router.push("/map");
			},
			heart() {
				this.$router.push("/heart");
			},
			board() {
				this.$router.push("/board");	
			},
			mypage() {
				this.$router.push("/mypage");
			},
			signup() {
				this.$router.push("/signup");
			},
			login() {
				this.$router.push("/login");
			}
		},
	};
</script>

<style scoped>
	.banner{
		top:20px;left: 10px;
		position: relative;
	}
	h1 {
		color: #231656;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
	}
	.left-box {
  position: absolute;
  top: 20px; left: 210px;
}
.map-box {
  position: absolute;
  top: 0; right: 900px;
  color: #231656;
}
.heart-box {
  position: absolute;
  top: 0; right: 830px;
  color: #231656;
}
.board-box {
  position: absolute;
  top: 0; right: 670px;
  color: #231656;
}
.mypage-box {
  position: absolute;
  top: 0; right: 490px;
  color: #231656;
}
.signup{
	width:160px;
	position: absolute;
	top: 40px; right: 270px;
}
.login{
	width:154px;
	position: absolute;
	top: 37px; right: 110px;
}
</style>
