import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/mail/compose/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/mail/compose/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/mail/compose/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/mail/compose/list',
      method: 'get',
      params: params
    })
  }
}
