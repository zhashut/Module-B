# Module_B
2022-金砖赛移动应用赛道样题

## 项目介绍

一款基于 android原生库搭配 okhttp网络请求库开发的 航班查询 app

## 如何运行app

1. 将项目 `clone` 至本地 `git clone https://github.com/zhashut/Module_B.git`

2. 使用 Android Studio 打开项目
3. 由于使用到 航班信息查询 接口，这里需要用到 `appkey`，请前往 `https://www.jisuapi.com/my/` 注册用户
4. 在首页搜索 **[航班查询](https://www.jisuapi.com/api/flight/)** 后点击 **申请数据** 后即可添加进 我的API 中
5. 将自己的 `appkey` 填写入项目的 `src/main/java/vip/zhonghui/b/utils/HttpUtil.java` 文件中的 `APP_KEY` 字段
6. 以上做完即可运行 app

