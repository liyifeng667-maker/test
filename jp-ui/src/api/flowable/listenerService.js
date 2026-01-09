import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/listener/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/listener/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/listener/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/extension/listener/list',
      method: 'get',
      params: params
    })
  }
}
