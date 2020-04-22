<template>
    <div class="app-container">
        <div class="filter-container" style="float:right; line-height:50px;">
            <el-button type="primary" icon="el-icon-edit" @click="dialogAddVisible = true">添加</el-button>
        </div>
        <el-table
            :data="tableData"
            style="width: 100%;margin-bottom: 20px;"
            row-key="id"
            border
            default-expand-all
            :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column align="center" prop="id" label="类目ID" width="180"/>
            <el-table-column align="center" prop="name" label="类目名" width="180"/>
            <el-table-column align="center" label="类目图标">
                <template slot-scope="scope">
                    <img :src="scope.row.iconUrl" style="width:40px;height:40px"/>
                </template>
            </el-table-column>
            <el-table-column align="center" label="类目图片">
                <template slot-scope="scope">
                    <img :src="scope.row.picUrl" style="width:40px;height:40px"/>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="keywords" label="关键字"/>
            <el-table-column align="center" prop="desc" label="简介"/>
            <el-table-column align="center" label="级别">
                <template  slot-scope="scope">
                    <el-tag v-if="scope.row.level=='L1'">一级类目</el-tag>
                    <el-tag v-else type="info">二级类目</el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 新增 -->
        <el-dialog title="创建" :visible.sync="dialogAddVisible">
            <el-form 
                ref="addCategoryDate"
                :model="addCategoryDate" 
                label-width="80px" 
                label-position="left" 
                style="margin: 0 0 0 50px;" 
                :rules="rules">
                <el-form-item label="类目名称" prop="name">
                    <el-input v-model="addCategoryDate.name" autocomplete="off" style="width:320px;"></el-input>
                </el-form-item>
                <el-form-item label="关键字">
                    <el-input v-model="addCategoryDate.keywords" autocomplete="off" style="width:320px;"></el-input>
                </el-form-item>
                <el-form-item label="级别">
                    <el-select v-model="addCategoryDate.level" placeholder="请选择类目">
                        <el-option label="一级类目" value="L1"></el-option>
                        <el-option label="二级类目" value="L2"></el-option>
                    </el-select>
                </el-form-item>
                <template v-if="addCategoryDate.level=='L2'">
                    <el-form-item label="父类目" prop="pid">
                        <el-select v-model="addCategoryDate.pid" placeholder="请选择">
                            <el-option 
                                v-for="item in levelData"
                                :key="item.id"
                                :label="item.name" 
                                :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </template>
                <el-form-item label="类目图标">
                    <el-upload
                        :action="uploadPath"
                        :headers="headers"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess">
                        <img v-if="addCategoryDate.iconUrl" :src="addCategoryDate.iconUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="类目图片">
                    <el-upload
                        :action="uploadPath"
                        :headers="headers"
                        :show-file-list="false"
                        :on-success="handleCategorySuccess">
                        <img v-if="addCategoryDate.picUrl" :src="addCategoryDate.picUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="类目简介">
                    <el-input v-model="addCategoryDate.desc" autocomplete="off" style="width:320px;"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelAddCategoryDig">取 消</el-button>
                <el-button type="primary" @click="addCategory('addCategoryDate')">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 修改 -->
        <el-dialog title="编辑" :visible.sync="dialogEditVisible">
            <el-form 
                ref="editCategoryDate"
                :model="editCategoryDate" 
                label-width="80px" 
                label-position="left" 
                style="margin: 0 0 0 50px;" 
                :rules="rules">
                <el-form-item label="类目名称" prop="name">
                    <el-input v-model="editCategoryDate.name" autocomplete="off" style="width:320px;"></el-input>
                </el-form-item>
                <el-form-item label="关键字">
                    <el-input v-model="editCategoryDate.keywords" autocomplete="off" style="width:320px;"></el-input>
                </el-form-item>
                <el-form-item label="级别">
                    <el-select v-model="editCategoryDate.level" placeholder="请选择类目">
                        <el-option label="一级类目" value="L1"></el-option>
                        <el-option label="二级类目" value="L2"></el-option>
                    </el-select>
                </el-form-item>
                <template v-if="editCategoryDate.level=='L2'">
                    <el-form-item label="父类目" prop="pid">
                        <el-select v-model="editCategoryDate.pid" placeholder="请选择">
                            <el-option 
                                v-for="item in levelData"
                                :key="item.id"
                                :label="item.name" 
                                :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </template>
                <el-form-item label="类目图标">
                    <el-upload
                        :action="uploadPath"
                        :headers="headers"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess">
                        <img v-if="editCategoryDate.iconUrl" :src="editCategoryDate.iconUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="类目图片">
                    <el-upload
                        :action="uploadPath"
                        :headers="headers"
                        :show-file-list="false"
                        :on-success="handleCategorySuccess">
                        <img v-if="editCategoryDate.picUrl" :src="editCategoryDate.picUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="类目简介">
                    <el-input v-model="editCategoryDate.desc" autocomplete="off" style="width:320px;"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelEditCategoryDig">取 消</el-button>
                <el-button type="primary" @click="editSubmitBtn('editCategoryDate')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import {listCategory, addCategory, deleteCategory, updateCategory, l1} from '../../api/malls/category'
