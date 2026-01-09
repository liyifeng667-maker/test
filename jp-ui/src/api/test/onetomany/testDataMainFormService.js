import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/test/onetomany/testDataMainForm/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/test/onetomany/testDataMainForm/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/test/onetomany/testDataMainForm/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  list: function (params) {
    return request({
      url: '/test/onetomany/testDataMainForm/list',
      method: 'get',
      params: params
    })
  },

  exportTemplate: function () {
    return request({
      url: '/test/onetomany/testDataMainForm/import/template',
      method: 'get',
      responseType: 'blob'
    })
  },

  exportExcel: function (params) {
    return request({
      url: '/test/onetomany/testDataMainForm/export',
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  },

  importExcel: function (data) {
    return request({
      url: '/test/onetomany/testDataMainForm/import',
      method: 'post',
      data: data
    })
  }
}
