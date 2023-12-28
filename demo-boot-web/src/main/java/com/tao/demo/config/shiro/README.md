# Bug记录

因为Shiro-redis的版本问题，导致redis.client过低，和高版本springboot冲突，导致一直异常。

```xml

<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
</dependency>
```