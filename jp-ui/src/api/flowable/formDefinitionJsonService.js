import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/formDefinitionJson/save',
      method: 'post',
      data: inputForm
    })
  },

  updatePrimary: function (id) {
    return request({
      url: '/extension/formDefinitionJson/updatePrimary',
      method: 'put',
      params: { id: id }
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/formDefinitionJson/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/formDefinitionJson/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/extension/formDefinitionJson/list',
      method: 'get',
      params: params
    })
  }
}
