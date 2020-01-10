# 单节点服务注册中心 (application.yml)
# 双节点高可用服务注册中心 (application-server1.yml, application-server1.yml)
## windows本地启动两个注册中心
1. 在 server1 的配置文件中，让它的service-url指向 server2，在 server2 的配置文件中让它的service-url指向 server1
2. 为了让 server1 和 server2 能够被正确的访问到，我们需要在C:\Windows\System32\drivers\etc目录下的hosts文件总添加两行配置，如下:
127.0.0.1 server1
127.0.0.1 server2
3. 由于 server1 和 server2 互相指向对方，实际上我们构建了一个双节点的服务注册中心集群

## windows本地启动两个jar并访问: http://localhost:8761/eureka-server1/, http://localhost:8762/eureka-server2/
java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=server1  
java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=server2

## 客户端添加两个注册中心的地址：
eureka.client.service-url.defaultZone=http://admin:123456@localhost:8761/eureka-server1/eureka/,http://admin:123456@localhost:8761/eureka-server2/eureka/ 

# ps: server1和server2部署在两个服务器，只需修改相应的localhost进行相互注册

