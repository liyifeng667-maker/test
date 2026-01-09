import request from '@/utils/httpRequest'

export default {
  getCode: function () {
    return request({
      url: '/sys/getCode',
      method: 'get'
    })
  },
  login: function (data) {
    return request({
      url: '/sys/login',
      method: 'post',
      data: data
    })
  },
  logout: function () {
    return request({
      url: '/sys/logout',
      method: 'get'
    })
  }
}
