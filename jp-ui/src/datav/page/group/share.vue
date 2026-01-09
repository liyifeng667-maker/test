<template>
  <el-dialog title="分享"
             append-to-body
             :visible.sync="box"
             width="40%">
    <avue-form :option="option"
               v-model="form">
      <template slot="href"
                slot-scope="{}">
        <el-input v-model="form.href"
                  type="textarea"
                  row="3">
        </el-input>
        <a @click="handleView">查看大屏</a>
        &nbsp;&nbsp;
        <a @click="handleCopy">复制链接</a>
      </template>
    </avue-form>
  </el-dialog>
</template>

<script>
export default {
  inject: ['contain'],
  data () {
    return {
      box: false,
      form: {},
      visual: {},
      option: {
        emptyBtn: false,
				submitBtn: false,
        submitText: '分享大屏',
        size: 'small',
        span: 24,
        column: [{
          label: '大屏名称',
          prop: 'title'
        }, {
          label: '分享',
          prop: 'status',
          type: 'switch',
          dicData: [{
            label: '',
            value: 0
          }, {
            label: '',
            value: 1
          }]
        }, {
          label: '分享链接',
          type: 'textarea',
          prop: 'href'
        }]
      }
    }
  },
  methods: {
    handleShow () {
      this.$parent.handleBuild(undefined, false)
      this.box = true
      // let visual = this.contain.visual
      // this.visual = visual
      // this.form.title = visual.title
      // this.form.status = visual.status
			// this.form.href = location.origin + "/datav.html#/view/" + this.visual.id;
    },
    handleView () {
      let routeUrl = this.$router.resolve({
        path: '/view/' + this.visual.id
      })
      window.open(routeUrl.href, '_blank')
    },
    handleCopy () {
      this.$Clipboard({
        text: this.visual.href
      }).then(() => {
        this.$message.success('链接复制成功')
      })
    }
    // handleSubmit (form, done) {
    //   updateObj({
    //     id: this.visual.id,
    //     category: this.visual.category,
    //     password: this.form.password,
    //     status: this.form.status,
    //     title: this.form.title
    //   }).then(() => {
    //     this.contain.visual = Object.assign(this.contain.visual, this.form)
    //     done();
    //     this.box = false
    //     this.$message.success('大屏分享成功');
    //   })
    // }
  }
}
</script>

<style>
</style>