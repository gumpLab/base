在《Spring Cloud微服务全栈技术与案例解析》书籍中的eureka章节，按照书籍中的配置写法，打开localhost:8761页面，无法加载wro.css和wro.js。
这是因为在application.properties中添加了spring.resources.add-mappings=true。
这导致没有为静态资源添加路径映射。因此只需要修改为false就可以访问静态资源。
eureka注册服务端口与app端口配置可以不同。
eureka注册中心的端口永远是8761，如果app端口不设置为8761，导致eureka注册连接一直报错。
如果app端口设置为7000，那么需要添加eureka服务地址配置。eureka.client.service-url.defaultZone=http://localhost:7000/eureka