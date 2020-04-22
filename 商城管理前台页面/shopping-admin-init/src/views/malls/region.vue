<template>
  <div class="app-container">
   <el-table
    v-loading="loading"
    element-loading-text="正在查询中..."
    :data="tableData"
    style="width: 100%;margin-bottom: 20px;"
    row-key="id"
    border
    default-expand-all
    :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column prop="name" label="区域名称" width="400"></el-table-column>
      <el-table-column prop="type" label="区域类型" width="400">
         <template slot-scope="scope">
          <span v-if="scope.row.type == 1">市</span>
          <span v-else-if="scope.row.type == 2">省</span>
          <span v-else>区</span>
          </template>
      </el-table-column>
      <el-table-column prop="code" label="区域编码"></el-table-column>
    </el-table>
  </div>
</template>
<script>
import { regionlist } from "@/api/malls/region";
export default {
  data() {
    return {
      loading: true,
      tableData: [],
    };
  },
  mounted() {
    this.getdata();
  },

  methods: {
    getdata() {
      regionlist()
        .then(res => {
          if ((res.data.responseCode == 0)) {
               this.tableData = res.data.data 
               this.loading = false
          } else {
            this.$notify.error({
              title: res.data.data,
              message: res.data.responseMsg
            });
          }
        })
        .catch(response => {
          this.$notify.error({
            title: "失败",
            message: "系统出现错误"
          });
        });
    },
  }
};
</script>

<style scoped>
</style>
