import request from '@/utils/httpRequest'

export default {
  list: function (params) {
    return request({
      url: '/sys/log/list',
      method: 'get',
      params: params
    })
  },

  mine: function (params) {
    return request({
      url: '/sys/log/data/mine',
      method: 'get',
      params: params
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/log/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  empty: function () {
    return request({
      url: '/sys/log/empty',
      method: 'delete'
    })
  }
}
