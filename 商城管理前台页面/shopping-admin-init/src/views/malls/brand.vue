<template>
  <div class="app-container">
    <div class="shousuo">
      <el-row :gutter="20">
        <el-col :span="7">
          <el-form label-width="100px">
            <el-input v-model="brandlist.id" placeholder="请输入品牌商ID"></el-input>
          </el-form>
        </el-col>
        <el-col :span="7">
          <el-form label-width="100px">
            <el-input v-model="brandlist.name" placeholder="请输入品牌商名称"></el-input>
          </el-form>
        </el-col>
        <el-col :span="6" style="width: 35%;">
          <el-button type="primary" icon="el-icon-search" @click="setectData()">查询</el-button>
          <el-button type="primary" icon="el-icon-edit" @click="resetData()">添加</el-button>
        </el-col>
      </el-row>
    </div>
    <el-table
      :data="tableData"
      border
      style="width: 100%"
      v-loading="loading"
      element-loading-text="正在查询中..."
    >
      <el-table-column fixed prop="id" label="品牌商ID" width="100"></el-table-column>
      <el-table-column prop="name" label="品牌商名称" width="150"></el-table-column>
      <el-table-column label="品牌商图片" width="180">
        <template slot-scope="scope">
          <span>
            <img :src="scope.row.picUrl" style="width:100px" />
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="desc" label="介绍"></el-table-column>
      <el-table-column prop="floorPrice" label="低价" width="100"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <template>
      <!-- 分页 -->
      <pagination
        :total="total"
        :page.sync="brandlist.pagenum"
        :limit.sync="brandlist.pagesize"
        @pagination="getList"
      />
      <!--添加模态框 -->
      <el-dialog
        title="添加"
        :visible.sync="addSpecDig"
        width="30%"
        :before-close="cancelAddSpecDig"
        center
      >
        <el-row>
          <el-col :span="21" :offset="1">
            <el-form
              ref="addSpecData"
              :model="addSpecData"
              label-width="100px"
              :inline="true"
              :label-position="'right'"
              status-icon
              :rules="rules"
            >
              <el-form-item prop="name" label="品牌商名称">
                <el-input v-model="addSpecData.name" />
              </el-form-item>
              <el-form-item prop="desc" label="介绍">
                <el-input v-model="addSpecData.desc" />
              </el-form-item>
              <el-form-item label="品牌商图片">
                <el-upload
                  :headers="headers"
                  class="avatar-uploader"
                  :action="storage"
                  :show-file-list="true"
                   accept=".jpg,.jpeg,.png,.gif"
                  :on-success="handleAvatarSuccess"
                >
                  <img v-if="addSpecData.picUrl" :src="addSpecData.picUrl" class="avatar" />
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item prop="floorPrice" label="底价">
                <el-input v-model="addSpecData.floorPrice" />
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancelAddSpecDig">取 消</el-button>
          <el-button type="primary" @click="addSubmitBtn()">确定</el-button>
        </span>
      </el-dialog>
      <!--修改模态框 -->
      <el-dialog
        title="编辑"
        :visible.sync="updataSpecDig"
        width="30%"
        :before-close="cancelAddSpecDig"
      >
        <el-row>
          <el-col :span="21" :offset="1">
            <el-form
              ref="updataSpecData"
              :model="updataSpecData"
              label-width="100px"
              :inline="true"
              :label-position="'right'"
              status-icon
              :rules="rules"
            >
              <el-form-item prop="name" label="品牌商名称">
                <el-input v-model="updataSpecData.name" />
              </el-form-item>
              <el-form-item prop="desc" label="介绍">
                <el-input v-model="updataSpecData.desc" />
              </el-form-item>
              <el-form-item label="品牌商图片">
                <el-upload
                  :headers="headers"
                  class="avatar-uploader"
                  :action="storage"
                  :show-file-list="true"
                   accept=".jpg,.jpeg,.png,.gif"
                  :on-success="handleAvatarSuccess"
                >
                  <img v-if="updataSpecData.picUrl" :src="updataSpecData.picUrl" class="avatar" />
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item prop="floorPrice" label="底价">
                <el-input v-model="updataSpecData.floorPrice" />
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancelAddSpecDig">取 消</el-button>
          <el-button type="primary" @click="updateSubmitBtn()">确定</el-button>
        </span>
      </el-dialog>
    </template>
  </div>
