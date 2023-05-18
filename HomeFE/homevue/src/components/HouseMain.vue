<template>
<div>
<h1>어떤 집을 찾고 계세요?</h1>

    <div class="search">
    <i class="fas fa-search"></i>
    <input type="text">
    <i class="fas fa-keyboard"></i>
    <i class="fas fa-microphone"></i>
    <img src="../assets/main/search-button.png" alt="searchButton" class="search-button" @click="search">
</div>
    
<br>
<div class="row row-cols-1" style="text-align:center; margin: 2.0rem 5.0rem 6.5rem; ">
    <div class="col">
    <b-card-group>
    <b-list-group horizontal class="row" style="position: relative;
    left: 50%;
    transform: translateX(-50%);">
        <b-list-group-item @click="houseinfo" style="background-color: #E2EBED;" class="col-3">
        <div class="card">
            <img src="../assets/house/house-img.png">
            <hr>
            <div class="house-title">역삼 아이파크</div>
            <h3 class="house-price">전세 3억 1500</h3>
            <div class="house-address">서울시 강남구 역삼동</div>
            <div class="house-info">🍎전세자금대출 가능🍎주차 가능🍎전망과 채광좋음🍎</div>
        </div>
        </b-list-group-item>
        <b-list-group-item @click="houseinfo" style="background-color: #E2EBED;" class="col-3">
        <div class="card">
            <img src="../assets/house/house-img.png">
            <hr>
            <div class="house-title">역삼 아이파크</div>
            <h3 class="house-price">전세 3억 1500</h3>
            <div class="house-address">서울시 강남구 역삼동</div>
            <div class="house-info">🍎전세자금대출 가능🍎주차 가능🍎전망과 채광좋음🍎</div>
        </div>
        </b-list-group-item>
        <b-list-group-item @click="houseinfo" style="background-color: #E2EBED;" class="col-3">
        <div class="card" >
            <img src="../assets/house/house-img.png">
            <hr>
            <div class="house-title">역삼 아이파크</div>
            <h3 class="house-price">전세 3억 1500</h3>
            <div class="house-address">서울시 강남구 역삼동</div>
            <div class="house-info">🍎전세자금대출 가능🍎주차 가능🍎전망과 채광좋음🍎</div>
        </div>
        </b-list-group-item>
        <b-list-group-item @click="houseinfo" style="background-color: #E2EBED;" class="col-3">
        <div class="card">
            <img src="../assets/house/house-img.png">
            <hr>
            <div class="house-title">역삼 아이파크</div>
            <h3 class="house-price">전세 3억 1500</h3>
            <div class="house-address">서울시 강남구 역삼동</div>
            <div class="house-info">🍎전세자금대출 가능🍎주차 가능🍎전망과 채광좋음🍎</div>
        </div>
        
        </b-list-group-item>
    </b-list-group>
    </b-card-group>
    </div>

    </div></div>
</template>

<script>
// import http from "@/router/axios-common.js";
// import HouseListItem from "@/components/house/HouseListItem";

export default {
    name: "HouseList",
    components: {
        // HouseListItem,    
    },

    data() {
        return {
                
            }
        },
		methods: {
            search() {
                
            },
            houseinfo() {
                
            },
		},
	};
</script>

<style scoped>
.house-title{
        color: #231656;
		font-weight: bolder;
        font-size:22px;
}
.house-price{
        color: #000000;
		font-weight: bolder;
        margin: 1.0rem 0 1.5rem;
}
.house-address{
        color: #9E9E9E;
		font-weight: bolder;
        margin: 1.0rem 0 1.5rem;
}
.house-info{
        color: #9E9E9E;
		font-weight: bolder;
        margin: 1.0rem 0 1.5rem;
}
	.weegle {
    width: 400px;
    display: block;
    text-align: center;
    margin: auto;
}
h1 {
		color: #231656;
		font-weight: 900;
		margin: 2.5rem 0 1.5rem;
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
.search-button{
    width:8%;
    margin:0 1.0rem 0;
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
