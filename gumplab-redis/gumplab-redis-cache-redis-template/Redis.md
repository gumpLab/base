## Redis
- config set stop-writes-on-bgsave-error no
- auth 123456
### 基本数据类型：String 
- Redis 的字符串是动态字符串，是可以修改的字符串，内部结构实现上类似于 Java 的 ArrayList，采用预分配冗余空间的方式来减少内存的频繁分配。
- 内部为当前字符串实际分配的空间 capacity 一般要高于实际字符串长度 len。当字符串长度小于 1M 时，扩容都是加倍现有的空间，如果超过 1M，扩容时一次只会多扩 1M 的空间。
  需要注意的是字符串最大长度为 512M。
### 基本数据类型：List --> LinkedList
- Redis 的列表结构常用来做异步队列使用。将需要延后处理的任务结构体序列化成字符串塞进 Redis 的列表，另一个线程从这个列表中轮询数据进行处理。
- 队列：左边进右边出  lpush --> rpop  ||  rpush --> lpop
- 栈  ：左边进左边出  lpush --> lpop  ||  rpush --> rpop
- index(索引) index = 0 表示第一个元素 index = -1 表示最后一个元素  index = -2 表示倒数第二个元素
###### 快速列表
- ZipList <压缩列表>：列表在元素较少的情况下会使用一块连续的内存存储，将所有的元素紧挨着一起存储，分配的是一块儿连续的内存。
- QuickList <快速列表>：`ZipList + 链表 = QuickList`，也就是将多个 ZipList 用多个双向指针串起来使用，既满足快速插入和删除，又节省了空间。
- 缘由：数据量过多的时候，列表使用 QuickList，因为普通链表需要的附加指针空间太大，浪费空间，且会加重内存的碎片化。
- 比如这个列表里存的都是 int 类型数据，需要在结构上额外加 pre 和 next 两个指针。
### 基本数据类型：Hash
- 类似于 Java 中的 HashMap，无序字典，为数组 + 链表的二维结构，一维 hash 的数组位置碰撞时，会将碰撞的元素使用链表串接起来。
- 不同的是 Redis 中字典值只能为 String，且采用了 渐进式 rehash 策略！
- 因为Java 的 HashMap 在字典很大时，rehash 是个耗时的操作，需要一次性全部 rehash。Redis 为了高性能，不能堵塞服务，所以采用了渐进式 rehash 策略。
##### 渐进式：rehash
- 渐进式 rehash 会在 rehash 的同时，保留新旧两个 hash 结构，查询时会同时查询两个 hash 结构，然后在后续的定时任务中以及 hash 的子指令中，循序渐进地将旧 hash 的内容一点点迁移到新的 hash 结构中。
- 当 hash 移除了最后一个元素之后，该数据结构自动被删除，内存被回收。
##### 优点
- hash 结构也可以用来存储用户信息，不同于字符串一次性需要全部序列化整个对象，hash 可以对用户结构中的每个字段单独存储。这样当我们需要获取用户信息时可以进行部分获取。
而以整个字符串的形式去保存用户信息的话就只能一次性全部读取，这样就会比较浪费网络流量。
##### 缺点
- hash 也有缺点，hash 结构的存储消耗要高于单个字符串，到底该使用 hash 还是字符串，需要根据实际情况再三权衡。
### 基本数据类型：set
- Redis 的集合相当于 Java 语言里面的 HashSet，它内部的键值对是无序的唯一的。它的内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值 NULL。当集合中最后一个元素移除之后，数据结构自动删除，内存被回收。 
- set 结构可以用来存储活动中奖的用户 ID，因为有去重功能，可以保证同一个用户不会中奖两次。
### 基本数据类型：z-set
- z-set 可能是 Redis 提供的最为特色的数据结构，它也是在面试中面试官最爱问的数据结构。
- 它类似于 Java 的 SortedSet 和 HashMap 的结合体，一方面它是一个 set，保证了内部value 的唯一性，另一方面它可以给每个 value 赋予一个 score，代表这个 value 的排序权重。
- 它的内部实现用的是一种叫着「跳跃列表」的数据结构。z-set 中最后一个 value 被移除后，数据结构自动删除，内存被回收。 
- z-set 可以用来存粉丝列表，value 值是粉丝的用户 ID，score 是关注时间。我们可以对粉丝列表按关注时间进行排序。
- z-set 还可以用来存储学生的成绩，value 值是学生的 ID，score 是他的考试成绩。我们可以对成绩按分数进行排序就可以得到他的名次。




