import request from '@/utils/httpRequest'

export default {
  models: function (data) {
    return request({
      url: '/rest/models',
      method: 'post',
      data: data
    })
  },

  editorJson: function (modelId) {
    return request({
      url: `/rest/models/${modelId}/editor/json?version=${new Date().getTime()}`,
      method: 'get'
    })
  }
}
