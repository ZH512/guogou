import request from '@/utils/request'

// 查询
export function listRole(query) {
  return request({
    url: '/role/list',
    method: 'get',
    params: query
  })
}

// 新增
export function createRole(data) {
  return request({
    url: '/role/create',
    method: 'post',
    data
  })
}

 
export function readRole(data) {
  return request({
    url: '/role/read',
    method: 'get',
    data
  })
}

// 编辑
export function updateRole(data) {
  return request({
    url: '/role/update',
    method: 'post',
    data
  })
}

// 删除
export function deleteRole(data) {
  return request({
    url: '/role/delete',
    method: 'post',
    data
  })
}
// 获取权限列表
export function getPermission(query) {
  return request({
    url: '/role/permissions',
    method: 'get',
    params: query
  })
}
// 修改详情
export function updatePermission(data) {
  return request({
    url: '/role/permissions',
    method: 'post',
    data
  })
}
// 获取角色列表
export function roleOptions(query) {
  return request({
    url: '/role/options',
    method: 'get',
    params: query
  })
}