<template>
    <div class="register">
        <el-container>
            <el-header><h1>欢迎注册医疗记录系统</h1></el-header>
        <el-main>
            <el-form ref="form" :model="form" label-width="80px" :rules="rules">
                <el-form-item label="用户名:" prop="userName">
                    <el-input v-model="form.userName" placeholder="请输入用户名"/>
                </el-form-item>
                <el-form-item label="密码:" prop="password">
                    <el-input v-model="form.password" type="password" placeholder="请输入密码"/>
                </el-form-item>
            </el-form>
            <el-button type="warning" @click="registerForm('form')">注册</el-button>
            <router-link :to="{path:'login'}"><el-button type="primary">已有账号，去登录</el-button></router-link>
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
        },
        dataForm: {}
    }
  },
  mounted () {
    console.log(sessionStorage.getItem('token'))
    if (sessionStorage.getItem('token') != null){
        this.$router.push('/medicalList')
    }
  },
  methods: {
    registerForm(form) {
        this.$refs[form].validate((valid) => {
        if (valid) {
            const url = this.host + '/medical/user/register'
            this.dataForm.userName = this.common.aesEncrypt(this.form.userName)
            this.dataForm.password = this.common.aesEncrypt(this.form.password)
            axios.post(url,this.dataForm).then(res => {
                if (res.data.code != 200){
                    this.$message.error(res.data.msg);
                }else{
                    this.$message({
                        message: '账户注册成功',
                        type: 'success'
                        });
                    this.$router.push('/login')
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

    .register {
        height: 100%;
        padding-top: 10%;
    }
</style>