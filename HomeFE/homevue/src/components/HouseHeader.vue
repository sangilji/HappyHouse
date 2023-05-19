<template>
	<div>
		<div>
			<b-navbar toggleable="lg" type="white">
    <b-navbar-brand @click="home" style="margin: 0rem 3.0rem 0rem;">HappyHouse</b-navbar-brand>

    <b-collapse id="nav-collapse" is-nav >
    <b-navbar-nav class="collapse navbar-collapse justify-content-end">
        <b-nav-item>
<<<<<<< HEAD
			<h2 class="map-box"  @click="map">지도</h2>
=======
			<router-link to="map">
			<h2 class="map-box" >지도</h2>
		</router-link>
>>>>>>> 44914525a4f76b0c86e63f5a0a33a6ea8cfbffbf
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
			<router-link to="join">
			<img class="signup" alt="signUp" src="../assets/signup-button.png" @click="signup"/>
		</router-link>
		</b-nav-item>
		<b-nav-item >
			<router-link to="login">
			<img class="login" alt="login" src="../assets/login-button.png" />
		</router-link>
		</b-nav-item>
    </b-navbar-nav>    
    </b-collapse>
</b-navbar>
</div>

		<div>
		</div>
		<div class="banner">
			<router-link to="home">
			<img center src="../assets/header/Banner.png" class="img-fluid" alt="Responsive image">
		<!-- <img  alt="House logo" src="../assets/header/Banner.png" /> -->
			</router-link>	
	</div>
	</div>
	
	
</template>

<script>
import { mapState, mapActions } from "vuex";
// import http from "@/router/axios-common.js";
const memberStore = "memberStore";
	export default {
		methods: {
			home() {		
				if (this.$route.path !== "/home") this.$router.push({name:"home"});
			},
			map() {
				if(this.$route.path!=="/map") this.$router.push({name:"map"});
			},
			heart() {
				if(this.$route.path!=="/heart") this.$router.push({name:"heart"});
			},
			board() {
				if(this.$route.path!=="/board/list") this.$router.push({name:"boardlist"});	
			},
			mypage() {
				if(this.$route.path!=="/mypage") this.$router.push({name:"mypage"});
			},
			signup() {
				if(this.$route.path!=="/join") this.$router.push({name:"join"});
			},
			login() {
				if(this.$route.path!=="/login") this.$router.push({name:"login"});
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
