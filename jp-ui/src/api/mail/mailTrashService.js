import request from '@/utils/httpRequest'

export default {
  delete: function (ids) {
    return request({
      url: '/mail/trash/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/mail/trash/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/mail/trash/list',
      method: 'get',
      params: params
    })
  }
}
