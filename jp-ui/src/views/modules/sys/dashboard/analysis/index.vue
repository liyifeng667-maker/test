<template>
    <div class="el-scrollbar__wrap">
      欢迎
    </div>
</template>

<script>
    export default {
      name: 'Analysis',
      components: {
    
      },
      data () {
        const rankList = []
        for (let i = 0; i < 7; i++) {
          rankList.push({
            name: '白鹭岛 ' + (i + 1) + ' 号店',
            total: 1234.56 - i * 100
          })
        }
        return {
    
        }
      },
      activated () {
    
      },
      watch: {
    
      },
      methods: {
        initValue () {
        }
    
      },
      mounted () {
    
      },
      destroyed () {
    
      }
    }
</script>

<style scoped lang="less">
    .analyze {
        
        .top-1 {
            top: 0.55rem;
        }
        .mt-0 {
            margin-top: 0;
        }

        .m-3 {
            margin-bottom: 0.75rem;
        }

        .right-0 {
            right: 200px;
        }
        .absolute {
            position: absolute;
        }
        .float-left {
            float: left;
        }
        .flex {
            display: flex;
        }
        .card-panel {
            height: 108px;
            cursor: pointer;
            font-size: 12px;
            position: relative;
            overflow: hidden;
            color: #666;
            background: #ffffff;
            box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
            border-color: rgba(0, 0, 0, .05);
            &:hover {
                .card-panel-icon-wrapper {
                    color: #fff;
                }
                .icon-sell {
                    background: #6778fa;
                }
                .icon-people {
                    background: #40c9c6;
                }
                .icon-location {
                    background: #36a3f7;
                }
                .icon-visit {
                    background: #f4516c;
                }
            }
            .icon-sell {
                color: #6778fa;
            }
            .icon-people {
                color: #40c9c6;
            }
            .icon-location {
                color: #36a3f7;
            }
            .icon-visit {
                color: #f4516c;
            }
            .card-panel-icon-wrapper {
                float: left;
                margin: 14px 0 0 14px;
                padding: 16px;
                transition: all 0.38s ease-out;
                border-radius: 6px;
            }
            .card-panel-icon {
                float: left;
                font-size: 48px;
            }
            .card-panel-description {
                float: right;
                font-weight: bold;
                margin: 26px;
                margin-left: 0px;
                .card-panel-text {
                    line-height: 18px;
                    color: rgba(0, 0, 0, 0.45);
                    font-size: 16px;
                    margin-bottom: 12px;
                }
                .card-panel-num {
                    font-size: 20px;
                }
            }
        }
    }
</style>
