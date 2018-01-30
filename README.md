##插件已经集成到idea中，可以通过idea插件库下载

 

 **目前只支持mysql数据生成，后续会陆续支持**<br/>
 **目前postgres 适配已经完成70%，对于bit类型以及其他postgres特有类型适配不够良好，希望对postgres熟悉的童鞋能留言给个建议，蟹蟹~** 

![输入图片说明](https://gitee.com/uploads/images/2018/0112/172532_2b0f0e39_549070.png "1515749070(1).png")


##mybatis-generate

1.  自定义生成mybatis ，可以自己控制模板

2.  db目录下的main文件运行即可

3.  借鉴 Mybatis-generator的思想

4.  insert、update、query生成  主键查询生成XXXById

5.  自由控制sql的返回的属性集，通过fetchXXX，或者excludeXXX控制  通过Builder构建查询


##程序界面

## 1.   程序主页
![主页](https://gitee.com/uploads/images/2017/1110/130118_532538fc_549070.png "主页.png")


## 2.   生成代码使用
![步骤1](https://gitee.com/uploads/images/2018/0116/130621_1470c25a_549070.png "步骤1.png")

具体使用请查询example目录