<template>
  <div class="app-container">
    <el-card class="box-card">
      <!-- card卡片头标题信息 -->
      <div slot="header" class="clearfix">
        <span>修改密码</span>
      </div>
      <!-- 卡片主题表单信息 -->
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px">
        <el-row>
          <el-col :span="14" :offset="4">
            <el-form-item label="原密码" prop="oldPassword">
              <el-input v-model="dataForm.oldPassword" type="password"/>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword">
              <el-input v-model="dataForm.newPassword" type="password" auto-complete="off"/>
            </el-form-item>
            <el-form-item label="确认密码" prop="newPassword2">
              <el-input v-model="dataForm.newPassword2" type="password" auto-complete="off"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!-- 卡边底部提交重置按钮 -->
      <div class="bottom" >
        <el-row type="flex" class="row-bg" justify="end">
          <el-button type="primary" @click="change">确定</el-button>
          <el-button @click="cancel">重置</el-button>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import { rePassword } from '@/api/rePassword/repassword'

export default {
  name: 'ChangePassword',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.dataForm.newPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      dataForm: {
        oldPassword: '',
        newPassword: '',
        newPassword2: ''
      },
      rules: {
        oldPassword: [
          { required: true, message: '旧密码不能为空', trigger: 'blur' },
          { min: 6, max: 8, message: '密码长度在 6 到 8 个字符', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '新密码不能为空', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' },
          { min: 6, max: 8, message: '密码长度在 6 到 8 个字符', trigger: 'blur' }
        ],
        newPassword2: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' },
          { min: 6, max: 8, message: '密码长度在 6 到 8 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    cancel() {
      this.dataForm = {
        oldPassword: '', // 原密码
        newPassword: '', // 新密码
        newPassword2: '' // 确认密码
      }
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    change() {
      this.$refs['dataForm'].validate((valid) => {
        if (!valid) {
          return
        }
        rePassword(this.dataForm).then(response => {
          this.$notify.success({
            title: '成功',
            message: '修改密码成功'
          })
          // 调用vuex 注销用户
          this.$store.dispatch('LogOut').then(() => {
            location.reload()
          })
        }).catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
      })
    }
  }
}
</script>
