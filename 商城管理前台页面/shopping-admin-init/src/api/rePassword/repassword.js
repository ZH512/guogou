/**
 * 修改用户密码的接口
 */
import request from '@/utils/request'

/**
 * 提交密码修改信息
 * @param data
 */
export function rePassword(data) {
  return request({
    url: '/profile/password',
    method: 'post',
    data
  })
}
