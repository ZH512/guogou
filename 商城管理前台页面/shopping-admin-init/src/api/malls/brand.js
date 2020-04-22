import request from '@/utils/request'
//品牌制造商
export function brandlist(data) {
    return request({
      url: '/brand/list',
      method: 'post',
      data
    })
  }
  
  //添加品牌制造商
  export function brandadd(data) {
    return request({
      url: '/brand/create',
      method: 'post',
      data
    })
  }
  
  //修改品牌制造商
  export function brandupdate(data) {
    return request({
      url: '/brand/update',
      method: 'post',
      data
    })
  }
  
  //删除品牌制造商
  export function deleteadd(data) {
    return request({
      url: '/brand/delete',
      method: 'post',
      data
    })
  }

  //添加图片
export function storagecreate(data) {
    return request({
      url: '/storage/create',
      method: 'post',
      data
    })
  }