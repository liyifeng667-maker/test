<template>
  <div class="progressbar">
    <div class="progressbar1">
      <div class="bar" :style="{ width: value1 }"></div>
      <div class="label" :style="{ left: value1 }">
        <span></span>{{ parseFloat(value).toFixed(2) }}%
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      value1: 0
    }
  },
  props: ['value'],

  created () {},
  mounted () {},
  computed: {},
  methods: {},
  watch: {
    value (newVal, oldVal) {
      this.value1 = newVal + '%'
    }
  }
}
</script>

<style scoped>
.progressbar {
  position: relative;
  display: block;
  width: 100%;
  height: 40px;
  padding: 10px 25px;
  border-radius: 16px;
  margin: 10px auto;
  -webkit-box-shadow: 0px 4px 4px -4px rgb(0 0 0 / 40%),
    0px -3px 3px -3px rgb(0 0 0 / 25%);
  box-shadow: 0px 4px 4px -4px rgb(0 0 0 / 40%),
    0px -3px 3px -3px rgb(0 0 0 / 25%);
}
.progressbar1 {
  position: absolute;
  display: block;
  content: "";
  width: calc(100% - 50px);
  height: 20px;
  top: 10px;
  left: 25px;
  border-radius: 20px;
  background: #dfdfdf;
}
.bar {
  position: absolute;
  display: block;
  width: 50%;
  height: 20px;
  top: 0;
  left: 0;
  background-color: #0077ffcc;
  border-radius: 20px;
  background-size: 3em 3em;
  background-image: linear-gradient(
    -45deg,
    transparent 0em,
    transparent 0.7em,
    #0077ff 0.9em,
    #0077ff 2.1em,
    transparent 2.1em,
    transparent 2.9em,
    #0077ff 3.1em
  );
  animation: ani 500ms infinite linear;
  -webkit-box-shadow: 0px 4px 4px -4px rgb(0 0 0 / 40%),
    0px -3px 3px -3px rgb(0 0 0 / 25%);
  box-shadow: 0px 4px 4px -4px rgb(0 0 0 / 40%),
    0px -3px 3px -3px rgb(0 0 0 / 25%);
}
@keyframes ani {
  0% {
    background-position: 0 0;
}
100% {
    background-position: 3em 0;
}
}

.label {
  position: absolute;
  display: block;
  width: 70px;
  height: 30px;
  line-height: 30px;
  top: 30px;
  left: 0;
  background-color: #0077ff;
  transform: translateX(-35px);
  /* overflow: hidden; */
  font-size: 14px;
  border-radius: 5px;
  color: white;
  text-align: center;
}
.label span {
  display: block;
  width: 0;
  height: 0;
  border: 10px solid #0077ff;
  position: absolute;
  top: -20px;
  left: 25px;
  border-top: 10px solid rgba(21, 255, 0, 0);
  border-left: 10px solid rgba(0, 17, 255, 0);
  border-right: 10px solid rgba(255, 0, 242, 0);
  border-bottom: 10px solid #0077ff;
}
</style>
