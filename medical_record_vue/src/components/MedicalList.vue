<template>
    <div class="medical_list">
        <el-container>
                <el-form ref="form" :model="form" :inline="true" style="display: inline" label-width="80px">
                    <el-form-item label="姓名">
                        <el-input v-model="form.name"/>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-select placeholder="请选择" v-model="form.sex" clearable>
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="诊断结论">
                        <el-input v-model="form.description"/>
                    </el-form-item>
                    <el-form-item label="使用药品">
                        <el-input v-model="form.drug"/>
                    </el-form-item>
                </el-form>
        </el-container>
        <el-button type="primary" @click="searchList()">查询</el-button>
        <el-button type="primary" @click="toastNew()">新增</el-button>
        <el-container>
            <el-main>
                <el-table :data="tableData" style="width:100%">
                    <el-table-column prop="id" label="ID" width="180">
                    </el-table-column>
                    <el-table-column prop="name" label="姓名" width="180">
                    </el-table-column>
                    <el-table-column prop="sex" label="性别" width="180">
                        <template slot-scope="scope">
                            {{parseSex(scope.row.sex)}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="age" label="年龄" width="180">
                    </el-table-column>
                    <el-table-column prop="description" label="诊断结论" width="180">
                    </el-table-column>
                    <el-table-column prop="drug" label="使用药品" width="180">
                    </el-table-column>
                    <el-table-column prop="doctorName" label="医师" width="180">
                    </el-table-column>
                    <el-table-column prop="historyDescription" label="既往病史" width="180">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间" width="180">
                        <template slot-scope="scope">
                            {{goTime(scope.row.createTime)}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="updateTime" label="更新时间" width="180">
                        <template slot-scope="scope">
                            {{goTime(scope.row.createTime)}}
                        </template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="editRecord(scope.row)" type="text" size="small">编辑</el-button>
                            <el-button @click="deleteRecord(scope.row.id)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>

     <!-- 配置新弹窗 -->
    <el-dialog title="新增记录" :visible.sync="recordToast">
      <el-form ref="baseForm" :data="baseForm" style="width: 100%" :model="baseForm" :rules="rules">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="baseForm.name"/>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
          <el-select placeholder="请选择" v-model="baseForm.sex" style="float:left">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth" prop="age">
              <el-input type="number" v-model="baseForm.age" placeholder="请输入内容" oninput="value=value.replace(/[^0-9]/g,'')"/>
        </el-form-item>
        <el-form-item label="诊断结论" :label-width="formLabelWidth" prop="description">
          <el-input v-model="baseForm.description"/>
        </el-form-item>
        <el-form-item label="使用药品" :label-width="formLabelWidth" prop="drug">
          <el-input v-model="baseForm.drug"/>
        </el-form-item>
        <el-form-item label="医师" :label-width="formLabelWidth" prop="doctorName">
          <el-input v-model="baseForm.doctorName"/>
        </el-form-item>
        <el-form-item label="既往病史" :label-width="formLabelWidth" prop="historyDescription">
          <el-input v-model="baseForm.historyDescription"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" v-if="isNew === 1" @click="insertRecord('baseForm')">新增记录</el-button>
            <el-button type="primary" v-if="isNew === 0" @click="updateRecord('baseForm')">更改记录</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    </div>
</template>
<script>
import axios from 'axios'
import moment from 'moment'
export default {
  data () {
    return{
        options: [{
            "label": "男",
            "value": 1
        }, {
            "label": "女",
            "value": 0
        }],
        form: {},
        tableData: [],
        config: {
        },
        baseForm: {},
        recordToast: false,
        toastTitle: '',
        isNew: 0,
        formLabelWidth: '120px',
        rules: {
            name: [
                { required: true, message: '请填写用户名', trigger: 'blur' },
                { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
            ],
            sex: [
                { required: true, message: '请选择性别', trigger: 'blur' },
            ],
            age: [
                { required: true, message: '请输入年龄', trigger: 'blur' },
                {validator: (rule, value, callback) => {
                        if (value && (value < 1 || value > 200)) {
                            callback(new Error('年龄必须在1岁到200岁之间'));
                        } else {
                            callback();
                        }
                    }
                }
            ],
            description: [
                { required: true, message: '请输入诊断结论', trigger: 'blur' },
            ],
            drug: [
                { required: true, message: '请输入使用药品', trigger: 'blur' },
            ],
            doctorName: [
                { required: true, message: '请输入医师', trigger: 'blur' },
            ],
            historyDescription: [
                { required: true, message: '请输入既往病史', trigger: 'blur' },
            ],
        },
        dataForm: {}
    }
  },
  mounted () {
    console.log(sessionStorage.getItem('token'))
    if (sessionStorage.getItem('token') == null){
        this.$router.push('/login')
    }
     this.config.headers = {
        "Content-Type": "application/json",
        "accessToken": sessionStorage.getItem('token')
     }
  },
  methods: {
    goTime(val) {
        if (val == undefined) return '无记录'
        return moment(Number(val)).format('YYYY-MM-DD HH:mm:ss')
      },
    parseSex(data) {
        return data === 1?'男':'女'
    },
    toastNew() {
        this.recordToast = true
        this.toastTitle = '新增记录'
        this.isNew = 1
        this.baseForm = {}
    },
    editRecord(form) {
        this.recordToast = true
        this.toastTitle = '修改记录'
        this.isNew = 0
        this.baseForm = form
    },
    judgeNull(val) {
        if(val==""||val==null||val==undefined){
            return false
        }
        return true
    },
    searchList() {
        this.tableData = []
        let url = this.host + '/medical/record/search?'
        // if (this.form.name != undefined){
        //     url = url.concat('name=',this.common.aesEncrypt(this.form.name),'&')
        // }
        // if (this.form.sex != undefined) {
        //     url = url.concat('sex=',this.common.aesEncrypt(this.form.sex + ''),'&')
        // }
        // if (this.form.description != undefined) {
        //     url = url.concat('description=',this.common.aesEncrypt(this.form.description),'&')
        // }
        // if (this.form.drug != undefined) {
        //     url = url.concat('drug=',this.common.aesEncrypt(this.form.drug),'&')
        // }
        var data = {
            'name': this.judgeNull(this.form.name)?this.common.aesEncrypt(this.form.name):'',
            'sex': this.judgeNull(this.form.sex)?this.common.aesEncrypt(this.form.sex + ''):'',
            'description': this.judgeNull(this.form.description)?this.common.aesEncrypt(this.form.description):'',
            'drug': this.judgeNull(this.form.drug)?this.common.aesEncrypt(this.form.drug):''
        }
        url = url.substring(0, url.length - 1)
        axios.post(url, data, this.config).then(res => {
            if (res.data.code == 0){
                sessionStorage.removeItem('userName')
                sessionStorage.removeItem('token')
                this.$message({
                        message: '用户已过期，请重新登录',
                        type: 'info'
                        });
                this.$router.push('/login')
            }
            for(let i = 0;i < res.data.data.length;i++){
                let ans = res.data.data[i]
                console.log(ans)
                let data = {
                    'id': this.common.aesDecrypt(ans.id),
                    'name': this.common.aesDecrypt(ans.name),
                    'sex': this.common.aesDecrypt(ans.sex),
                    'age': this.common.aesDecrypt(ans.age),
                    'description': this.common.aesDecrypt(ans.description),
                    'drug': this.common.aesDecrypt(ans.drug),
                    'doctorName': this.common.aesDecrypt(ans.doctorName),
                    'historyDescription': this.common.aesDecrypt(ans.historyDescription),
                    'createTime': this.common.aesDecrypt(ans.createTime),
                    'updateTime': this.common.aesDecrypt(ans.updateTime)
                }
                this.tableData.push(data)
            }
        })
    },
    deleteRecord(id) {
         this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const url = this.host + '/medical/record/delete/?id=' + this.common.aesEncrypt(id) 
          axios.post(url, null, this.config).then(res => {
            if (res.data.code == 0){
                sessionStorage.removeItem('userName')
                sessionStorage.removeItem('token')
                this.$message({
                        message: '用户已过期，请重新登录',
                        type: 'info'
                        });
                this.$router.push('/login')
                return
            }
            this.$message({
                type: 'success',
                message: '删除成功!'
            });
            this.searchList()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    insertRecord(baseForm) {
        console.log(this.baseForm)
        this.$refs[baseForm].validate((valid) => {
        if (valid) {
            const url = this.host + '/medical/record/insert'
            this.dataForm.name = this.common.aesEncrypt(this.baseForm.name)
            this.dataForm.sex = this.common.aesEncrypt(this.baseForm.sex + "")
            this.dataForm.age = this.common.aesEncrypt(this.baseForm.age + "")
            this.dataForm.description = this.common.aesEncrypt(this.baseForm.description)
            this.dataForm.doctorName = this.common.aesEncrypt(this.baseForm.doctorName)
            this.dataForm.historyDescription = this.common.aesEncrypt(this.baseForm.historyDescription)
            this.dataForm.drug = this.common.aesEncrypt(this.baseForm.drug)
            axios.post(url,this.dataForm,this.config).then(res => {
                if (res.data.code == 0){
                    sessionStorage.removeItem('userName')
                    sessionStorage.removeItem('token')
                    this.$message({
                        message: '用户已过期，请重新登录',
                        type: 'info'
                        });
                    this.$router.push('/login')
                    return
                }else if(res.data.code == 200){
                    this.$message({
                        message: '新增记录成功',
                        type: 'success'
                        });
                    this.searchList()    
                }else{
                    this.$message.error(res.data.msg);
                }
            })
            this.recordToast = false
        } else {
            return false;
        }
        });
    },
    updateRecord(baseForm) {
        this.$refs[baseForm].validate((valid) => {
            console.log(this.baseForm)
        if (valid) {
            const url = this.host + '/medical/record/update?id=' + this.common.aesEncrypt(this.baseForm.id)
            this.dataForm.name = this.common.aesEncrypt(this.baseForm.name)
            this.dataForm.sex = this.common.aesEncrypt(this.baseForm.sex + "")
            this.dataForm.age = this.common.aesEncrypt(this.baseForm.age + "")
            this.dataForm.description = this.common.aesEncrypt(this.baseForm.description)
            this.dataForm.doctorName = this.common.aesEncrypt(this.baseForm.doctorName)
            this.dataForm.historyDescription = this.common.aesEncrypt(this.baseForm.historyDescription)
            this.dataForm.drug = this.common.aesEncrypt(this.baseForm.drug)
            axios.post(url,this.dataForm,this.config).then(res => {
                if (res.data.code == 0){
                    sessionStorage.removeItem('userName')
                    sessionStorage.removeItem('token')
                    this.$message({
                        message: '用户已过期，请重新登录',
                        type: 'info'
                        });
                    this.$router.push('/login')
                    return
                }else if(res.data.code == 200){
                    this.$message({
                        message: '修改记录成功',
                        type: 'success'
                        });
                    this.searchList()    
                }else{
                    this.$message.error(res.data.msg);
                }
            })
            this.recordToast = false
        } else {
            return false;
        }
        });
    }
  }
}
</script>