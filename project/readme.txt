es.zip 为搜索引擎服务器部分压缩文件
myse.zip 为搜索引擎爬虫部分压缩文件
se.sql 为数据库转储文件

技术要点：
    maven构建项目
爬虫部分：
    webmagic爬虫框架，自带slf4j-log4j12日志系统
    hibernate与数据库连接
    lombok插件，简化实体类开发
搜索引擎部分：
    SpringBoot搭建服务器
    SpringData  Jpa与数据库连接
    thymeleaf模板引擎
    lucene全文检索引擎建立索引与获取索引数据
    slf4j-log4j12日志系统
    lombok插件，简化实体类开发
数据库部分：
    mysql

技术版本：
爬虫部分：
    webmagic0.7.3
搜索引擎部分
    SpringBoot2.1.1
    lucene7.1.0
数据库部分
    mysql8.0.11

    