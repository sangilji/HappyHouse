<template>
	<div>
		<div>
			<b-navbar toggleable="lg" type="white">
    <b-navbar-brand @click="home" style="margin: 0rem 3.0rem 0rem;">HappyHouse</b-navbar-brand>

    <b-collapse id="nav-collapse" is-nav >
    <b-navbar-nav class="collapse navbar-collapse justify-content-end">
        <b-nav-item>
			<h2 class="map-box" id="map" @click="map">지도</h2>
		</b-nav-item>
        <b-nav-item>
			<h2 class="heart-box" id="heart" @click="heart">찜</h2>
		</b-nav-item>
        <b-nav-item>
			<h2 class="board-box" id="board" @click="board">공지사항</h2>
		</b-nav-item>
        <b-nav-item>
			<h2 class="mypage-box" id="mypage" @click="mypage">마이페이지</h2>
		</b-nav-item>
        <b-nav-item>
			<img class="signup" alt="signUp" src="../assets/signup-button.png" @click="signup"/>
		</b-nav-item>
		<b-nav-item >
			<img class="login" alt="login" src="../assets/login-button.png" @click="login"/>
		</b-nav-item>
    </b-navbar-nav>    
    </b-collapse>
</b-navbar>
</div>

		<div>
		</div>
		<div class="banner" @click="home">
			<img center src="../assets/header/Banner.png" class="img-fluid" alt="Responsive image">
		<!-- <img  alt="House logo" src="../assets/header/Banner.png" /> -->
		</div>
	</div>
	
	
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
	
	h1 {
		color: #231656;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
	}
	h2 {
		color: #231656;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
	}
	.left-box {
	position: absolute;
	top: 20px; left: 210px;
}

.signup{
	width:130px;
}
.login{
	width:120px;
}
</style>
