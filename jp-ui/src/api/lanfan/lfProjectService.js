import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/lanfan/lfProject/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/lanfan/lfProject/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/lanfan/lfProject/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  list: function (params) {
    return request({
      url: '/lanfan/lfProject/list',
      method: 'get',
      params: params
    })
  },

  exportTemplate: function () {
    return request({
      url: '/lanfan/lfProject/import/template',
      method: 'get',
      responseType: 'blob'
    })
  },

  exportExcel: function (params) {
    return request({
      url: '/lanfan/lfProject/export',
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  },

  importExcel: function (data) {
    return request({
      url: '/lanfan/lfProject/import',
      method: 'post',
      data: data
    })
  }
}
