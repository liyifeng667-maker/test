import request from '@/utils/httpRequest'

export default {
  delete: function (id) {
    return request({
      url: '/reports/delete',
      method: 'delete',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/reports/list',
      method: 'get',
      params: params
    })
  }
}
