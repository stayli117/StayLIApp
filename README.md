# StayLIApp
* 综合
* 涉及的点比较多，比较杂
* 更多是学以致用
* lifecycle liveData room 使用练习
* 自定义注解实现 多baseUrl 的 RxJava okhttp retrofit 网络框架实现
* A star 算法实现8方向最短路径查询
* 快排算法简单实现

# Java 中底层数据操作

* int -> byte 

循序手动操作
   
 ```
     public static byte[] intToBytes2(int n){
           byte[] b = new byte[4];

           for(int i = 0;i < 4;i++){
            b[i]=(byte)(n>>(24-i*8));  
       } 
       return b;
     }
```
  利用API
  
 ```
  int v = 123456;
  byte[] bytes = ByteBuffer.allocate(4).putInt(v).array();
 ```
 
 通用多类型方式
  ```
    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
      DataOutputStream dos = new DataOutputStream(baos);  
      try {  
       
          dos.writeByte(-12);  
          dos.writeLong(12);  
          dos.writeChar('1');  
          dos.writeFloat(1.01f);  
          dos.writeUTF("嗯，是我");  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
   ```
   
   当然还有很多方式，自己书写骚操作吧
   
   
   
# 总结一些知识吧

* 分类
     * 继承View
     * 继承ViewGroup
     * 继承系统控件(Button,LinearLayout…)

* View 的几个重要方法
     * requestLayout
     * View重新调用一次layout过程
     * invalidate
     * View重新调用一次draw过程
     * forceLayout
     * 标识View在下一次重绘，需要重新调用layout过程。
     * postInvalidate
     * 这个方法与invalidate方法的作用是一样的，都是使View树重绘，但两者的使用条件不同，postInvalidate是在非UI线程中调用，invalidate则是在UI线程中调用。
* 关键的三个操作
     * 测量——onMeasure()：决定View的大小
     * 布局——onLayout()：决定View在ViewGroup中的位置
     * 绘制——onDraw()：如何绘制这个View。
* 自定义属性
     * 复写构造方法
     * values 下建立 attrs.xml 文件,在其中定义属性    
     * 通过context.obtainStyledAttributes将构造函数中的attrs进行解析出来,就可以拿到相对应的属性.
       TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
* 关键方法
     * onMeasure()
        
 ```
    MeasureSpecs 类（父视图对子视图的测量要求）
    
    测量规格（MeasureSpec） = 测量模式（mode） + 测量大小（size）
   
    测量模式（Mode）的类型有3种：UNSPECIFIED、EXACTLY 和 AT_MOST

    不要忘记调用 setMeasuredDimension（）方法 设置测量完成的数据
    
    整个过程
    
    开始测量 -> measure()-> onMeasure->
    setMeasuredDimension（）-> getDefaultSize()
    -> 测量完毕
    
    实际作用的方法：
    getDefaultSize() = 计算View的宽/高值
    setMeasuredDimension（） = 存储测量后的View宽 / 高
    
```



## HashMap 知识总结

* 说明一点 Android开发 能够使用SparseArray的情况下，别执着于HashMap
* HashMap的原理 ，还是建议一定要去看源码，看源码，不说第三遍

### 原理

* 简单说： HashMap就是Hash表的Map实现。 Hash表就是Hash数组，Map实现是指实现了Map接口。

* 数据结构
  ```
  HashMap的底层是基于数组和链表实现的，
  存储速度快的原因是因为它是通过计算散列码来决定存储的位置。
  HashMap中主要是通过key的hashCode来计算hash值的，
  只要hashCode相同，计算出来的hash值就一样。
  如果存储的对象对多了，就有可能不同的对象所算出来的hash值是相同的，
  这就出现了所谓的hash冲突。解决hash冲突的方法有很多，
  HashMap底层是通过链表来解决hash冲突的。
  
  
  * 如果看过源码应该知道，HashMap其实就是一个Entry数组，
  Entry对象中包含了键和值，其中next也是一个Entry对象，
  它就是用来处理hash冲突的，形成一个链表。
  
  默认的大小是16 ，加载因子，加载因子越大表示当前数组填满的元素越多，
  空间利用率高，不方便查询；加载因子越小，表示填满元素越少，空间利用率低，
  方便速度快。默认值是0.75
  
  其中扩容的临界值，threshold = loadFactor（加载因子）*容量
  
  当然容量和扩容因子，都可以通过构造方法进行设置。
  
   ```
* 存放数据的过程
   ```
   HashMap存放数据的时候，会先拿到key的hashCode值，
   对其进行hash运算，得到具体的hash值，
   然后根据hash值查找存放的位置，找到存放的位置后，
   然后遍历table数组找到对应位置的entry，如果当前的key已经存在，
   且对比entry的hash值和key相同的话，那么就更新value值；
   否则就将key和value值加到数组中；
   
   这里需要注意：判断是否2个Entry相同，需要从2方面判断：
   1、2个Entry的key必须相同（k = e.key||key.equals(k)）；
   2、2个Entry的hashCode，也就是hash值必须相同（
   这里说的hash是经过hash（hashCode）换算后的值）；
   上述2个条件都满足的话，才可以认定当前Entry已经存在，
   新的Entry才会替换旧的Entry
   
   ```
* 读取数据的过程

   ``` 
    首先拿到key的hashcode，
    求出hash码，根据hash码找到索引的位置，
    然后去数组中获取对应索引的元素，如果key的hash相同，
    key相同的话，那么这就是我们要找的entry，把entry的值返回出去就Ok了
 
   ``` 
   
* 与Hashtable的对比

     ``` 
     HashMap允许key和value为null；
     HashMap是非同步的，线程不安全，
     也可以通过Collections.synchronizedMap()方法来得到一个同步的HashMap
     HashMap存取速度更快，效率高
     HashMap去掉了HashTable中的contains方法，
     加上了containsValue和containsKey方法
      
     ``` 
* 与ConcurrentHashMap的对比
     ``` 
     Hashtable是synchronized的，
     但是ConcurrentHashMap同步性能更好，
     因为它仅仅根据同步级别对map的一部分进行上锁。
     ConcurrentHashMap当然可以代替HashTable，
     但是HashTable提供更强的线程安全性

     ``` 
