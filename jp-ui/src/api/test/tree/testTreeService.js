import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/test/tree/testTree/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/test/tree/testTree/delete',
      method: 'delete',
      params: {ids: ids}
    })
  },

  queryById: function (id) {
    return request({
      url: '/test/tree/testTree/queryById',
      method: 'get',
      params: {id: id}
    })
  },

  treeData: function () {
    return request({
      url: '/test/tree/testTree/treeData',
      method: 'get'
    })
  }
}
