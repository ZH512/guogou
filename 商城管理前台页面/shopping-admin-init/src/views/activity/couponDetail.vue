<template>
    <div class="app-coupon">
      <table border="0" cellspacing="2" cellpadding="4" bgcolor="#dcdfe6"  align="center">
        <tr height="37px" align="center" class="title">
          <td >名称</td><td>介绍名称</td><td>标签</td><td>优惠券类型</td><td>最低消费</td><td>优惠面值</td>
        </tr>
        <tr  align="center" class="content">
          <td>{{couponData.name}}</td><td>{{couponData.desc}}</td><td>{{couponData.tag}}</td><td>{{couponData.type | formatType2}}</td>
          <td>满{{couponData.min}}元可用</td><td>减免{{couponData.discount}}元</td>
        </tr>
        <tr  align="center" class="title">
          <td>每人限额</td><td>当前状态</td><td>商品范围</td><td>有效期</td><td>优惠兑换码</td><td>发行数量</td>
        </tr>
        <tr align="center" class="content">
          <td v-if="couponData.limit!=0">{{couponData.limit}}</td><td v-else>不限</td><td>{{couponData.status | formatStatus}}</td>
          <td>{{couponData.goodsType | formatGoodsType}}</td><td style="max-width:100px">{{days}}</td>
          <td>{{couponData.code}}</td>
          <td v-if="couponData.total!=0">{{couponData.total}}</td>
          <td v-else>不限</td>
        </tr>
      </table>
    <!-- 查询和其他操作 -->
    <div class="filter-couponUser" style="float: right;">
      <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户id"/>
      <el-select v-model="listQuery.status" placeholder="请选择优惠券状态" clearable>
        <el-option label="未使用" value="0"></el-option>
        <el-option label="已使用" value="1"></el-option>
        <el-option label="已过期" value="2"></el-option>
        <el-option label="已下架" value="3"></el-option>
      </el-select>
      <el-button v-permission="['GET /admin/coupon/couponData']" class="filter-item" type="primary" icon="el-icon-search" @click="listUser">查找</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="用户优惠券ID" prop="id" sortable/>
      <el-table-column align="center" label="用户ID"  prop="userId"/>
      <el-table-column align="center" label="领取时间"  prop="addTime"/>
      <el-table-column align="center" label="使用状态" prop="status">
        <template slot-scope="scope">
          <p>{{scope.row.status | formatUserStatus}}</p>
        </template>
      </el-table-column>
      <el-table-column align="center" label="订单ID" prop="orderId"/>
      <el-table-column align="center" label="使用时间" prop="usedTime"/>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"  />
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
  </div>
</template>

<script>
import {readCoupon,listUser} from '@/api/activity/coupon'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
    export default {
     name: 'Topic',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: [],
      total: 0,
      couponData :{},
      days:'',
      listQuery: {
        couponId:undefined,
        userId:undefined,
        status:undefined,
        page: 1,
        limit: 10,
        sort: 'add_time',
        order: 'desc'
      },
    }
  },
  created() {
    this.listQuery.couponId=this.$route.query.id
    this.readCoupon()
    this.listUser()
  },
  methods: {
    // 有效时间显示转换
    getTimeScope() {
      if (this.couponData.timeType === 0) {
        return '领取' + this.couponData.days + '天有效'
      } else if (this.couponData.timeType === 1) {
        return '自' + this.couponData.startTime + '至' + this.couponData.endTime + '有效'
      } else {
        return '未知'
      }
    },
    listUser() {
      this.listLoading = true
      listUser(this.listQuery)
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
    //获取数据
   readCoupon() {
      const id={
        id:this.$route.query.id
      }
      readCoupon(id)
        .then(response => {
          this.couponData= response.data.data
          this.days=this.getTimeScope()
        })
        .catch(() => {
          this.couponData = {}
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
    },
    // 转化用户使用状态
    formatUserStatus(status) {
      if (status === 0) {
        return '未使用'
      } else if (status === 1) {
        return '已使用'
      }else if (status === 2) {
        return '已过期'
      } else {
        return '已经下架'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.filter-couponUser{
  margin-bottom: 20px;
}
.app-coupon {
    padding: 20px;
}
table{
  margin:20px 0px 20px 0px;
  width: 100%;
  color: #606266;
}
.title td{
  background:#f2f6fc;
}
.title {
  height: 37px;
}
.content {
  height: 60px;
}
.content td{
	background-color:#ffffff;
}
</style>