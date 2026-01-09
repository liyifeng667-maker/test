import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/form/make/save',
      method: 'post',
      data: inputForm
    })
  },

  saveFormSource: function (inputForm) {
    return request({
      url: '/form/make/saveFormSource',
      method: 'post',
      data: inputForm
    })
  },

  saveBasicInfo: function (inputForm) {
    return request({
      url: '/form/make/saveBasicInfo',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/form/make/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/form/make/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  getTableColumnList: function (params) {
    return request({
      url: '/form/make/getTableColumnList',
      method: 'get',
      params: params
    })
  },

  getTableList: function (params) {
    return request({
      url: '/form/make/getTableList',
      method: 'get',
      params: params
    })
  },

  list: function (params) {
    return request({
      url: '/form/make/list',
      method: 'get',
      params: params
    })
  },

  validateTableNoExist: function (params) {
    return request({
      url: '/form/make/validateTableNoExist',
      method: 'get',
      params: params
    })
  },

  validateKeyNoExist: function (params) {
    return request({
      url: '/form/make/validateKeyNoExist',
      method: 'get',
      params: params
    })
  },

  createMenu: function (inputForm) {
    return request({
      url: '/form/make/createMenu',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: inputForm
    })
  }
}
