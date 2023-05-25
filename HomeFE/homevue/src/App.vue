<template>
	<div id="app">			
		<!-- {{ this.$store.state.country }} -->
		<router-view key="$route.path"></router-view>
    <!-- key="$route.path": 같은 경로일때 로드가 되지 않는 문제..절대 경로로 확인되는 경로를 다시 연결하여 노출시키는 방법이다. -->
	</div>
</template>

<script>
	export default {
    
	};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
