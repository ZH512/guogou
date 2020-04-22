import request from '@/utils/request'

export function publishGoods(data) {
    return request({
      url: '/goods/create',
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