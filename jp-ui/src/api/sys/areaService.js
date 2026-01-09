import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/sys/area/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/area/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/sys/area/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  treeData: function (extId) {
    return request({
      url: '/sys/area/treeData',
      method: 'get',
      params: { extId: extId }
    })
  }
}
