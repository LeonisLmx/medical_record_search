<template>
  <div id="app">
    <home-head v-if="userName !== null"></home-head>
    <router-view/>
  </div>
</template>
<script>
import HomeHead from '../src/components/vab/head'
export default {
  name: 'App',
  data () {
    return{
      userName: ''
    }
  },
  components: {
    HomeHead
  },
  mounted() {
    this.userName = sessionStorage.getItem('userName')
  },
  watch: {
    $route(to, from) {
      this.userName = sessionStorage.getItem('userName')
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
