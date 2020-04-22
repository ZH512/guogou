<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>商城首页配置</span>
      </div>
    <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-width="300px">
      <el-form-item label="新品首发栏目商品显示数量" prop="shopping_wx_index_new">
        <el-input v-model="dataForm.shopping_wx_index_new" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="人气推荐栏目商品显示数量" prop="shopping_wx_index_hot">
        <el-input v-model="dataForm.shopping_wx_index_hot" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="品牌制造商直供栏目品牌商显示数量" prop="shopping_wx_index_brand">
        <el-input v-model="dataForm.shopping_wx_index_brand" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="专题精选栏目显示数量" prop="shopping_wx_index_topic">
        <el-input v-model="dataForm.shopping_wx_index_topic" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="分类栏目显示数量" prop="shopping_wx_catlog_list">
        <el-input v-model="dataForm.shopping_wx_catlog_list" style="width:1000px"/>
      </el-form-item>
      <el-form-item label="分类栏目商品显示数量" prop="shopping_wx_catlog_goods">
        <el-input v-model="dataForm.shopping_wx_catlog_goods" style="width:1000px"/>
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
import { listWx, updateWx } from "@/api/config/config";

export default {
  name: "ConfigWx",
  data() {
    return {
      dataForm: {},
      rules: {
        shopping_express_freight_min: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        shopping_express_freight_value: [
          { required: true, message: "不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.init();
  },
  methods: {
    init: function() {
      listWx().then(response => {
        this.dataForm = response.data.data;
        console.log(this.dataForm);
      });
    },
    cancel() {
      this.init();
    },
    update() {
      updateWx(this.dataForm)
        .then(response => {
          this.$notify.success({
            title: "成功",
            message: "前台界面配置成功"
          });
        })
        .catch(response => {
          this.$notify.error({
            title: "失败",
            message: response.data.errmsg
          });
        });
    }
  }
};
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