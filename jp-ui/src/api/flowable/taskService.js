import request from '@/utils/httpRequest'

export default {
  start: function (data) {
    return request({
      url: '/flowable/task/start',
      method: 'post',
      data: data
    })
  },

  todoList: function (params) {
    return request({
      url: '/flowable/task/todo',
      method: 'get',
      params: params
    })
  },

  historicList: function (params) {
    return request({
      url: '/flowable/task/historic',
      method: 'get',
      params: params
    })
  },

  historicTaskList: function (procInsId) {
    return request({
      url: '/flowable/task/historicTaskList',
      method: 'get',
      params: { procInsId: procInsId }
    })
  },

  myApplyedList: function (params) {
    return request({
      url: '/flowable/task/myApplyed',
      method: 'get',
      params: params
    })
  },

  getTaskDef: function (params) {
    return request({
      url: '/flowable/task/getTaskDef',
      method: 'get',
      params: params
    })
  },

  delegate: function (taskId, userId) {
    return request({
      url: '/flowable/task/delegate',
      method: 'put',
      params: { taskId: taskId, userId: userId }
    })
  },

  callback: function (params) {
    return request({
      url: '/flowable/task/callback',
      method: 'put',
      params: params
    })
  },

  audit: function (data) {
    return request({
      url: '/flowable/task/audit',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: data
    })
  },

  backNodes: function (taskId) {
    return request({
      url: '/flowable/task/backNodes',
      method: 'put',
      params: { taskId: taskId }
    })
  },

  back: function (params) {
    return request({
      url: '/flowable/task/back',
      method: 'put',
      params: params
    })
  },

  transfer: function (taskId, userId) {
    return request({
      url: '/flowable/task/transfer',
      method: 'put',
      params: { taskId: taskId, userId: userId }
    })
  },

  addSignTask: function (data) {
    return request({
      url: '/flowable/task/addSignTask',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: data
    })
  },
  getFlowChart: function (processInstanceId) {
    return request({
      url: '/flowable/task/getFlowChart',
      method: 'get',
      params: { processInstanceId: processInstanceId }
    })
  },

  urge: function (data) {
    return request({
      url: '/flowable/task/urge',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      data: data
    })
  }
}
