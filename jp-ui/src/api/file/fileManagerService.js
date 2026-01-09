import request from '@/utils/httpRequest'

export default {

  getUrl: function (params) {
    return request({
      url: '/file/getUrl',
      method: 'get',
      params: params
    })
  }
}
