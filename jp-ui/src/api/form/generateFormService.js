import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/form/generate/save',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: inputForm
    })
  },

  queryById: function (params) {
    return request({
      url: '/form/generate/queryById',
      method: 'get',
      params: params
    })
  },

  delete: function (params) {
    return request({
      url: '/form/generate/delete',
      method: 'delete',
      params: params
    })
  },

  list: function (params) {
    return request({
      url: '/form/generate/list',
      method: 'get',
      params: params
    })
  }
}
