import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/test/treetable/carKind/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/test/treetable/carKind/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/test/treetable/carKind/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  treeData: function () {
    return request({
      url: '/test/treetable/carKind/treeData',
      method: 'get'
    })
  }
}
