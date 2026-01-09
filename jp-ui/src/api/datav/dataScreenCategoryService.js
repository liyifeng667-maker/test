import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/datav/dataScreenCategory/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/datav/dataScreenCategory/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/datav/dataScreenCategory/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  treeData: function (extId) {
    return request({
      url: '/datav/dataScreenCategory/treeData',
      method: 'get',
      params: { extId: extId }
    })
  }
}
