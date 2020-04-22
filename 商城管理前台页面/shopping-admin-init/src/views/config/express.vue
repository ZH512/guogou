<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>运费配置</span>
      </div>
    <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-width="300px">
      <el-form-item label="运费满减所需最低消费" prop="shopping_express_freight_min">
        <el-input v-model="dataForm.shopping_express_freight_min" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="运费满减不足所需运费" prop="shopping_express_freight_value">
        <el-input v-model="dataForm.shopping_express_freight_value" style="width:1000px"/>
      </el-form-item>
      <!-- <el-form-item>
        <el-button type="primary" @click="update">确定</el-button>
        <el-button @click="cancel">重置</el-button>
      </el-form-item> -->
      <div class="bottom">
        <el-button type="primary" @click="update">确定</el-button>
        <el-button @click="cancel">重置</el-button>
      </div>
  </el-form>
  </el-card>
  </div>
</template>

<script>
import { listExpress, updateExpress } from '@/api/config/config'

export default {
  name: 'ConfigExpress',
  data() {
    return {
      dataForm: {
        shopping_express_freight_min: 0,
        shopping_express_freight_value: 0
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
      listExpress().then(response => {
        this.dataForm = response.data.data
      })
    },
    cancel() {
      this.init()
    },
    update() {
      this.$refs['dataForm'].validate((valid) => {
        if (!valid) {
          return
        }
        updateExpress(this.dataForm).then(response => {
          this.$notify.success({
            title: '成功',
            message: '运费配置修改成功'
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
<style rel="stylesheet/scss" lang="scss" scoped>
.bottom{
  width: 100%;
  height: 80px;
  line-height: 80px;
  float: right;
  padding: 0 0 0 90%;
}
</style>