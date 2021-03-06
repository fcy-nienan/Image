package Imitate.Cache.writeCache;

import java.util.logging.Logger;

public class System {
    /*
    * 四种操作
    * 增加,修改,删除,查找
    * 两个状态
    * 缓存,内存
    * 缓存空间是有容量的
    * 缓存是会过期的
    * 缓存上一次访问时间
    *
    * 过期后如何删除
    *   开定时器删除还是put的时候再删除还是定时删除
    *
    * 缓存空间满后淘汰哪些数据
    *   根据缓存的哪些属性选择需要淘汰的数据
    *
    *
    * 修改
    *   修改缓存,修改内存
    * 删除
    *   删除缓存,删除内存
    * 查找
    *   查找缓存,查找内存
    *
    * 新增数据可以多个线程同时进行,仅就数据而言,但是我们还要维护一个缓存的size变量
    * 在对其自增的过程中需要进行同步处理
    *
    * 修改不能多个线程同时进行
    * 可能会造成缓存和内存中的数据不一致
    *
    *
    * */
}
