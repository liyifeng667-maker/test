import request from '@/utils/httpRequest'

export default {
  deploy: function (params) {
    return request({
      url: '/flowable/model/deploy',
      method: 'put',
      params: params
    })
  },
  updateCategory: function (params) {
    return request({
      url: '/flowable/model/updateCategory',
      method: 'put',
      params: params
    })
  },
  copy: function (id) {
    return request({
      url: '/flowable/model/copy',
      method: 'get',
      params: { id: id }
    })
  },

  getBpmnXml: function (id) {
    return request({
      url: '/flowable/model/getBpmnXml',
      method: 'get',
      params: { id: id }
    })
  },

  exportBpmnXml: function (id) {
    return request({
      url: '/flowable/model/exportBpmnXml',
      method: 'get',
      params: { id: id },
      responseType: 'blob'
    })
  },

  delete: function (ids) {
    return request({
      url: '/flowable/model/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  saveModel: function (modelId, data) {
    return request({
      url: `/flowable/model/saveModel/${modelId}`,
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: data
    })
  },

  list: function (params) {
    return request({
      url: '/flowable/model/list',
      method: 'get',
      params: params
    })
  }
}
