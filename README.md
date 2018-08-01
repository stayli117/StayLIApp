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