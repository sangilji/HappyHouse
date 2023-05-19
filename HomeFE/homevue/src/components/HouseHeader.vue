<template>
	<div>
		<div>
			<b-navbar toggleable="lg" type="white">
    <b-navbar-brand @click="home" style="margin: 0rem 3.0rem 0rem;">HappyHouse</b-navbar-brand>

    <b-collapse id="nav-collapse" is-nav >
    <b-navbar-nav class="collapse navbar-collapse justify-content-end">
        <b-nav-item>
			<h2 class="map-box"  @click="map">지도</h2>
			<router-link to="map">
		</router-link>
		</b-nav-item>
        <b-nav-item>
			<router-link to="heart">
			<h2 class="heart-box" id="heart">찜</h2>
		</router-link>
		</b-nav-item>
        <b-nav-item>
			<router-link to="boardlist">
			<h2 class="board-box" id="board">공지사항</h2>
		</router-link>
		</b-nav-item>
        <b-nav-item>
			<router-link to="mypage">
			<h2 class="mypage-box" id="mypage">마이페이지</h2>
		</router-link>
		</b-nav-item>
	
		<!-- after login -->
		<div class="d-flex justify-content-center" v-if="userInfo">
        <b-nav-item>
				<h2>{{ userInfo.name }} 님</h2>
		</b-nav-item>
		<b-nav-item @click="onClickLogout">
			<img class="logout" alt="logout" src="../assets/logout.png"/>
		</b-nav-item>
		</div>
		
		<!-- before login -->
		<div class="d-flex justify-content-center" v-else>
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
</div>
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
import { mapState, mapActions,mapGetters } from "vuex";
// import http from "@/router/axios-common.js";
const memberStore = "memberStore";
export default {
	name: "HouseHeader",
	date() {
		return {};
	},
	computed: {
	...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
	},
	methods: {
		...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
    console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
    this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
    if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
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
			},
	
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
.logout{
	width:120px;
	margin:2.4rem 0;
}
.myname{
	width:120px;
}
</style>
