<template>
<div class="importdialog">
  <el-dialog :title="title" :close-on-click-modal="false" v-dialogDrag :visible.sync="visible" :width="'600px'">
    <el-form size="small" :model="importForm" ref="importForm" v-loading="loading"   :disabled="false" label-width="120px">
      <el-row  :gutter="15">
        <el-col :span="10" v-if="tplurl">
            <el-form-item label="模板文件" >
              <el-button  type="default" icon="el-icon-download" @click="downloadTpl()" size="small" style="width: 100px;">下载模板</el-button>
           </el-form-item>
        </el-col>
        <el-col :span="14">
           <el-form-item label="上传文件" prop="name" :rules="[{required: true, message:'上传文件不能为空'}]">
              <div v-if="importForm.name" class="import-name"><div class="import-name-text">{{importForm.name}}</div><i class="el-icon-close" @click="clearFile"></i></div>
              <el-button  v-else type="default" @click="selectFile" size="small" style="width: 100px;">选择文件</el-button>
              <input type="file" name="file" ref ="fileInput"  class="el-upload__input" @change="getFile">
           </el-form-item>
        </el-col>
        <el-col :span="24" v-if="tip">
            <div v-html="tip" class="tip" style="color:#A2A4A9;font-size:12px;margin: 0px 0px 5px;"></div>
        </el-col>
        <el-col :span="24" v-if="errMsg">
        	<div style="text-align:right;">
        	  <el-button size="mini" style="padding-top: 0px;padding-bottom: 0px;" type="text" v-clipboard:copy="errMsg.replace(/<br\/>/g, '\n')" v-clipboard:success="onCopy" v-clipboard:error="onError">
              拷贝错误信息
              </el-button>
        	</div>
            <div v-html="errMsg" style="color:red;font-size:12px;line-height: 1.5;"></div>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="small" @click="visible = false" icon="el-icon-circle-close" :disabled="loading">关闭</el-button>
      <el-button size="small" type="primary" @click="doSubmit()" icon="el-icon-circle-check" :disabled="loading">确定</el-button>
    </span>
  </el-dialog>
