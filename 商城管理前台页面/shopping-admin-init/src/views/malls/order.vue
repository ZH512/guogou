<template>
  <div class="app-container">
    <div class="shousuo">
      <el-row :gutter="24">
        <el-col :span="6">
          <el-form>
            <el-input v-model="brandlist.userId" placeholder="请输入用户ID"></el-input>
          </el-form>
        </el-col>
          <el-col :span="6">
          <el-form>
            <el-input v-model="brandlist.orderSn" placeholder="请输入订单编号"></el-input>
          </el-form>
          </el-col>
          <el-col :span="6">
          <el-form>
              <el-select v-model="brandlist.orderStatuslist" multiple placeholder="请选择订单状态">
                  <el-option label="未支付" value="1"></el-option>
                  <el-option label="用户取消" value="2"></el-option>
                  <el-option label="系统取消" value="3"></el-option>
                  <el-option label="已支款" value="4"></el-option>
                  <el-option label="申请退款" value="5"></el-option>
                  <el-option label="已退款" value="6"></el-option>
                  <el-option label="已发货" value="7"></el-option>
                  <el-option label="用户发货" value="8"></el-option>
                  <el-option label="系统发货" value="9"></el-option>
              </el-select>
          </el-form>
        </el-col>
        <el-col :span="6" >
          <el-button type="primary" icon="el-icon-search" @click="setectData()">查询</el-button>
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
      <el-table-column fixed prop="orderSn" label="订单编号" align="center" width="150"></el-table-column>
      <el-table-column prop="userId" label="用户ID" align="center" width="130"></el-table-column>
      <el-table-column prop="orderStatus" label="商品状态" width="130" align="center">
         <template slot-scope="scope">
          <span v-if="scope.row.orderStatus == 1"><el-tag>未支付</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 2"><el-tag>用户取消</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 3"><el-tag>系统取消</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 4"><el-tag>已支款</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 5"><el-tag>申请退款</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 6"><el-tag>已退款</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 7"><el-tag>已发货</el-tag></span>
          <span v-else-if="scope.row.orderStatus == 8"><el-tag>用户发货</el-tag></span>
          <span v-else><el-tag>系统发货</el-tag></span>
          </template>
      </el-table-column>
      <el-table-column prop="orderPrice" width="130" label="订单金额" align="center"></el-table-column>
      <el-table-column prop="actualPrice" width="100" label="支付金额" align="center"></el-table-column>
      <el-table-column prop="shipSn" width="150" label="物流单号" align="center"></el-table-column>
      <el-table-column prop="shipChannel" label="物流渠道" align="center"></el-table-column>
      <el-table-column label="操作" width="200" prop="orderStatus" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="Viewdetails(scope.row)">查看详情</el-button>
          <el-button v-if="scope.row.orderStatus == 4" size="mini" type="primary" @click="delivergoods(scope.row)">发货</el-button>
          <el-button v-if="scope.row.orderStatus == 5" size="mini" type="primary" @click="refund(scope.row)">退款</el-button>
        </template>
      </el-table-column>
    </el-table> -->
    <template>
      <!-- 分页 -->
      <pagination
        :total="total"
        :page.sync="brandlist.pagenum"
        :limit.sync="brandlist.pagesize"
        @pagination="getList"
      />
      <!-- 退款 -->
       <el-dialog
        title="退款"
        :visible.sync="updataDig"
        width="30%"
        :before-close="updateSpecDig"
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
              <el-form-item prop="price" label="退款">
                <el-input :disabled="true" v-model="updataSpecData.price" />
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button @click="updateSpecDig">取 消</el-button>
          <el-button type="primary" @click="updateSubmitBtn()">确定</el-button>
        </span>
      </el-dialog>
      <!-- 发货 -->
      <el-dialog
        title="发货"
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
              <el-form-item prop="shipChannel" label="快递公司">
                <el-input v-model="addSpecData.shipChannel" />
              </el-form-item>
              <el-form-item prop="shipSn" label="快递编号">
                <el-input v-model="addSpecData.shipSn" />
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancelAddSpecDig">取 消</el-button>
          <el-button type="primary" @click="addSubmitBtn()">确定</el-button>
        </span>
      </el-dialog>
      <!-- 查看详情 -->
      <el-dialog
        title="查看详情"
        :visible.sync="seleteSpecDig"
        width="50%"
        :before-close="cancelSpecDig"
      >
   <el-form :model="shopping" status-icon ref="shopping" label-width="100px" class="demo-ruleForm">
      <el-form-item label="订单编号">
                <span>{{shopping.shopping1.orderSn}}</span>
              </el-form-item>
               <el-form-item label="订单状态">
                  <span v-if="shopping.shopping1.orderStatus == 1"><el-tag>未支付</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 2"><el-tag>用户取消</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 3"><el-tag>系统取消</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 4"><el-tag>已支款</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 5"><el-tag>申请退款</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 6"><el-tag>已退款</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 7"><el-tag>已发货</el-tag></span>
                  <span v-else-if="shopping.shopping1.orderStatus == 8"><el-tag>用户发货</el-tag></span>
                  <span v-else><el-tag>系统发货</el-tag></span>
              </el-form-item>
               <el-form-item label="订单用户">
                <span>{{shopping.shopping1.userId}}</span>
              </el-form-item>
               <el-form-item label="订单留言">
                <span>{{shopping.shopping1.message}}</span>
              </el-form-item>
              <el-form-item label="收获信息">
                <span>{{shopping.shopping1.message}}</span>
              </el-form-item>
              <el-form-item label="收获信息">
                <span>(收货人){{shopping.shopping1.consignee}}
                  (手机号){{shopping.shopping1.mobile}}
                  (地址){{shopping.shopping1.address}}
                  </span>
              </el-form-item>
             <el-form-item label="商品信息">
              </el-form-item>
                 <table
                    border = 1
                    style="width: 100%">
                     <tr>
                        <th>商品名称</th>
                        <th>商品编号</th>
                        <th>货品规格</th>
                        <th>货品价格</th>
                        <th>货品数量</th>
                        <th>货品图片</th>
                    </tr>
                     <tr>
                        <td>{{shopping.shopping2.goodsName}}</td>
                        <td>{{shopping.shopping2.goodsSn}}</td>
                        <td>{{shopping.shopping2.specifications}}</td>
                        <td>{{shopping.shopping2.price}}</td>
                        <td>{{shopping.shopping2.number}}</td>
                        <td><img :src="shopping.shopping2.picUrl" alt="" style="width:100px"></td>
                    </tr>
                  </table> 
              <el-form-item label="费用信息">
                <span>(实际费用){{shopping.shopping1.actualPrice}}
                  =(商品总价){{shopping.shopping1.goodsPrice}}
                  +(快递费用){{shopping.shopping1.freightPrice}}
                  -(优惠卷费用){{shopping.shopping1.couponPrice}}
                  -(积分减免){{shopping.shopping1.integralPrice}}
                  </span>
              </el-form-item>
                <el-form-item label="支付信息">
                <span>(支付渠道)微信支付
                  (支付时间){{shopping.shopping1.payTime}}
                  </span>
              </el-form-item>
                <el-form-item label="快递信息">
                <span>(快递公司){{shopping.shopping1.shipChannel}}
                  (快递单号){{shopping.shopping1.shipSn}}
                  (发货时间){{shopping.shopping1.shipTime}}
                  </span>
              </el-form-item>
              <el-form-item label="收获信息">
                <span>(确认收货时间){{shopping.shopping1.confirmTime}}
                  </span>
              </el-form-item>
          </el-form>
      </el-dialog>
    </template> -->
  </div>
