import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/database/datamodel/dataSet/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/database/datamodel/dataSet/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },
  getData: function (id) {
    return request({
      url: `/database/datamodel/dataSet/getData/${id}/json`,
      method: 'get'
    })
  },
  queryById: function (id) {
    return request({
      url: '/database/datamodel/dataSet/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  getMeta: function (params) {
    return request({
      url: '/database/datamodel/dataSet/getMeta',
      method: 'get',
      headers: { arrayFormat: 'brackets' },
      params: params
    })
  },

  exec: function (params) {
    return request({
      url: '/database/datamodel/dataSet/exec',
      method: 'get',
      headers: { arrayFormat: 'brackets' },
      params: params
    })
  },

  list: function (params) {
    return request({
      url: '/database/datamodel/dataSet/list',
      method: 'get',
      params: params
    })
  }
}
