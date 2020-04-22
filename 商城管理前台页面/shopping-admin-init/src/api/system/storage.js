/**
 * 对象存储的接口
 */
import request from '@/utils/request'

/**
 * 查询对象存储列表
 * @param data
 */
export function listStorage(data) {
  return request({
    url: '/storage/list',
    method: 'get',
    params: data
  })
}

//上传
export function addStorage(data) {
    return request({
      url: '/storage/create',
      method: 'post',
      data
    })
}

//删除
export function deleteStorage(data) {
    return request({
      url: '/storage/delete',
      method: 'post',
      data
    })
}