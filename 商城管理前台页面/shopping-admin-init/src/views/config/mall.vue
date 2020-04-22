<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>商城信息配置</span>
      </div>
    <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-width="300px">
      <el-form-item label="商场名称" prop="shopping_mall_name">
        <el-input v-model="dataForm.shopping_mall_name" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="商场地址" prop="shopping_mall_address">
        <el-input v-model="dataForm.shopping_mall_address" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="联系电话" prop="shopping_mall_phone">
        <el-input v-model="dataForm.shopping_mall_phone" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="联系QQ" prop="shopping_mall_qq">
        <el-input v-model="dataForm.shopping_mall_qq" style="width:1000px"/>
      </el-form-item>
      
      <!-- <el-form-item>
        <el-button type="primary" @click="update">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </el-form-item> -->
      <div class="bottom">
        <el-button type="primary" @click="update">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-form>
    </el-card>
  </div>
</template>

<script>
import { listMall, updateMall } from '@/api/config/config'

export default {
  name: 'ConfigMail',
  data() {
    return {
      dataForm: {
        shopping_mall_name: '',
        shopping_mall_address: '',
        shopping_mall_phone: '',
        shopping_mall_qq: ''
      },
      rules: {
        shopping_express_freight_min: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        shopping_express_freight_value: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init: function() {
      listMall().then(response => {
        this.dataForm = response.data.data
      })
    },
    cancel() {
      this.init()
    },
    update() {
      updateMall(this.dataForm)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '商场配置成功'
          })
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.bottom{
  width: 100%;
  height: 80px;
  line-height: 80px;
  float: right;
  padding: 0 0 0 90%;
}
</style>