# gzgz-cloud
## 微服务学习教程

[Spring Cloud Alibaba 微服务教程](https://github.com/mtcarpenter/spring-cloud-learning)

## 前言
`gzgz-cloud`通过 spring cloud alibaba 微服务体系学习各种先进技术及收集常用工具。  

## 演示地址
前端代码地址：

演示地址：

# 技术栈

- 注册中心：Nacos
- 配置中心：Nacos
- 服务网关：Spring cloud Gateway
- 服务调用：Spring cloud open-Feign
- 负载均衡：Spring cloud loadbalancer
- 链路追踪：zipkin + sleuth  
- 权限认证：shiro 
- 熔断降级：Sentinel
- 消息队列：RabbitMQ 
- 项目部署：Docker
- 分布式事务：Seata
- 数据库：mysql-5.7.32
- JDK: 1.8  
- 日志收集: ELK  
- 文件服务：OSS/Minio
- 搜索服务器：Elasticsearch
- 定时器管理：XXL-JOB
- 监控工具：spring boot admin

## 组织架构

```
gzgz-cloud
├─gzgz-cloud-common    -- 工具类及通用代码
├─gzgz-cloud-mbg MBG   -- 代码生成器
├─gzgz-cloud-shiro     -- apache shiro 公共配置类
├─gzgz-cloud-gateway   -- 网关服务
├─gzgz-cloud-monitor   -- spring boot admin 监控
├─gzgz-cloud-storage   --  文件存储服务

```
## 开源许可证


