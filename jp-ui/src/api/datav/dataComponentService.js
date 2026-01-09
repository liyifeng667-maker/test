import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/datav/dataComponent/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/datav/dataComponent/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/datav/dataComponent/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  list: function (params) {
    return request({
      url: '/datav/dataComponent/list',
      method: 'get',
      params: params
    })
  },

  exportTemplate: function () {
    return request({
      url: '/datav/dataComponent/import/template',
      method: 'get',
      responseType: 'blob'
    })
  },

  exportExcel: function (params) {
    return request({
      url: '/datav/dataComponent/export',
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  },

  importExcel: function (data) {
    return request({
      url: '/datav/dataComponent/import',
      method: 'post',
      data: data
    })
  }
}
