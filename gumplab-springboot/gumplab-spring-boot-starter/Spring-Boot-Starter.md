使用一个公用的starter的时候，只需要将相应的依赖添加的Maven的配置文件当中即可，免去了自己需要引用很多依赖类，并且SpringBoot会自动进行类的自动配置。那么 SpringBoot 是如何知道要实例化哪些类，并进行自动配置的呢？
首先，SpringBoot 在启动时会去依赖的starter包中寻找 resources/META-INF/spring.factories 文件，
后根据文件中配置的Jar包去扫描项目所依赖的Jar包，这类似于 Java 的 SPI 机制。
第二步，根据 spring.factories配置加载AutoConfigure类。
最后，根据 @Conditional注解的条件，进行自动配置并将Bean注入Spring Context 上下文当中。
我们也可以使用@ImportAutoConfiguration({MyServiceAutoConfiguration.class}) 指定自动配置哪些类。

其中 spring-boot-configuration-processor 的作用是编译时生成 spring-configuration-metadata.json ，此文件主要给IDE使用。
如当配置此jar相关配置属性在 application.yml ，你可以用ctlr+鼠标左键点击属性名，IDE会跳转到你配置此属性的类中。
我们日常使用的Spring官方的Starter一般采取spring-boot-starter-{name} 的命名方式，如 spring-boot-starter-web。
而非官方的Starter，官方建议 artifactId 命名应遵循{name}-spring-boot-starter 的格式。 例如：ysc-spring-boot-starter。

第二步编写Service: Starter要实现的功能，很简单，提供一个Service，包含一个能够将配置文件中配置的字符串根据传入的字符进行分割的方法String[] split(String separatorChar)
第三步编写配置文件读取类
第四步，编写AutoConfigure类 ，这步是关键点