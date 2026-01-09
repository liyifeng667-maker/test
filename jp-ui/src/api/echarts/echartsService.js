import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/echarts/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/echarts/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/echarts/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  mergeChartData: function (params) {
    return request({
      url: '/echarts/mergeChartData',
      method: 'get',
      params: params
    })
  },

  queryDesignById: function (id) {
    return request({
      url: '/echarts/queryDesignById',
      method: 'get',
      params: { id: id }
    })
  },

  getChartData: function (id) {
    return request({
      url: `/echarts/getChartData/${id}`,
      method: 'get'
    })
  },
  list: function (params) {
    return request({
      url: '/echarts/list',
      method: 'get',
      params: params
    })
  }
}