|Command|Return|Desc|Demo|
| :--- | :--- | :--- | :--- |
| set key value | OK | 新增 | set project gumplab|
| get key value | value/nil(key not exist) | 获取 | get project gumplab|
| mset key1 value key2 value | OK | 批量新增 | set project1 gumplab project2 redis|
| mget key1 key2 ... | value1 value2 | 批量获取 | mget project1 project2|
| expire key seconds | (integer) 1 / (integer) 0(key not exist) | 设置key过期时间 | expire project 10|
| setex key seconds value | OK | 新增的同时设置过期时间 | set project 10 gumplab|
| exists key1 key2 key3... | (integer) 1 / (integer) 0 (key all not exist)  | 验证Key是否存在 | exists project1 project2|
| setnx key value | (integer) 1 / (integer) 0(key is exist)  | set key 的时候先验证是否存在 | setnx key value |
| incr key | value + 1  | value类型为数字的时候自增+1 | set number 10 --> incr number --> number = 11 |
| incrby key number | value + number  | value类型为数字的时候增加一个number值 | incrby number 30 --> number = 41 |
---
|Command|Return|Desc|Demo|
| :--- | :--- | :--- | :--- |
| rpush key value0 value1 value2 | (integer) 3 | 从右边push元素进去 | rpush books java go python |
| lpop key | value_index(0)-->java | 从左边pop元素出去 | lpop books |
| lindex key index | OK | 获取index位置所在的元素元素 - go | lindex books 1 |
| lrange key 0 -1 | OK | 获取所有value | lrange key 0 -1 |
| ltrim key start_index end_index| OK | 通过 ltrim 来实现一个定长的链表，区间之外的元素统统移除， ltrim key 1 0 表示移除所有元素 | ltrim key 1 2  |
| llen key | OK | 展示key的length | llen books |
---
|Command|Return|Desc|Demo|
| :--- | :--- | :--- | :--- |
| hset key field value | OK | 添加 |  |
| hget key field | field-vale | 获取 key 中某个 field 对应的 value |  |
| hlen key | Integer(3) | 获取 Hash 的length |  |
| hgetall key | field1 value1 field2 value2 | 打印key中所有键值对儿数据 |  |
| hmset key field1 value1 field2 value2 ... | OK | 批量添加 |  |
| hmget key field1 field2 ... | value1 value2 | 批量获取 |  |
| hincrby key field number | field-value + number | field对应的value为数值，累加一个number |  |


> zadd books 9.0 "think in java" 
(integer) 1 
> zadd books 8.9 "java concurrency" 
(integer) 1 
> zadd books 8.6 "java cookbook" 
(integer) 1 
> zrange books 0 -1 # 按 score 排序列出，参数区间为排名范围
1) "java cookbook" 
2) "java concurrency" 
3) "think in java" 
> zrevrange books 0 -1 # 按 score 逆序列出，参数区间为排名范围
1) "think in java" 
2) "java concurrency" 
3) "java cookbook" 
> zcard books # 相当于 count()
(integer) 3 
> zscore books "java concurrency" # 获取指定 value 的 score
"8.9000000000000004" # 内部 score 使用 double 类型进行存储，所以存在小数点精度问题
> zrank books "java concurrency" # 排名
(integer) 1 
> zrangebyscore books 0 8.91 # 根据分值区间遍历 zset
1) "java cookbook" 
2) "java concurrency" 
> zrangebyscore books -inf 8.91 withscores # 根据分值区间 (-∞, 8.91] 遍历 zset，同时返
回分值。inf 代表 infinite，无穷大的意思。
1) "java cookbook" 
2) "8.5999999999999996" 
3) "java concurrency" 
4) "8.9000000000000004" 
> zrem books "java concurrency" # 删除 value
(integer) 1 
> zrange books 0 -1 
1) "java cookbook" 
2) "think in java"

