import request from '@/utils/httpRequest'

export default {
  getConfig: function () {
    return request({
      url: '/sys/sysConfig/getConfig',
      method: 'get'
    })
  },

  queryById: function () {
    return request({
      url: '/sys/sysConfig/queryById',
      method: 'get'
    })
  },

  save: function (inputForm) {
    return request({
      url: '/sys/sysConfig/save',
      method: 'post',
      data: inputForm
    })
  }
}
