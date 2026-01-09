import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/test/manytomany/student/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/test/manytomany/student/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/test/manytomany/student/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  list: function (params) {
    return request({
      url: '/test/manytomany/student/list',
      method: 'get',
      params: params
    })
  },

  exportTemplate: function () {
    return request({
      url: '/test/manytomany/student/import/template',
      method: 'get',
      responseType: 'blob'
    })
  },

  exportExcel: function (params) {
    return request({
      url: '/test/manytomany/student/export',
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  },

  importExcel: function (data) {
    return request({
      url: '/test/manytomany/student/import',
      method: 'post',
      data: data
    })
  }
}
