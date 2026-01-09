import request from '@/utils/httpRequest'

export default {
  executeSql: function (tableName, dataSourceId) {
    return request({
      url: `/database/table/executeSql/${tableName}?dataSourceId=${dataSourceId}`,
      method: 'get'
    })
  },

  save: function (inputForm) {
    return request({
      url: '/gen/genTable/saveTableFromDB',
      method: 'post',
      data: inputForm
    })
  },

  queryAlter: function (dataSourceId, tableName) {
    return request({
      url: '/database/table/alter',
      method: 'get',
      params: { dataSourceId: dataSourceId, name: tableName }
    })
  },

  submitAlter: function (inputForm) {
    return request({
      url: '/database/table/alter/do',
      method: 'post',
      data: inputForm
    })
  },

  queryCreate: function (dataSourceId) {
    return request({
      url: '/database/table/create',
      method: 'get',
      params: { dataSourceId: dataSourceId }
    })
  },

  submitCreate: function (inputForm) {
    return request({
      url: '/database/table/create/do',
      method: 'post',
      data: inputForm
    })
  },

  list: function (params) {
    return request({
      url: '/database/table/list',
      method: 'get',
      params: params
    })
  },

  drop: function (tableName, dataSourceId) {
    return request({
      url: '/database/table/drop',
      method: 'delete',
      params: { tableName: tableName, dataSourceId: dataSourceId }
    })
  }
}