</template>
<script>
import {orderlist,orderdetail,ordership,orderrefund} from "@/api/malls/order";
import Pagination from "@/components/Pagination";
export default {
  components: { Pagination },
  data() {
    return {
      updataDig:false,
      addSpecDig:false,
      seleteSpecDig:false,
      loading: true,
      total: 0,
      tableData: [],
      updataSpecData:{
        price:"",
        orderSn:"",
      },
      addSpecData:{
        orderSn:"",
        shipChannel:"",
        shipSn:""
      },
       rules: {
        shipChannel: [{ required: true, message: "快递公司不能为空", trigger: "blur" }],
        shipSn: [{ required: true, message: "快递编号不能为空", trigger: "blur" }],
      },
      //获取列表
      brandlist: {
        pagenum: 1,
        pagesize: 10,
        userId:"",
        orderSn:"",
        orderStatuslist:[]
      },
      shopping:{
        shopping1:{},
        shopping2:{},
        },
      //查看详情
      orderSn:{
        orderId:""
      },
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    getdata() {
      orderlist(this.brandlist)
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
    cancelSpecDig(){
      this.seleteSpecDig = false
    },
    //发货
    delivergoods(row){
      this.addSpecData.orderSn = {...row}.orderSn
      this.addSpecDig = true
    },
    addSubmitBtn(){
        ordership(this.addSpecData)
        .then(res => {
          console.log(res)
          if (res.data.responseCode == 0) {
              this.$notify({
              title: res.data.data,
              message: res.data.responseMsg,
              type: 'success'
            });   
            this.addSpecDig = false
            this.getdata()
          } else {
           this.$notify.error({
              title: res.data.data,
              message: res.data.responseMsg
            });
          }
        })
    },
    cancelAddSpecDig(){
       this.addSpecDig = false
    },
    updateSpecDig(){
      this.updataDig =false
    },
    updateSubmitBtn(){
        orderrefund(this.updataSpecData)
        .then(res => {
          if (res.data.responseCode == 0) {
             this.$notify({
              title: res.data.data,
              message: res.data.responseMsg,
              type: 'success'
            });
            this.getdata()
             this.updataDig = false
          } else {
           this.$notify.error({
              title: res.data.data,
              message: res.data.responseMsg
            });
          }
        })
    },
    //退款
    refund(row){
      this.updataDig = true
      this.orderSn.orderId = {...row}.id
      this.updataSpecData.orderSn ={...row}.orderSn
        orderdetail(this.orderSn)
        .then(res => {
          if (res.data.responseCode == 0) {
              this.updataSpecData.price = {...res.data.data.shoppingOrderGoods}.price
          } else {
           this.$notify.error({
              title: res.data.data,
              message: res.data.responseMsg
            });
          }
        })
    },
    cancelSpecDig(){
      this.seleteSpecDig = false
    },
    //查看详情
    Viewdetails(row){
      this.orderSn.orderId = {...row}.id
        orderdetail(this.orderSn)
        .then(res => {
          if (res.data.responseCode == 0) {
              this.shopping.shopping1 = {...res.data.data.shoppingOrder}
              this.shopping.shopping2 = {...res.data.data.shoppingOrderGoods}
              this.updataSpecData.orderSn = {...res.data.data.shoppingOrderGoods}.orderSn
              this.updataSpecData.price = {...res.data.data.shoppingOrderGoods}.price
              this.seleteSpecDig = true
          } else {
           this.$notify.error({
              title: res.data.data,
              message: res.data.responseMsg
            });
          }
        })
    },
    getList(pagenum) {
      this.brandlist.pagenum = pagenum.page;
      this.brandlist.pagesize = pagenum.limit;
      this.getdata();
    },
    //查找
    setectData() {
      this.getdata();
    },
  }
};
</script>

<style scoped>
.shousuo {
  float: right;
  margin-bottom: 20px;
}
</style>