</div>
</template>
<script>
export default {
  props: {
    title: {
      type: String,
      default: '数据导入'
    },
    importurl: {
      type: String,
      default: ''
    },
    tplurl: {
      type: String,
      default: ''
    },
    tip: {
      type: String,
      default: ''
    },
    uppath: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      source: '',
      filename: '',
      filePath: '',
      visible: false,
      loading: false,
      errMsg: '',
      importForm: {
        name: ''
      },
      dowloadUrl: '',
      chunkSize: 1024 * 1024,
      simultaneousUploads: 5
    }
  },
  components: {
  },
  created () {

  },
  methods: {
    init () {
      this.$refs.fileInput && (this.$refs.fileInput.value = null)
      this.$refs.importForm && (this.$refs.importForm.clearValidate())
      Object.assign(this.$data, this.$options.data(), {visible: true})
    },
    close () {
      this.visible = false
    },
    downloadTpl () {
      this.$utils.download(this.tplurl)
    },
    selectFile () {
      this.$refs.fileInput.click()
    },
    getFile () {
      this.importForm.name = this.$refs.fileInput.files[0].name
      this.errMsg = ''
    },
    clearFile () {
      this.$refs.fileInput.value = null
      this.importForm.name = ''
      this.errMsg = ''
    },
      // 表单提交
    doSubmit (formBody) {
      this.errMsg = ''
      this.$refs['importForm'].validate((valid) => {
        if (valid) {
          if (this.importurl === '') {
            return
          }
          this.loading = true
          if (!formBody) {
              // let chunkFlag = this.chunkUpload()
              // // 如果分片上传了
              // if (chunkFlag) {
              //   return
              // }
            formBody = new FormData()
            formBody.append('file', this.$refs.fileInput.files[0])
          }
          this.$http({
            url: this.importurl,
            method: 'post',
            data: formBody,
            timeout: 30 * 60 * 1000
          }).then(({data}) => {
            this.loading = false
            this.$emit('refreshData')
            if (data.success) {
                // this.$message.success(data.success)
              this.visible = false
              if (data.msg) {
                this.$message.success(data.msg)
              } else {
                this.$message.success('导入成功')
              }
              if (data.filePath) {
                this.dowloadUrl = data.filePath
                sessionStorage.setItem('checkFilePath', this.dowloadUrl)
                console.log(this.dowloadUrl)
              }
            } else {
              if (data.download === 'download') {
                this.source = data.source
                this.filename = data.filename
                this.downfile(this.source, this.filename)
              }
              this.errMsg = data.msg || '导入失败'
            }
          }).catch(() => {
            this.loading = false
          })
        }
      })
      return this.dowloadUrl
    },
    chunkUpload () {
      let file = this.$refs.fileInput.files[0]
      let chunks = Math.ceil(file.size / this.chunkSize)
      if (chunks <= 2) {
        return false
      }
      var bproto = window.Blob.prototype
      var sliceName = 'slice'
      var sliceArr = ['slice', 'webkitSlice', 'mozSlice']
      for (var i = 0; i < sliceArr.length; i++) {
        if (bproto[sliceArr[i]]) {
          sliceName = sliceArr[i]
          break
        }
      }
      let identifier = this.$utils.getTimeUuid(25) + '-' + file.size + '-' + (file.relativePath || file.webkitRelativePath || file.fileName || file.name || '').replace(/[^0-9a-zA-Z_-]/img, '').substr(-20)
      let chunksArr = []
      for (var offset = 0; offset < chunks; offset++) {
          // eslint-disable-next-line one-var
        let startByte = offset * this.chunkSize, endByte = Math.min(file.size, (offset + 1) * this.chunkSize)
        var _data = new FormData()
        _data.append('chunkNumber', offset + 1)
        _data.append('chunkSize', this.chunkSize)
        _data.append('currentChunkSize', endByte - startByte)
        _data.append('totalSize', file.size)
        _data.append('filename', file.name)
        _data.append('totalChunks', chunks)
        _data.append('upload', file[sliceName](startByte, endByte, file.type), file.name)
        _data.append('identifier', identifier)
        chunksArr.push({status: 0, data: _data})
      }
      this.chunks = chunksArr
      this.uploadChunk()
      return true
    },
    uploadChunk () {
          // 上传中的
        // eslint-disable-next-line one-var
      var simultaneousUploads = this.simultaneousUploads, uploadingCount = this.chunks.filter(x => x.status === 1).length
      if (uploadingCount >= simultaneousUploads) {
        return
      }
      // 未上传的
      var unuploadList = this.chunks.filter(x => x.status === 0)
      for (var i = 0; i < (simultaneousUploads - uploadingCount) && i < unuploadList.length; i++) {
        var _this = this;
        (function (f) {
          f.status = 1
          _this.$http({
            url: '/sys/file/upload?target=/userfiles/' + _this.uppath,
            method: 'post',
            data: f.data
          }).then(({data}) => {
            if (data.success) {
              f.status = 2
              if (data.id) {
                var formBody = new FormData()
                formBody.append('importPath', data.id)
                _this.doSubmit(formBody)
                return
              }
            } else {
              f.status = 0
              _this.errMsg = data.msg || '导入失败'
              _this.loading = false
              return
            }
            _this.uploadChunk()
          }).catch(() => {
            f.status = 0
          })
        })(unuploadList[i])
      }
    },
    downfile (source, filename) {
      this.$utils.download('/sys/file/download', {source: source, filename: filename})
    },
    onCopy (e) {
      this.$message.success('复制成功!')
    },
      // 复制失败
    onError (e) {
      this.$message.success('复制失败!')
    }
  }
}
</script>
<style scoped>
.import-name{
	display: flex;
    align-items: center;
    justify-content: space-between;
}
.import-name-text{
	flex:1;
	width:0px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.import-name .el-icon-close {
    display: none;
}
.import-name:hover{
 	color:#409eff;
}
.import-name:hover .el-icon-close{
	display:inline;
}
.importdialog  >>> .el-dialog__body{
	padding-top: 10px;
	padding-bottom: 0px;
}
.importdialog .el-form-item{
	margin-bottom: 5px;
}
.importdialog >>> .tip div{
	padding-bottom:5px;
}
</style>
