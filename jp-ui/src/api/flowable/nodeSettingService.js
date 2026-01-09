import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/nodeSetting/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/nodeSetting/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryValueByKey: function (params) {
    return request({
      url: '/extension/nodeSetting/queryValueByKey',
      method: 'get',
      params: params
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/nodeSetting/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/extension/nodeSetting/list',
      method: 'get',
      params: params
    })
  }
}
