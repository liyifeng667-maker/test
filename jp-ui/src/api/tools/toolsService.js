import request from '@/utils/httpRequest'

export default {
  sendEmail: function (inputForm) {
    return request({
      url: '/tools/email/send',
      method: 'post',
      data: inputForm
    })
  },

  createTwoDimensionCode: function (params) {
    return request({
      url: '/tools/TwoDimensionCodeController/createTwoDimensionCode',
      method: 'get',
      params: params
    })
  }
}
