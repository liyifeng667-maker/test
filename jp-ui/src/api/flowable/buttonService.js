import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/button/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/button/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/button/queryById',
      method: 'get',
      params: { id: id }
    })
  },
  validateCodeNoExist: function (params) {
    return request({
      url: '/extension/button/validateCodeNoExist',
      method: 'get',
      params: params
    })
  },

  validateNameNoExist: function (params) {
    return request({
      url: '/extension/button/validateNameNoExist',
      method: 'get',
      params: params
    })
  },

  list: function (params) {
    return request({
      url: '/extension/button/list',
      method: 'get',
      params: params
    })
  }
}
