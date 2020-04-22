import request from '@/utils/request'

//行政区域
export function regionlist() {
    return request({
      url: '/region/list',
      method: 'post'
    })
  }