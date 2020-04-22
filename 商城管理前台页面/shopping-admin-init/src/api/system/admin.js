import request from '@/utils/request'

// 查询
export function listAdmin(query) {
  return request({
    url: '/admin/list',
    method: 'get',
    params: query
  })
}

// 新增
export function createAdmin(data) {
  return request({
    url: '/admin/create',
    method: 'post',
    data
  })
}

export function readminAdmin(data) {
  return request({
    url: '/admin/readmin',
    method: 'get',
    data
  })
}
// 修改
export function updateAdmin(data) {
  return request({
    url: '/admin/update',
    method: 'post',
    data
  })
}
// 删除
export function deleteAdmin(data) {
  return request({
    url: '/admin/delete',
    method: 'post',
    data
  })
}