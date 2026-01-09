import request from '@/utils/httpRequest'

export default {
  list: function (params) {
    return request({
      url: '/flowable/process/list',
      method: 'get',
      params: params
    })
  },

  runningDataList: function (params) {
    return request({
      url: '/flowable/process/runningData',
      method: 'get',
      params: params
    })
  },

  historyListData: function (params) {
    return request({
      url: '/flowable/process/historyListData',
      method: 'get',
      params: params
    })
  },

  revokeProcIns: function (id) {
    return request({
      url: '/flowable/process/revokeProcIns',
      method: 'put',
      params: { id: id }
    })
  },

  deleteProcIns: function (ids, reason) {
    return request({
      url: '/flowable/process/deleteProcIns',
      method: 'delete',
      params: {
        ids: ids,
        reason: reason
      }
    })
  },

  deleteAllProcIns: function (ids) {
    return request({
      url: '/flowable/process/deleteAllProcIns',
      method: 'delete',
      params: { procInsIds: ids }
    })
  },

  suspend: function (procDefId) {
    return request({
      url: '/flowable/process/update/suspend',
      method: 'put',
      params: { procDefId: procDefId }
    })
  },

  active: function (procDefId) {
    return request({
      url: '/flowable/process/update/active',
      method: 'put',
      params: { procDefId: procDefId }
    })
  },

  stop: function (id, message) {
    return request({
      url: '/flowable/process/stop',
      method: 'put',
      params: { id: id, message: message }
    })
  },

  getFlowChart: function (processDefId) {
    return request({
      url: '/flowable/process/getFlowChart',
      method: 'get',
      params: { processDefId: processDefId }
    })
  },

  queryProcessStatus: function (procDefId, procInsId) {
    return request({
      url: '/flowable/process/queryProcessStatus',
      method: 'get',
      params: { procDefId: procDefId, procInsId: procInsId }
    })
  },

  exist: function (key) {
    return request({
      url: '/flowable/process/exist',
      method: 'get',
      params: { key: key }
    })
  }
}
