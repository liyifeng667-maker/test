import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/datav/dataScreen/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/datav/dataScreen/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/datav/dataScreen/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  copy: function (id) {
    return request({
      url: '/datav/dataScreen/copy',
      method: 'get',
      params: { id: id }
    })
  },

  sqlQuery: function (inputForm) {
    return request({
      url: '/datav/dataScreen/sqlQuery',
      method: 'post',
      data: inputForm
    })
  },

  list: function (params) {
    return request({
      url: '/datav/dataScreen/list',
      method: 'get',
      params: params
    })
  }
}
