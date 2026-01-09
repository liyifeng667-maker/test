import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/notify/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/notify/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/notify/queryById',
      method: 'get',
      params: { id: id }
    })
  },
  query: function (params) {
    return request({
      url: '/notify/queryById',
      method: 'get',
      params: params
    })
  },

  list: function (params) {
    return request({
      url: '/notify/list',
      method: 'get',
      params: params
    })
  }
}
