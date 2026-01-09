import request from '@/utils/httpRequest'

export default {
  upload: function (formData, config = {}) {
    return request({
      url: '/file/upload?uploadPath=userdir',
      method: 'post',
      config: config,
      data: formData,
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },

  download: function (params) {
    return request({
      url: '/file/download',
      method: 'get',
      params: params
    })
  },

  uploadFile: function (formData, config = {}) {
    return request({
      url: '/file/uploadFile?uploadPath=userdir',
      method: 'post',
      config: config,
      data: formData,
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },
  downloadFile: function (params) {
    return request({
      url: '/file/downloadFile',
      method: 'get',
      params: params
    })
  }
}
