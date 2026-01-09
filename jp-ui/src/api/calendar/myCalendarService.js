import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/myCalendar/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (id) {
    return request({
      url: '/myCalendar/del',
      method: 'delete',
      params: { id: id }
    })
  },

  queryById: function (id) {
    return request({
      url: '/myCalendar/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  drag: function (params) {
    return request({
      url: '/myCalendar/drag',
      method: 'put',
      params: params
    })
  },

  resize: function (params) {
    return request({
      url: '/myCalendar/resize',
      method: 'put',
      params: params
    })
  },

  list: function () {
    return request({
      url: '/myCalendar/findList',
      method: 'get'
    })
  }
}
