import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/extension/flowCopy/save',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/extension/flowCopy/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  list: function (params) {
    return request({
      url: '/extension/flowCopy/list',
      method: 'get',
      params: params
    })
  }
}
