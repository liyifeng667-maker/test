import request from '@/utils/httpRequest'

export default {
  save: function (inputForm) {
    return request({
      url: '/sys/role/save',
      method: 'post',
      data: inputForm
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/role/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/sys/role/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  validateNotExist: function (obj) {
    return request({
      url: '/sys/role/validateNotExist',
      method: 'get',
      params: obj
    })
  },

  list: function (params) {
    return request({
      url: '/sys/role/list',
      method: 'get',
      params: params
    })
  },
  assign: function (params) {
    return request({
      url: '/sys/role/assign',
      method: 'get',
      params: params
    })
  },

  assignAuthorityToRole: function (inputForm) {
    return request({
      url: '/sys/role/assignAuthorityToRole',
      method: 'post',
      data: inputForm
    })
  },

  removeUserFromRole: function (userId, roleId) {
    return request({
      url: '/sys/role/removeUserFromRole',
      method: 'delete',
      params: { userId: userId, roleId: roleId }
    })
  },

  addUserToRole: function (roleId, userIds) {
    return request({
      url: '/sys/role/addUserToRole',
      method: 'put',
      params: {
        roleId: roleId,
        userIds: userIds
      }
    })
  }
}
