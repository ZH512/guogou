/**
 * 商品类目的接口
 */
import request from '@/utils/request'

/**
 * 查询商品类目列表
 * @param data
 */
export function listCategory(data) {
  return request({
    url: '/category/list',
    method: 'get',
    params: data
  })
}

//新增商品类目
export function addCategory(data) {
    return request({
      url: '/category/create',
      method: 'post',
      data
    })
}

//删除商品类目
export function deleteCategory(data) {
    return request({
      url: '/category/delete',
      method: 'post',
      data
    })
}

//修改商品类目
export function updateCategory(data) {
    return request({
      url: '/category/update',
      method: 'post',
      data
    })
}

//获取一级商品类目列表
export function l1(data) {
  return request({
    url: '/category/l1',
    method: 'post',
    data
  })
}