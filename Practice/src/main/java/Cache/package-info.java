package Cache;
/*
*
*
* 缓存淘汰算法
* FIFO First In First Out
*   先入先出
* LFU  Least Frequently Used
*   按照使用频率排序
*
*
*
* LRU  Least Recently Used
*   按照使用时间排序
*
*
* 缓存一致性
*   当对数据进行更新的时候是先更新缓存还是先更新数据库
*
*   进行的步骤
*   查询缓存不存在的时候会从数据库中读取值并更新缓存
*
*   考虑存在的问题
*       更新数据库出现错误
*       多线程同时更新
*
*   先更新缓存，再更新数据库
*   A线程更新缓存
*
* */