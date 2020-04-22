/**
 * 首页-全站基本信息获取
 */
import request from '@/utils/request'

/**
 * 获取基本信息
 * @param query
 */
export function info(data) {
  return request({
    url: '/dashboard/info',
    method: 'get',
    params: data
  })
}
