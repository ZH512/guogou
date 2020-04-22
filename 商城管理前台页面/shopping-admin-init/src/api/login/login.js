/**
 * 用户登录，获取当前登录用户信息，注销接口
 */
import request from '@/utils/request'

/**
 * 登录接口
 * @param username 用户名
 * @param password 密码
 */
export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

/**
 * 用户注销接口
 */
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}

/**
 * 获取用户的信息，包括权限，用户名，头像路径等
 * @param token 当前登录用户的token
 */
export function getUserInfo(token) {
  return request({
    url: '/auth/info',
    method: 'get',
    params: { token }
  })
}

