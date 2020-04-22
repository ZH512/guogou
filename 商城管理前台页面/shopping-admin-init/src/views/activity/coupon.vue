<template>
    <div class="app-coupon">

    <!-- 查询和其他操作 -->
    <div class="filter-coupon" style="float: right;">
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入优惠券标题"/>
      <el-select v-model="listQuery.type" placeholder="请选择优惠券类型" clearable>
        <el-option label="通用领劵" value="0"></el-option>
        <el-option label="注册赠劵" value="1"></el-option>
        <el-option label="兑换码" value="2"></el-option>
      </el-select>
      <el-select v-model="listQuery.status" placeholder="请选择优惠券状态" clearable>
        <el-option label="正常" value="0"></el-option>
        <el-option label="已过期" value="1"></el-option>
        <el-option label="已下架" value="2"></el-option>
      </el-select>
      <el-button v-permission="['GET /admin/coupon/list']" class="filter-item" type="primary" icon="el-icon-search" @click="getList">查找</el-button>
      <el-button v-permission="['POST /admin/coupon/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" min-width="80" sortable label="优惠券ID" prop="id"/>
      <el-table-column align="center" min-width="80" label="优惠券名称"  prop="name"/>
      <el-table-column align="center" min-width="80" label="介绍"  prop="desc"/>
      <el-table-column align="center" min-width="80" label="标签" prop="tag"/>
      <el-table-column align="center" min-width="80" label="最低消费" prop="min">
        <template slot-scope="scope">
          <p>满{{scope.row.min}}元可用</p>
        </template>
      </el-table-column>
      <el-table-column min-width="80" align="center" label="满减金额" prop="discount">
        <template slot-scope="scope">
          <p>减免{{scope.row.discount}}元</p>
        </template>
      </el-table-column>
      <el-table-column min-width="80" align="center" label=" 每人限领" prop="limit">
        <template slot-scope="scope">
        <p v-if="scope.row.limit!=0">{{scope.row.limit}}</p>
          <p v-else>不限</p>
        </template>
      </el-table-column>
      <el-table-column align="center" min-width="100" label="商品使用范围" prop="goodsType">
        <template slot-scope="scope">
          <p>{{scope.row.goodsType | formatGoodsType}}</p>
        </template>
      </el-table-column>
      <el-table-column min-width="80" align="center" label="优惠券类型" prop="type">
        <template slot-scope="scope">
          <p>{{scope.row.type | formatType2}}</p>
        </template>
      </el-table-column>
      <el-table-column min-width="80" align="center" label="优惠券数量" prop="total">
        <template slot-scope="scope">
          <p v-if="scope.row.total!=0">{{scope.row.total}}</p>
          <p v-else>不限</p>
        </template>
       </el-table-column>
      <el-table-column min-width="80" align="center" label="状态" prop="status">
        <template slot-scope="scope">
          <p>{{scope.row.status | formatStatus}}</p>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/coupon/read','GET /admin/coupon/listuser']" type="primary" size="mini" @click="handleCouponInfo(scope.row)">详情</el-button>
          <el-button  type="info" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button  type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
  <!-- 对话框 -->
    <el-dialog
      :visible.sync="dialogFormVisible"
      width="45%"
      :before-close="handleClose">
      <div slot="title" class="dialogTitle" v-if="dialogStatus">新增</div>
      <div slot="title" class="dialogTitle" v-else>编辑</div>
      <el-form :model="dataForm" status-icon :rules="rules" ref="dataForm" label-width="100px" class="demo-dataForm">
        <el-form-item label="优惠券名称" prop="name">
          <el-input v-model="dataForm.name"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="desc">
          <el-input v-model="dataForm.desc"></el-input>
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-input v-model="dataForm.tag"></el-input>
        </el-form-item>
        <el-form-item label="最低消费" prop="min">
          <el-input v-model="dataForm.min">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="满减金额" prop="discount">
          <el-input v-model="dataForm.discount">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="每人限领" prop="limit">
          <el-input v-model="dataForm.limit">
            <template slot="append">张</template>
          </el-input>
        </el-form-item>
        <el-form-item label="分发类型" prop="type">
          <el-select v-model="dataForm.type" placeholder="请选择优惠券类型" clearable>
            <el-option label="通用领劵" value=0></el-option>
            <el-option label="注册赠劵" value=1></el-option>
            <el-option label="兑换码" value=2></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="优惠券数量" prop="total">
          <el-input v-model="dataForm.total">
            <template slot="append">张</template>
          </el-input>
        </el-form-item>
        <el-form-item label="有效期" prop="timeType">
          <el-radio-group v-model="dataForm.timeType" size="small">
            <el-radio-button label="0">领券相对天数</el-radio-button>
            <el-radio-button label="1">指定绝对时间</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="days" v-if="dataForm.timeType=='0'">
          <el-input v-model="dataForm.days">
            <template slot="append">天</template>
          </el-input>
        </el-form-item>
         <el-form-item  class="date" v-else> 
          <el-col :span="11">
              <el-date-picker required type="date" placeholder="选择日期" v-model="dataForm.startTime" style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">至</el-col>
          <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="dataForm.endTime" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="商品限制范围" prop="goodsType">
          <el-radio-group v-model="dataForm.goodsType" size="small" @change=goodsType>
            <el-radio-button label="0">全场通用</el-radio-button>
            <el-radio-button label="1">指定分类</el-radio-button>
            <el-radio-button label="2">指定商品</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="dataForm.goodsType!=0">
          <div>目前不支持</div>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="handleStatus()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { listCoupon, deleteCoupon ,updateCoupon,createCoupon} from '@/api/activity/coupon'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
    export default {
     name: 'Topic',
  components: { BackToTop, Pagination },
  data() {
    return {
      query: '1',
      dialogFormVisible: false,
      dialogStatus:true,//true  新增 false修改
      list: [],
      total: 0,
      dataForm :{
        name:"",
        desc:"",
        tag:"",
        min:0,
        discount:0,
        limit:1,
        goodsType:0,
        type:'0',
        total:0,
        days:0,
        startTime:"",
        endTime:'',
        timeType:0,
      },
      listQuery: {
        name:undefined,
        type:undefined,
        status:undefined,
        page: 1,
        limit: 20,
        sort: 'add_time',
        order: 'desc'
      },
      rules: {
          name: [
            { required: true, message: '优惠券标题不能为空', trigger: 'blur' },
          ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    //转换为int型
    goodsType(){
      this.dataForm.goodsType=parseInt(this.dataForm.goodsType)
    },
    //查看优惠劵详情
    handleCouponInfo(row) {
      this.$router.push({ path: 'couponDetail', query: { id: row.id }})
    },
    //重置
    restoreDialog(){
      this.$refs['dataForm'].clearValidate()
      this.dataForm={
        name:"",
        desc:"",
        tag:"",
        min:0,
        discount:0,
        limit:1,
        goodsType:0,
        type:'0',
        total:0,
        days:0,
        startTime:"",
        endTime:'',
        timeType:0,
      }
    },
     // 删除优惠券
    handleDelete(row) {

      this.$confirm('此操作将删除该优惠券, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCoupon(row)
          .then(response => {
            this.$notify.success({
              title: '成功',
              message: '删除优惠券成功'
            })
            const index = this.list.indexOf(row)
            this.list.splice(index, 1)
          })
          .catch(response => {
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
    },
    //确定 对话框
    handleStatus(){
      if(this.dialogStatus){
        this.createData()
      }else{
        this.updateData()
      }
    },
    //关闭按钮
    handleClose(done) {
      done();
      },
      //取消对话框
      handleClose() {
      this.dialogFormVisible=false
      },
    // 添加钩子，启动模态框以及其类型状态
    handleCreate() {
      this.dialogStatus =true
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.restoreDialog()
      })
    },
    // 确认创建优惠券
    createData() {
      this.dataForm.type=parseInt(this.dataForm.type)
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createCoupon(this.dataForm)
            .then(response => {
              this.getList()
              this.dialogFormVisible = false         
              this.$notify.success({
                title: '成功',
                message: '创建优惠券成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    // 编辑钩子函数
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = false
      this.dialogFormVisible = true
      this.dataForm.type=this.dataForm.type+""
    },
    // 确认编辑
    updateData() {
      this.dataForm.type=parseInt(this.dataForm.type)
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          updateCoupon(this.dataForm)
            .then(() => {
              this.dataForm.timeType=parseInt(this.dataForm.timeType)
              for (const v of this.list) {
                if (v.id === this.dataForm.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              
              this.$notify.success({
                title: '成功',
                message: '更新优惠券成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    //获取数据
   getList() {
      this.listLoading = true
      listCoupon(this.listQuery)
        .then(response => {
          this.list = response.data.data.list
          this.total = parseInt(response.data.data.total)
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
  },
  filters: {
    // 过滤器将type转换对应的优惠券类型
    formatType(type) {
      for (let i = 0; i < defaultTypeOptions.length; i++) {
        if (type === defaultTypeOptions[i].value) {
          return defaultTypeOptions[i].label
        }
      }
      return ''
    },
    formatType2(type) {
      if (type === 0) {
        return '通用领劵'
      } else if (type === 1) {
        return '注册赠劵'
      } else {
        return '兑换码'
      }
    },
    // 转换适用范围
    formatGoodsType(goodsType) {
      goodsType=parseInt(goodsType)
      if (goodsType === 0) {
        return '全场通用'
      } else if (goodsType === 1) {
        return '指定分类'
      } else {
        return '指定商品'
      }
    },
    // 转化优惠券状态
    formatStatus(status) {
      if (status === 0) {
        return '正常'
      } else if (status === 1) {
        return '已过期'
      } else {
        return '已下架'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dialogTitle{
  font-size: 18px;
  color: #303133;
}
.filter-coupon{
  margin-bottom:20px;
}
.app-coupon {
    padding: 20px;
}
</style>
<style>
.date .el-input--medium .el-input__inner {
    height: 36px;
    line-height: 36px;
    width: 130px;
}
.app-coupon .el-input__inner{
      width: 100%;
}
.app-coupon .el-dialog__body {
    padding: 40px 60px;
}
.app-coupon .el-form-item__label {
    text-align: left;
}
.app-coupon .el-form-item__content {
    width: 300px;
    height: 36px;
}
.app-coupon .el-table th.gutter{
    display: table-cell!important;
}
</style>