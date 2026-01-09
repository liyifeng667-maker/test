import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/formCategory/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/formCategory/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/formCategory/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  treeData: function () {
    return request({
      url: '/extension/formCategory/treeData',
      method: 'get'
    })
  }
}
