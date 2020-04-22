import request from '@/utils/request'
//查看订单管理
export function orderlist(query) {
    return request({
      url: '/order/list',
      method: 'get',
      params: query
    })
  } 
  //查看详情
  export function orderdetail(data) {
    return request({
      url: '/order/detail',
      method: 'post',
      data
    })
  }
  //发货
  export function ordership(data) {
    return request({
      url: '/order/ship',
      method: 'post',
      data
    })
  }
  //退款
  export function orderrefund(data) {
    return request({
      url: '/order/refund',
      method: 'post',
      data
    })
  }