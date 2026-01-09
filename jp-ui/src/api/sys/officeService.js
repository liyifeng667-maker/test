import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/sys/office/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/office/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/sys/office/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  treeData: function (params) {
    return request({
      url: '/sys/office/treeData',
      method: 'get',
      params: params
    })
  }
}
