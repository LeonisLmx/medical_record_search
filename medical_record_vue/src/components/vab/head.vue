<template>
    <div class="head">
        <el-container class="layout-container">
            <h2 style="text-align:center">基于可搜索加密的医疗记录系统</h2>
            <el-header class="layout-header">
                <span>欢迎您,{{userName}}</span>
                <el-button class="layout-button" type="text" size="small" @click="logout()">退出登录</el-button>
            </el-header>
        </el-container>
    </div>
</template>
<script>
export default {
  data () {
    return{
        userName: sessionStorage.getItem('userName')
    }
  },
  mounted() {
    console.log(this.userName)
  },
  methods: {
    logout () {
        sessionStorage.removeItem('userName')
        sessionStorage.removeItem('token')
        this.$router.push('/login')
    }
  }
}
</script>
<style scoped>
    .layout-container {
        display: flex;
        border-bottom: 1px solid #dcdfe6;
        margin-bottom: 10px;
    }
    .layout-header{
        text-align: right;
        flex: 1;
        text-align: right;
        align-items: center;
    }
</style>
