import request from '@/utils/request'

  export function detailGoods(id) {
    return request({
      url: '/goods/detail',
      method: 'get',
      params: { id }
    })
  }

  export function editGoods(data) {
    return request({
      url: '/goods/update',
      method: 'post',
      data
    })
  }
  export function listCatAndBrand() {
    return request({
      url: '/goods/catAndBrand',
      method: 'get'
    })
  }

  export function createStorage(data) {
    return request({
      url: '/storage/create',
      method: 'post',
      data
    })
  }

  const uploadPath = process.env.BASE_API + '/storage/create'
  export { uploadPath }