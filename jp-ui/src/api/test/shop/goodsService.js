import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/test/shop/goods/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/test/shop/goods/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/test/shop/goods/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  list: function (params) {
    return request({
      url: '/test/shop/goods/list',
      method: 'get',
      params: params
    })
  },

  exportTemplate: function () {
    return request({
      url: '/test/shop/goods/import/template',
      method: 'get',
      responseType: 'blob'
    })
  },

  exportExcel: function (params) {
    return request({
      url: '/test/shop/goods/export',
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  },

  importExcel: function (data) {
    return request({
      url: '/test/shop/goods/import',
      method: 'post',
      data: data
    })
  }
}
