import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/database/datalink/dataSource/save',
      method: 'post',
      data: inputForm
    })
  },

  test: function (inputForm) {
    return request({
      url: '/database/datalink/dataSource/test',
      method: 'post',
      data: inputForm
    })
  },

  checkEnName: function (oldEnName, enName) {
    return request({
      url: '/database/datalink/dataSource/checkEnName',
      method: 'get',
      params: {
        oldEnName: oldEnName,
        enName: enName
      }
    })
  },

  delete: function (ids) {
    return request({
      url: '/database/datalink/dataSource/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/database/datalink/dataSource/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  list: function (params) {
    return request({
      url: '/database/datalink/dataSource/list',
      method: 'get',
      params: params
    })
  },

  treeData: function () {
    return request({
      url: '/database/datalink/dataSource/treeData',
      method: 'get'
    })
  }
}
