package com.stayli.plugins.transform

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod

public class ClassInject {

//    private static String injectStr = "System.out.println(\"I Love yhg\" ); ";
//    private static String injectStr = "android.util.Log.e(\"abc\", \"onCreate\");";
    private
    static String injectStr = " android.widget.Toast.makeText(this, \"aasdsadsafaf\", android.widget.Toast.LENGTH_SHORT).show();";
//    android.widget.Toast.makeText(this, "aasdsadsafaf", Toast.LENGTH_SHORT).show();

    public static void injectDir(String path, String packageName, ClassPool pool) {

        File dir = new File(path)
        if (dir.isDirectory()) {
            dir.eachFileRecurse {
                File file ->

                    String filePath = file.absolutePath
                    //确保当前文件是class文件，并且不是系统自动生成的class文件
                    if (filePath.endsWith(".class")
                            && !filePath.contains('R$')
                            && !filePath.contains('R.class')
                            && !filePath.contains("BuildConfig.class")) {

                        System.err.println(filePath + ", -> " + packageName)
                        // 判断当前目录是否是在我们的应用包里面
                        int index = filePath.indexOf(packageName);
                        boolean isMyPackage = index != -1;
                        if (isMyPackage) {
                            if (file.getName().equals("SplashActivity.class")) {

                                int end = filePath.length() - 6 // .class = 6
                                String className = filePath.substring(index, end)
                                        .replace('\\', '.')
                                        .replace('/', '.')

                                //开始修改class文件
                                CtClass c = pool.getCtClass(className)

                                if (c.isFrozen()) {
                                    c.defrost()
                                }

                                //获取到OnCreate方法
                                CtMethod ctMethod = c.getDeclaredMethod("onCreate")

                                println("方法名 = " + ctMethod)

                                String insetBeforeStr = """ android.widget.Toast.makeText(this,"我是被插入的Toast代码~!!",android.widget.Toast.LENGTH_SHORT).show();
                                                """
                                //在方法开头插入代码
                                ctMethod.insertBefore(insetBeforeStr);

//                            CtConstructor[] cts = c.getDeclaredConstructors()
//                            if (cts == null || cts.length == 0) {
//                                //手动创建一个构造函数
//                                CtConstructor constructor = new CtConstructor(new CtClass[0], c)
//                                System.err.println(filePath)
//                                constructor.insertBeforeBody(injectStr)
//                                c.addConstructor(constructor)
//                            } else {
//                                System.err.println(filePath)
//                                cts[0].insertBeforeBody(injectStr)
//                            }
                                c.writeFile(path)
                                c.detach()
                            }
                        }
                    }
            }
        }
    }
}
