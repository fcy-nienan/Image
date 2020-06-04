package Imitate.Cache.writeCache;
//缓存组件
//缓存过期处理:后台定期轮询查找过期数据并删除
//缓存淘汰策略:LRU算法
//并发安全:安全
//相关接口:
/*
* 设置缓存
* set(String key,String value)
* set(String key,String value,long liveMillis)
* 获取缓存
* get(String key)
* 查询是否过期
* expired(String key)
* 移除缓存
* remove(String key)
*
*
*
*暂时没哪个能力写出完美的带并发的组件,以后来试试
*
*
* */