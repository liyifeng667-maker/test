import request from '@/utils/httpRequest'
import jsencrypt from '@/utils/jsencrypt.js'

export default {
  save: function (inputForm) {
    var publicKey = window.localStorage.getItem('publicKey')
    var aesParam = jsencrypt.encrypt(JSON.stringify(inputForm), publicKey)
 //   aesParam = 'iIH6O/74zsh9ORzo+q+qgglpgjD1R9D/HSiAcjObnW2mpbZUYPsFFDYLIfYe734WFLuv5aGcO7L5o/pI7N7XQcoK70dqjYw40jrfsGcXQM5lqx02MSvtkCv/Lv4ELqgWlJufvbbczVCUWaSFPHoL78Ab7tdBTnq/Mbk+0yc9Hyz/dwtxx7jiedNtg0miX8VWLKhfddOm7WppTszffRdKJV7sfW71K8s+8ndIhn3F8lLC7qzFoPwBz+q6uGLbv7/hz34qjlAbgxIrdvEt4Sz4mYBqOwX+z6Q7wjoXDmZ4wZ0q4+/1ZSunUxiglc12ZI3T/DpP0CILR6RoMaHv7fuoUM6y1b8X1Mdsx76XCphSMiOj0RCyoIu0mHWIvFbemCitfn3KHamXxvt8cv8hMZvyWcrDviOQZCuFp192sEOyl16npRvxM87gHq5Jhw2OZ5zPgoQniUqgS6QiSdL0BilWLKe0FioIIecDRYHvYO1q+phy2yte/nnpcjZHqDtxvhGRc4SUehno9MyVg/TID4rGskMyveysI9o+NuChf7jPRHjUO50ugHfyBzn4yQKulBgNM/w651GZ0Dnsa7wyQsUTwXmwTAIYL6pfhaK89NOKeSw0JS9qxnv0PM2PKDbemBoeOQAvlGIQRFOMOTDo3x+JS4Ez/bcpm/wi8kMnFL8QWW4='
    inputForm.oldLoginName = ''
    inputForm.newPassword = ''
    inputForm.confirmNewPassword = ''
    inputForm.aesParam = aesParam
    return request({
      url: '/sys/user/save',
      method: 'post',
      headers: { arrayFormat: 'repeat' },
      data: inputForm
    })
  },

  saveInfo: function (inputForm) {
    var publicKey = window.localStorage.getItem('publicKey')
    var str = JSON.stringify(inputForm)
    var param = {'aesParam': jsencrypt.encrypt(str, publicKey)}
    console.log(JSON.stringify(param))
    var xx = jsencrypt.decrypt(param.aesParam, '')
    console.log(xx)
    return request({
      url: '/sys/user/saveInfo',
      method: 'post',
      headers: { arrayFormat: 'repeat' },
      data: param
    })
  },

  savePwd: function (inputForm) {
    var publicKey = window.localStorage.getItem('publicKey')
    var param = {'aesParam': jsencrypt.encrypt(JSON.stringify(inputForm), publicKey)}
    return request({
      url: '/sys/user/savePwd',
      method: 'put',
      params: param
    })
  },

  delete: function (ids) {
    return request({
      url: '/sys/user/delete',
      method: 'delete',
      params: { ids: ids }
    })
  },

  queryById: function (id) {
    return request({
      url: '/sys/user/queryById',
      method: 'get',
      params: { id: id }
    })
  },

  getMenus: function () {
    return request({
      url: '/sys/user/getMenus',
      method: 'get'
    })
  },

  info: function () {
    return request({
      url: '/sys/user/info',
      method: 'get'
    })
  },

  list: function (params) {
    return request({
      url: '/sys/user/list',
      method: 'get',
      params: params
    })
  },
  exportTemplate: function () {
    return request({
      url: '/sys/user/import/template',
      method: 'get',
      responseType: 'blob'
    })
  },

  exportExcel: function (params) {
    return request({
      url: '/sys/user/export',
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  },

  importExcel: function (data) {
    return request({
      url: '/sys/user/import',
      method: 'post',
      data: data
    })
  }
}
