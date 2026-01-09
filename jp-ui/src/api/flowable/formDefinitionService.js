import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/formDefinition/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/formDefinition/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/formDefinition/queryById',
      method: 'get',
      params: { id: id }
    })
  },
  queryByJsonId: function (jsonId) {
    return request({
      url: '/extension/formDefinition/queryByJsonId',
      method: 'get',
      params: { jsonId: jsonId }
    })
  },

  list: function (params) {
    return request({
      url: '/extension/formDefinition/list',
      method: 'get',
      params: params
    })
  }
}
