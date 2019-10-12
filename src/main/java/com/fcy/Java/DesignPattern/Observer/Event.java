package com.fcy.Java.DesignPattern.Observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
* 主题通知观察者的事件
* 每个主题都有状态
* Old存储旧的状态
* New存储新的状态
* */
public class Event {
    private Object Old;
    private Object New;
}
