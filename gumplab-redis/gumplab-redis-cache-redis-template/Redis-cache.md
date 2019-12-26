/**
 * springboot + redis：缓存数据
 * 
 * 启动类开启缓存：@EnableCaching 
 *
 * 配置文件开启redis缓存：spring.cache.type: redis #缓存类型开启为redis
 * @Cacheable：      优先从缓存查询数据
 * @CachePut：       添加到缓存中
 * @CacheEvict：     删除缓存中的数据
 *
 * * 通用参数：
 * * key：       缓存的key，为空(按照方法所有参数进行组合)，不为空(必须按照SPEL表达式编写)
 * * value：     缓存的名称，必须指定一个，如 value = {"user1","user2"}
 * * condition： 是否调用缓存的条件，SPEL表达式编写，返回 true or false，默认true执行缓存
 *
 * * cacheEvict 特例：
 * * allEntries：       是否清空所有缓存，默认false不清空
 * * beforeInvocation： 是否在方法执行前就清空，默认false，且方法抛出异常则不会清空缓存
 */