<template>
  <el-dialog
    title="修改密码"
    :visible.sync="visible"
     v-dialogDrag
    :append-to-body="true">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px" @submit.native.prevent>
      <el-form-item label="账号">
        <span>{{ userName }}</span>
      </el-form-item>
      <el-form-item label="原密码" prop="password">
        <el-input type="password" size="small" v-model="dataForm.password"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" size="small" v-model="dataForm.newPassword"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" size="small" v-model="dataForm.confirmPassword"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="small" @click="visible = false" icon="el-icon-circle-close">关闭</el-button>
      <el-button size="small" type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {clearLoginInfo} from '@/utils'
  import userService from '@/api/sys/userService'
  export default {
    data () {
      let validateConfirmPassword = (rule, value, callback) => {
        if (this.dataForm.newPassword !== value) {
          callback(new Error('确认密码与新密码不一致'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        dataForm: {
          password: '',
          newPassword: '',
          confirmPassword: ''
        },
        dataRule: {
          password: [
            {required: true, message: '原密码不能为空', trigger: 'blur'}
          ],
          newPassword: [
            {required: true, message: '新密码不能为空', trigger: 'blur'},
            {validator: this.checkPassword, trigger: 'blur'}
          ],
          confirmPassword: [
            {required: true, message: '确认密码不能为空', trigger: 'blur'},
            {validator: validateConfirmPassword, trigger: 'blur'}
          ]
        }
      }
    },
    computed: {
      userName: {
        get () {
          return this.$store.state.user.name
        }
      },
      mainTabs: {
        get () {
          return this.$store.state.common.mainTabs
        },
        set (val) {
          this.$store.commit('common/updateMainTabs', val)
        }
      }
    },
    methods: {
      // 初始化
      init () {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            userService.savePwd({
              'oldPassword': this.dataForm.password,
              'newPassword': this.dataForm.newPassword
            }).then(({data}) => {
              this.$message({
                message: '修改成功, 请重新登录!',
                type: 'success',
                duration: 1500
              })
              this.visible = false
              this.$nextTick(() => {
                this.mainTabs = []
                clearLoginInfo()
                this.$router.replace({name: 'login'})
              })
            })
          }
        })
      },
             // 校验密码
      checkPassword (rule, value, callback) {
        this.level = []
        if (!value) {
          return callback(new Error('密码不能为空!'))
        }
        if (value.length < 8) {
          return callback(new Error('密码不少于8位!'))
        }
        if (value.length > 20) {
          return callback(new Error('密码不大于20位!'))
        }
      // 校验是数字
        const regex1 = /^\d+$/
      // 校验字母
        const regex2 = /^[A-Za-z]+$/
      // 校验符号
      // eslint-disable-next-line no-useless-escape
        const regex3 = /^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、]+$/
        if (regex1.test(value)) {
          this.level.push('low')
          return callback(new Error('设置8至20位包含大小写字母、数字、特殊符号的密码!'))
        } else if (regex2.test(value)) {
          this.level.push('low')
          return callback(new Error('设置8至20位包含大小写字母、数字、特殊符号的密码!'))
        } else if (regex3.test(value)) {
          this.level.push(new Error('low'))
          return callback(new Error('设置8至20位包含大小写字母、数字、特殊符号的密码!'))
        } else if (/^[A-Za-z\d]+$/.test(value)) {
          this.level.push('low')
          this.level.push('middle')
          return callback(new Error('设置8至20位包含大小写字母、数字、特殊符号的密码!'))
      // eslint-disable-next-line no-useless-escape
        } else if (/^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、\d]+$/.test(value)) {
          this.level.push('low')
          this.level.push('middle')
          return callback(new Error('设置8至20位包含大小写字母、数字、特殊符号的密码!'))
          // eslint-disable-next-line no-useless-escape
        } else if (/^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、A-Za-z]+$/.test(value)) {
          this.level.push('low')
          this.level.push('middle')
          return callback(new Error('设置8至20位包含大小写字母、数字、特殊符号的密码!'))
         // eslint-disable-next-line no-useless-escape
        } else if (/^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、A-Za-z\d]+$/.test(value)) {
          this.level.push('low')
          this.level.push('middle')
          this.level.push('high')
        }
        return callback()
      }
    }
  }
</script>

