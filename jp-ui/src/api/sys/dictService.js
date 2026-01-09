import request from '@/utils/httpRequest'

export default {
  queryById: function (id) {
    return request({
      url: '/sys/dict/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  save: function (inputForm) {
    return request({
      url: '/sys/dict/save',
      method: 'post',
      data: inputForm
    })
  },

  list: function (params) {
    return request({
      url: '/sys/dict/type/list',
      method: 'get',
      params: params
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/dict/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryDictValue: function (id) {
    return request({
      url: '/sys/dict/queryDictValue',
      method: 'get',
      params: { dictValueId: id },
      loading: false
    })
  },

  saveDictValue: function (inputForm) {
    return request({
      url: '/sys/dict/saveDictValue',
      method: 'post',
      data: inputForm
    })
  },

  getDictValue: function (dictTypeId) {
    return request({
      url: '/sys/dict/getDictValue',
      method: 'get',
      params: {
        dictTypeId: dictTypeId
      }
    })
  },

  getDictMap: function (dictTypeId) {
    return request({
      url: '/sys/dict/getDictMap',
      method: 'get',
      params: {
        dictTypeId: dictTypeId
      }
    })
  },

  deleteDictValue: function (ids) {
    return request({
      url: '/sys/dict/deleteDictValue',
      method: 'delete',
      params: { ids: ids }
    })
  }
}
