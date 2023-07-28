# FinalProject

#### 作者
大二 南京大学 软件工程 211250126 秦含滋
#### 介绍
- 百度移动端大作业
- 项目运行图片可见此文档最后

#### 软件架构
软件架构说明
- 主页的 3 个`fragment`使用一个tab栏进行合格，优化了开始 3 个`activity`的不好实现
- 

#### 安装教程
- 使用android studio打开、构建、运行项目即可
- 注意安装sdk以及将sdk的路径改为本地路径

#### 使用说明
- 第一次构建运行项目，需要初始化数据库表 `详情见MainActivity.kt中`，否则数据库内容为空，主页也是空。
- 初始化数据库：依次初始化`News1`、`News2`、`Accounts`数据库。
- 首页 Home 中的 + 可以添加新闻，当然这里规定了`type == "置顶"`为不带图片的News，`type == "热点"`为带图片的News，添加的时候选择即可，并且支持打开相册选择图片。
- 天气页面则是仿照ios系统的天气页面布局来完成，使用到了当地当天的特定数据作为展示
- 首页 Home 中的搜索栏则是利用`webView`，结合传参来实现调用百度的搜索功能
- 首页 Video 中的每个视频的封面图片则是用的随机数据（从本地的三张图内随机选择，当然以后也可做相应拓展）
- 视频落地页也做了相应调整，由于事件以及知识等方面的限制，想模仿抖音的设计，却只能照猫画虎。不过也支持一些相应的互动。
- 不知是由于什么原因，网络图床、视频床的一些url在`VideoView`里面的兼容性并不是太好。项目虽然有3个视频的url，但是`VideoView`里只能正常打开一个，向其他同学询问了一下发现他们也是如此，便不再深究了。
- 首页 User 支持注册登录，以及登录用户登出、编辑个人信息等功能。
- 在注册和编辑个人信息的同时，增加了对于用户名、密码、电话等的约束条件以及提示，这也符合日常人们使用软件的习惯。
- 在一些页面相互退出跳转的地方，笔者也做了一些小小的优化，算是一些有心之举吧。

#### 参与贡献
- 本项目的完成一定程度上参考了[bilibili-2023年百度移动端实战训练营](https://www.bilibili.com/video/BV1KW4y1o7sw/?spm_id_from=333.788&vd_source=21694b6c9f9a10f279227c0070e4ce38)的课程Demo 和 **2023百度移动端实战训练营** 内的优秀作业展示。
1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

#### 软件页面展示
![Alt text](imgs/image.png)
![Alt text](imgs/image-0.png)
![Alt text](imgs/image-1.png)
![Alt text](imgs/image-2.png)
![Alt text](imgs/image-3.png)
![Alt text](imgs/image-4.png)
![Alt text](imgs/image-5.png)
![Alt text](imgs/image-6.png)
![Alt text](imgs/image-10.png)
![Alt text](imgs/image-7.png)
![Alt text](imgs/image-8.png)
![Alt text](imgs/image-9.png)    


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