import { getToken } from '@/utils/auth'
export default {
    data() {
      return {
        tableData: [{}],
        levelData:[],
        dialogAddVisible: false,
        dialogEditVisible: false,
        addCategoryDate: {
          name: '',
          keywords:'',
          level: 'L2',
          pid:'',
          desc:'',
          iconUrl: '',
          picUrl:'',
        },
        editCategoryDate:{
          name: '',
          keywords:'',
          level: '',
          pid:'',
          desc:'',
          iconUrl: '',
          picUrl:'',
        },
        uploadPath:process.env.BASE_API+'/storage/create',
        headers:{'Shopping-Admin-Token':getToken()},
        rules:{
            name:[
                {required:true,message:'请输入类目名称',trigger:'blur'},
                {min:1,max:22,message:'长度在 1 到 200 个字符', trigger:'blur'}
            ],
            pid:[
                {required:true,message:'请选择父类目名称',trigger:'blur'},
            ]
        }
      }
    },
    mounted(){
        //获取数据
        this.getDate()
        this.getLevelDate()
    },
    methods: {
        getDate(){
            listCategory().then(response => {
                this.tableData = response.data.data.list
                // console.log(this.tableData)
            }).catch(error => {
                this.$message({
                showClose: true,
                message: '商品类目列表获取失败',
                type: 'error'
                })
            })
        },
        getLevelDate(){
            let list={
                level:"L1"
            }
            l1(list).then(response => {
                this.levelData = response.data.data
                // console.log(this.levelData)
            }).catch(error => {
                this.$message({
                    showClose: true,
                    message: '获取一级商品类目列表',
                    type: 'error'
                })
            })
        },
        handleAvatarSuccess(response) {
            this.addCategoryDate.iconUrl = response.data.url
            // console.log(this.addCategoryDate.iconUrl)
        },
        handleCategorySuccess(res, file) {
            this.addCategoryDate.picUrl = res.data.url
        },
        //新增类目
        addCategory(fromName) {
            this.$refs[fromName].validate(valid => {
                if (valid) {
                //数据验证
                console.log(this.addCategoryDate.pid)
                const data = {
                    //提交服务器端数据
                    name: this.addCategoryDate.name,
                    keywords: this.addCategoryDate.keywords,
                    level: this.addCategoryDate.level,
                    pid: this.addCategoryDate.pid,
                    desc: this.addCategoryDate.desc,
                    iconUrl: this.addCategoryDate.iconUrl,
                    picUrl: this.addCategoryDate.picUrl,
                };
                if(data.level=="L1"){
                    data.pid ==0
                }
                addCategory(data)
                    .then(response => {
                    if (response.data.responseMsg == "Success") {
                        //响应结果
                        this.getDate(); //刷新数据
                        this.cancelAddCategoryDig(); //关闭对话框
                        this.$notify({
                            title: '成功',
                            message: "新增成功",
                            type: "success"
                        });
                    } else {
                        this.$notify({
                            title: '失败',
                            message: response.RET_MSG,
                            type: "error"
                        });
                    }
                    }).catch(error => {
                        this.$notify({
                            title: '失败',
                            message: "添加失败",
                            type: "error"
                        });
                    });
                } else {
                    this.$notify({
                        title: '失败',
                        type: "error",
                        message: "提示：您填写的信息有误"
                    });
                }
            });
        },
        //新增模态框关闭钩子
        cancelAddCategoryDig() {
            this.dialogAddVisible = false;
            this.dialogEditVisible = false;
            this.addCategoryDate={
                name: '',
                keywords:'',
                level: '',
                pid:'',
                desc:'',
                iconUrl: '',
                picUrl:'',
            };
        },
        //打开修改学制模态框
        handleEdit(row) {
            this.editCategoryDate = { ...row };
            this.dialogEditVisible = true;
        },
        //设置修改学制模态框关闭钩子
        cancelEditCategoryDig() {
            this.dialogEditVisible = false;
        },
        //修改
        editSubmitBtn(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    let data = {
                        //提交服务器端数据
                        children: this.editCategoryDate.children,
                        name: this.editCategoryDate.name,
                        keywords: this.editCategoryDate.keywords,
                        level: this.editCategoryDate.level,
                        pid: this.editCategoryDate.pid,
                        desc: this.editCategoryDate.desc,
                        iconUrl: this.editCategoryDate.iconUrl,
                        picUrl: this.editCategoryDate.picUrl,
                        id:this.editCategoryDate.id
                    }
                    if(data.level=="L1"){
                        data.pid = 0
                    }
                    //数据验证
                    updateCategory(data)
                        .then(response => {
                        if (response.data.responseMsg == "Success") {
                            this.getDate();
                            this.cancelEditCategoryDig();
                            this.$notify({
                                title: '成功',
                                message: "修改成功",
                                type: "success"
                            });
                        } else {
                            this.$notify({
                                title: '失败',
                                message: response.RET_MSG,
                                type: "error"
                            });
                        }
                    }).catch(error => {
                        this.$notify({
                            title: '失败',
                            message: "修改失败",
                            type: "error"
                        });
                    });
                } else {
                    this.$notify({
                        title: '失败',
                        type: "error",
                        message: "提示：您填写的信息有误"
                    });
                }
            });
        },
        //删除
        handleDelete(row) {
            this.$confirm('此操作将删除该类目, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                deleteCategory(row).then(response => {
                    this.getDate()
                    this.$notify.success({
                        title: '成功',
                        message: '删除成功'
                    })
                }).catch(response => {
                    this.$notify.error({
                        title: '失败',
                        message: response.data.errmsg
                    })
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })
            })
        }
  
    },
}
</script>

<style lang="scss" scoped>
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
    width: 150px;
    height: 120px;
    line-height: 150px;
    text-align: center;
  }
  .avatar {
    width: 150px;
    height: 120px;
    display: block;
  }
</style>