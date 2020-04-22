import request from '@/utils/request'
//上传
export function listGoods(query) {
    return request({
      url: '/goods/list',
      method: 'get',
      params: query
    })
}

export function deleteGoods(data) {
  return request({
    url: '/goods/delete',
    method: 'post',
    data
  })
}