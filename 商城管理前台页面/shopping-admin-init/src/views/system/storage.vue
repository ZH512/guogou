<template>
    <div class="app-container">
        <div class="filter-container" style="float:right; line-height:50px;">
            <el-row :gutter="20" style="float:right;">
                <el-col :span="8">
                    <el-form label-width="80px">
                        <el-input v-model="searchList.key" placeholder="请输入对象KEY" clearable/>
                    </el-form>
                </el-col>
                <el-col :span="7">
                    <el-form label-width="80px">
                        <el-input v-model="searchList.name" placeholder="请输入对象名称" clearable/>
                    </el-form>
                </el-col>
                <el-col :span="9"style="float:right;text-align: center;">
                    <el-button type="primary" icon="el-icon-search" @click="getDate">查询</el-button>
                    <el-button type="primary" icon="el-icon-edit" @click="addStorage">添加</el-button>
                </el-col>
            </el-row>
        </div>
        <el-table
            :data="tableData"
            border
            style="width: 100%">
            <el-table-column align="center" prop="key" label="对象KEY"/>
            <el-table-column align="center" prop="name" label="对象名称"/>
            <el-table-column align="center" prop="type" label="对象类型"/>
            <el-table-column align="center" prop="size" label="对象大小"/>
            <el-table-column align="center" label="图片">
                <template slot-scope="scope">
                    <img :src="scope.row.url" style="width:40px;height:40px"/>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="url" label="图片链接"/>
            <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini" @click="handleDel(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page"
                :page-sizes="[10,20,30,40,50,100]"
                :page-size="limit"
                layout="total, sizes, prev, pager, next, jumper"
                :total="dataSize"
            ></el-pagination>
        </div>
        <!--新增-->
        <el-dialog
            title="上传对象"
            :visible.sync="addStorageDig"
            width="50%"
            :before-close="cancelAddStorageDig">
            <div>
                <el-row>
                    <el-form label-width="100px" :model="addDataList" ref="addDataListRest">
                        <div class="upLoadImg">
                            <el-upload
                                :show-file-list="false"
                                :action="uploadPath"
                                :headers="headers"
                                :on-success="handleGalleryUrl"
                                accept=".jpg,.jpeg,.png,.gif"
                                >
                                <el-button type="primary">点击上传</el-button>
                            </el-upload>
                        </div>
                    </el-form>
                </el-row>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import {listStorage, deleteStorage} from '../../api/system/storage'
import { getToken } from '@/utils/auth'
 export default {
    data() {
        return {
            searchList: {
                key: "",
                name: ""
            }, //搜索关键字
            tableData: [{}], //表格数据  
            page: 1, //页数
            limit: 20, //每页大小
            dataSize: 0, //总条数
            addStorageDig: false, //新增学制模态框状态
            addDataList:{
                key:'',
                name:'',
                type:'',
                size:'',
                url:'',
            },
            uploadPath:process.env.BASE_API+'/storage/create',
            headers:{'Shopping-Admin-Token':getToken()}
        }
    }, 
    mounted(){
        //获取数据
        this.getDate()
    },
    methods: { 
        // 获取图片路径 
        handleGalleryUrl(response, file, fileList) {
            this.addStorageDig = false;
            this.getDate()
            this.$notify({
                title: '成功',
                message: '上传成功',
                type: 'success'
            });
        }, 
        getDate(){
            listStorage({limit: this.limit, page: this.page,
                        key: this.searchList.key,name: this.searchList.name}).then(response => {
                this.tableData = response.data.data.list
                this.dataSize = response.data.data.total
            }).catch(error => {
                this.$message({
                showClose: true,
                message: '对象存储列表获取失败',
                type: 'error'
                })
            })
        },
        //每页条数
        handleSizeChange(val) {
            this.limit = val
            this.getDate()
        },
        //第几页
        handleCurrentChange(val){
            this.page = val
            this.getDate()
        },
        //打开新增学制模态框
        addStorage() {
        this.addStorageDig = true;
        },
        //新增学制模态框关闭钩子
        cancelAddStorageDig() {
            this.addStorageDig = false;
        },
        handleDel(row) {
            const data = { key: row.key };
            console.log(data);
            this.$confirm("此操作将删除该文件对象，是否继续?", "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                if (this.dataSize.length != 1) {
                    deleteStorage(data).then(response => {
                        this.getDate()
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success'
                        });
                    }).catch(error => {
                        this.$notify({
                            title: '失败',
                            message: "删除失败",
                            type: "error"
                        });
                    });
                } else {
                    // this.$message({
                    //     showClose: true,
                    //     message: "至少保留一条学制数据",
                    //     type: "error"
                    // });
                }
                }).catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除"
                    });
            });
        }
    }
}
</script>

<style lang="scss" scoped>
.storage{

}
</style>