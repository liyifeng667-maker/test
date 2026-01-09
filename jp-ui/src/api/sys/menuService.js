import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/sys/menu/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/menu/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/sys/menu/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  treeData: function (params) {
    return request({
      url: '/sys/menu/treeData',
      method: 'get',
      params: params
    })
  }
}
