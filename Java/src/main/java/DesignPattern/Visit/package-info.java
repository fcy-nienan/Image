/*
* 访问者模式
* 将特定的数据结构和对数据的访问分离
* 分离数据操纵和数据结构
* 数据结构提供访问者接口
* 具体的访问者可以获取到该数据的引用,然后通过该引用访问该数据
*
* ComputerPart  具体数据
* Computer      包含具体数据的对象
* ComputerVisit 访问者接口
*
* 也就是将自己的引用传给需要访问我的对象
* 定义一个accept方法,该方法接受一个访问者接口的参数
* 然后调用该访问者接口的某个方法,将自己的引用当作参数传递给访问者
* */
package DesignPattern.Visit;