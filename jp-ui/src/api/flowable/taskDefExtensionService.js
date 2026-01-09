import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/taskDefExtension/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/taskDefExtension/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/extension/taskDefExtension/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  queryByDefIdAndTaskId: function (params) {
    return request({
      url: '/extension/taskDefExtension/queryByDefIdAndTaskId',
      method: 'get',
      params: params
    })
  },

  list: function (params) {
    return request({
      url: '/extension/taskDefExtension/list',
      method: 'get',
      params: params
    })
  }
}
