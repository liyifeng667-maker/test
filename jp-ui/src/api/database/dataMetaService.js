import request from '@/utils/httpRequest'

export default {
  queryNeedByDataSetId: function (id) {
    return request({
      url: '/database/datamodel/dataMeta/queryNeedByDataSetId',
      method: 'get',
      params: { id: id }
    })
  }
}
