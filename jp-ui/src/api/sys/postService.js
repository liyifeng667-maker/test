import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/sys/post/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/post/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/sys/post/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/sys/post/list',
      method: 'get',
      params: params
    })
  }
}
