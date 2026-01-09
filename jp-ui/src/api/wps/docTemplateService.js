import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/wps/docTemplate/save',
      method: 'post',
      data: inputForm
    })
  },

  createFile: function (type) {
    return request({
      url: '/weboffice/new/url',
      method: 'get',
      params: { type: type }
    })
  },

  delete: function (ids) {
    return request({
      url: '/wps/docTemplate/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/wps/docTemplate/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/wps/docTemplate/list',
      method: 'get',
      params: params
    })
  }
}
