import request from '@/utils/request'

export function listCoupon(query) {
  return request({
    url: '/coupon/list',
    method: 'get',
    params: query
  })
}

export function createCoupon(data) {
  return request({
    url: '/coupon/create',
    method: 'post',
    data
  })
}
export function listUser(query) {
  return request({
    url: '/coupon/listuser',
    method: 'get',
    params: query
  })
}
export function readCoupon(query) {
  return request({
    url: '/coupon/read',
    method: 'get',
    params: query
  })
}

export function updateCoupon(data) {
  return request({
    url: '/coupon/update',
    method: 'post',
    data
  })
}

export function deleteCoupon(data) {
  return request({
    url: '/coupon/delete',
    method: 'post',
    data
  })
}