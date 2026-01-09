import request from '@/utils/httpRequest'

export default {
  delete: function (ids) {
    return request({
      url: '/mail/box/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/mail/box/queryById',
      method: 'get',
      params: { id: id }
    })
  },
  queryStatus: function () {
    return request({
      url: '/mail/box/queryStatus',
      method: 'get'
    })
  },

  list: function (params) {
    return request({
      url: '/mail/box/list',
      method: 'get',
      params: params
    })
  }
}
