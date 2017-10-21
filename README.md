#mybatis-generate
自定义生成mybatis ，可以自己控制模板

修改Main 文件中的相关属性，配置为自己的数据库然后运行即可

借鉴 Mybatis-generator的思想

目前主要生成针对，自由控制sql的返回的属性集，通过fetchXXX，或者excludeXXX控制


#使用
第一次运行会报错，在resources目录下会生成ppEnv.json的配置文件，填写正确的参数即可