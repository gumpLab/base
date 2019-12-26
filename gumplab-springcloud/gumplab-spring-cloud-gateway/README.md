# Don't worry about the vague future, just strive for the clear present.
# Sping Cloud Gateway 
## GateWay 与 Zuul 对比
1. Zuul:
>-- Netflix公司开源
>-- Zuul 1.x 构建于 Servlet2.5，兼容 3.x，使用的是阻塞式的 API，不支持长连接
>-- Zuul 2.x 使用异步无阻塞式的 API，但是目前 Spring Cloud 没有集成 Zuul 2.x
2. Gateway <响应式异步模式>:
>-- Spring Cloud 微服务平台的一个子项目，属于 Sping 开源社区
>-- 构建于 Spring 5+，基于 Spring Boot 2.x 响应式、非阻塞式的 API，支持长连接
## nacos 实现动态路由优势
1. 对路由进行统一管理
2. 有阿里稳定团队维护 nacos
3. 路由信息存储在 redis 缓存中，增加缓存压力（网关节点过多，请求到达网关后，网关都会到 redis 缓存中获取）。
## Spring cloud gateway 和 nacos 实现动态路由
1. 编写动态路由实现类
2. 编写监听 nacos 下发路由配置类
### Spring coud gateway 主要源码分析
1. RouteDefinitionWriter：路由操作接口 --> 对路由的增删改查
2. RouteDefinitionRepository：路由信息存储器 --> 存储路由到内存或者缓存或者数据库中
3. InMemoryRouteDefinitionRepository: 内存存储器 --> gateway实例的内存，已经封装好可直接调用
4. ApplicationEventPublisherAware：事件发送器 --> 类似于事务，处理路由的增删改查的事务

