</template>

<script>
const storage = process.env.BASE_API + '/storage/create';
import { brandlist, brandadd,storagecreate,deleteadd,brandupdate } from "@/api/malls/brand";
import { getToken} from "@/utils/auth"
import Pagination from "@/components/Pagination";
export default {
  components: { Pagination },
  data() {
    return {
      storage,
      headers:{"Shopping-Admin-Token":getToken()},
      addSpecData: {
        name: "",
        desc: "",
        floorPrice: "",
        picUrl: ""
      },
      updataSpecData:{
        id:"",
        name: "",
        desc: "",
        floorPrice: "",
        picUrl: ""
      },
      addSpecDig: false,
      updataSpecDig: false,
      loading: true,
      total: 0,
      tableData: [],
      brandlist: {
        pagenum: 1,
        pagesize: 10,
        id: "",
        name: ""
      },
      id:{
        id:""
      },
      rules: {
        name: [{ required: true, message: "品牌商名称为空", trigger: "blur" }],
        desc: [{ required: true, message: "介绍不能为空", trigger: "blur" }],
        floorPrice: [
          { required: true, message: "低价不能为空", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    getdata() {
      brandlist(this.brandlist)
        .then(res => {
          if (res.data.responseCode == 0) {
            this.total = res.data.data.total;
            this.tableData = res.data.data.list;
            this.loading = false;
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
    //图片路径
    handleAvatarSuccess: function(response) {
          this.addSpecData.picUrl = response.data.url
          this.updataSpecData.picUrl = response.data.url},
    //修改
    handleEdit(row) {
      this.updataSpecDig = true
      this.updataSpecData= { ...row }
    },
    //删除
    handleDelete(row) {
      this.id.id = { ...row }.id
      this.$confirm('此操作将删除该品牌商, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
        deleteadd(this.id).then(res => {
        if (res.data.responseCode == 0) {
            this.$notify({
            title: res.data.data,
            message: res.data.responseMsg,
            type: 'success'
        });
        } else {
          this.$notify.error({
            title: "错误",
            message: res.responseMsg
          });
        }
        this.getdata()
      });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    getList(pagenum) {
      this.brandlist.pagenum = pagenum.page;
      this.brandlist.pagesize = pagenum.limit;
      this.getdata();
    },
    setectData() {
      this.getdata();
    },
    resetData() {
      this.addSpecDig = true;
    },
    cancelAddSpecDig() {
      this.updataSpecDig = false
      this.addSpecDig = false;
    },
    //修改
    updateSubmitBtn(){
      brandupdate(this.updataSpecData).then(res => {
        if (res.data.responseCode == 0) {
            this.getdata()
            this.updataSpecDig = false;
            this.$notify({
            type: 'success',
            title: res.data.data,
            message: res.data.responseMsg
          });
        } else {
          this.$notify.error({
            title: res.data.data,
            message: res.data.responseMsg
          });
        }
      });
    },
    //新增
    addSubmitBtn() {
      brandadd(this.addSpecData).then(res => {
        if (res.data.responseCode == 0) {
            this.getdata()
            this.addSpecDig = false;
            this.addSpecData = {}
            this.$notify({
            type: 'success',
            title: res.data.data,
            message: res.data.responseMsg
          });
        } else {
          this.$notify.error({
            title: res.data.data,
            message: res.data.responseMsg
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.shousuo {
  float: right;
  margin-bottom: 20px;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
