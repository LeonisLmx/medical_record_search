<template>
    <div class="login">
        <el-container>
            <el-header><h1>欢迎来到医疗记录系统</h1></el-header>
            <el-main>
                <el-form ref="form" :model="form" label-width="80px" :rules="rules" width="50%">
                    <el-form-item label="用户名:" prop="userName">
                        <el-input v-model="form.userName" placeholder="请输入用户名" class="input-text"/>
                    </el-form-item>
                    <el-form-item label="密码:" prop="password">
                        <el-input v-model="form.password" type="password" placeholder="请输入密码" class="input-text"/>
                    </el-form-item>
                </el-form>
                <el-button type="primary" @click="loginForm('form')">登录</el-button>
                <router-link :to="{path:'register'}"><el-button type="warning">注册</el-button></router-link>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import axios from 'axios'
export default {
  data () {
    return{
        form: {},
        rules: {
            userName: [
                { required: true, message: '请填写用户名', trigger: 'blur' },
                { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
            ],
            password: [
                { required: true, message: '请填写密码', trigger: 'blur' },
                { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
            ]
        }
    }
  },
  mounted () {
    console.log(sessionStorage.getItem('token'))
    if (sessionStorage.getItem('token') != null){
        this.$router.push('/medicalList')
    }
  },
  methods: {
    loginForm(form) {
        this.$refs[form].validate((valid) => {
        if (valid) {
            const url = this.host + '/medical/user/login'
            const dataForm = {}
            dataForm.userName = this.common.aesEncrypt(this.form.userName)
            dataForm.password = this.common.aesEncrypt(this.form.password)
            axios.post(url,dataForm).then(res => {
                if (res.data.code != 200){
                    this.$message.error(res.data.msg);
                }else{
                    sessionStorage.setItem('token', res.data.data)
                    sessionStorage.setItem('userName', this.form.userName)
                    this.$message({
                        message: '账号验证成功',
                        type: 'success'
                        });
                    this.$router.push('/medicalList')
                }
            })
        } else {
            return false;
        }
        });
    }
  }
}
</script>
<style scoped>
    .el-main {
        width: 40%;
        text-align: center;
        margin: 0 auto;
    }

    .login {
        height: 100%;
        padding-top: 10%;
    }
</style>