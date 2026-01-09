import request from '@/utils/httpRequest'

export default {
  treeData: function (extId) {
    return request({
      url: '/extension/actCategory/treeData',
      method: 'get',
      params: { extId: extId }
    })
  },

  save: function (inputForm) {
    return request({
      url: '/extension/actCategory/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/actCategory/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/actCategory/queryById',
      method: 'get',
      params: { id: id }
    })
  }
}
