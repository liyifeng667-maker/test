import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/datav/dataMap/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/datav/dataMap/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/datav/dataMap/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/datav/dataMap/list',
      method: 'get',
      params: params
    })
  }
}
