<template>
<div>
  <el-dialog
    :title="title"
    :close-on-click-modal="false"
     v-dialogDrag
    :visible.sync="visible">
<el-form :model="inputForm" size="small" ref="inputForm"
         v-loading="loading"
         :class="method==='view'?'readonly':''"
         :disabled="method==='view'"
         label-width="100px">

  <!-- 档案头部 -->
  <div class="archive-header">


    <div class="base-info">
      <el-row :gutter="15">
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="inputForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="inputForm.gender" style="width:100%">
              <el-option v-for="item in $dictUtils.getDictList('lf_gender')"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="年龄" prop="age">
            <el-input v-model="inputForm.age"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工号" prop="jobNo">
            <el-input v-model="inputForm.jobNo"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="身份" prop="identityType">
            <el-select v-model="inputForm.identityType" style="width:100%">
              <el-option v-for="item in $dictUtils.getDictList('lf_identity_type')"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="准入状态" prop="accessStatus">
            <el-select v-model="inputForm.accessStatus" style="width:100%">
              <el-option v-for="item in $dictUtils.getDictList('lf_access_status')"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </div>
      <div class="avatar-box">
      <image-select v-model="inputForm.avatarPath"></image-select>
      <div class="avatar-tip">人脸照片</div>
    </div>
  </div>

  <!-- 分割线 -->
  <el-divider content-position="center">其他信息</el-divider>

  <!-- 其他信息 -->
  <el-row :gutter="15">
    <el-col :span="12">
      <el-form-item label="身份证" prop="idCard">
        <el-input v-model="inputForm.idCard"></el-input>
      </el-form-item>
    </el-col>
    <el-col :span="12">
      <el-form-item label="手机号" prop="phoneNumber">
        <el-input v-model="inputForm.phoneNumber"></el-input>
      </el-form-item>
    </el-col>

    <el-col :span="12">
      <el-form-item label="所属公司" prop="company">
        <el-input v-model="inputForm.company"></el-input>
      </el-form-item>
    </el-col>
    <el-col :span="12">
      <el-form-item label="项目" prop="projectId">
        <el-input v-model="inputForm.projectId"></el-input>
      </el-form-item>
    </el-col>

    <el-col :span="12">
      <el-form-item label="考试成绩ID" prop="examScoreId">
        <el-input v-model="inputForm.examScoreId"></el-input>
      </el-form-item>
    </el-col>

    <el-col :span="12">
      <el-form-item label="出生年月" prop="birthday">
        <el-date-picker
          style="width:100%"
          v-model="inputForm.birthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"/>
      </el-form-item>
    </el-col>
  </el-row>

</el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="small" @click="visible = false">关闭</el-button>
      <el-button size="small" type="primary" v-if="method != 'view'" @click="doSubmit()" v-noMoreClick>确定</el-button>
    </span>
  </el-dialog>
</div>
</template>

<script>
  import personBankService from '@/api/zhgd/personBankService'
  export default {
    data () {
      return {
        title: '',
        method: '',
        visible: false,
        loading: false,
        inputForm: {
          id: '',
          name: '',
          age: '',
          gender: '',
          jobNo: '',
          idCard: '',
          identityType: '',
          accessStatus: '',
          avatar: '',
          avatarPath: '',
          company: '',
          projectId: '',
          examScoreId: '',
          birthday: '',
          phoneNumber: ''
        }
      }
    },
    components: {
    },
    methods: {
      init (method, id) {
        this.method = method
        this.inputForm.id = id
        if (method === 'add') {
          this.title = `新建人员库`
        } else if (method === 'edit') {
          this.title = '修改人员库'
        } else if (method === 'view') {
          this.title = '查看人员库'
        }
        this.visible = true
        this.loading = false
        this.$nextTick(() => {
          this.$refs.inputForm.resetFields()
          if (method === 'edit' || method === 'view') { // 修改或者查看
            this.loading = true
            personBankService.queryById(this.inputForm.id).then(({data}) => {
              this.inputForm = this.recover(this.inputForm, data)
              this.loading = false
            })
          }
        })
      },
      // 表单提交
      doSubmit () {
        this.$refs['inputForm'].validate((valid) => {
          if (valid) {
            this.loading = true
            personBankService.save(this.inputForm).then(({data}) => {
              this.visible = false
              this.$message.success(data)
              this.$emit('refreshDataList')
              this.loading = false
            }).catch(() => {
              this.loading = false
            })
          }
        })
      }
    }
  }
</script>
<style>
  .archive-header {
  display: flex;
  padding: 10px 10px 0;
}

.avatar-box {
  width: 160px;
  text-align: center;
  margin-left: 20px;
}

.avatar-box .avatar-tip {
  margin-top: 8px;
  font-size: 12px;
  color: #999;

}

.base-info {
  flex: 1;
}

.readonly .el-input__inner,
.readonly .el-textarea__inner {
  border: none;
  background: #f5f7fa;
}
</style>

