package reflection;


import java.lang.reflect.Method;

/**
 * <p>
 * Title: 私有方法调用工具类
 * </p>
 *
 * <p>
 * Description:利用java反射调用类的的私有方法
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 *
 * @author 孙钰佳
 * @main sunyujia@yahoo.cn
 * @date Jun 1, 2008 10:18:58 PM
 */
public class PrivateUtil {
    /**
     * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
     *
     * @param clazz
     *            目标类
     * @param methodName
     *            方法名
     * @param classes
     *            方法参数类型数组
     * @return 方法对象
     * @throws Exception
     */
    public static Method getMethod(Class clazz, String methodName,
                                   final Class[] classes) throws Exception {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(methodName, classes);
            } catch (NoSuchMethodException ex) {
                if (clazz.getSuperclass() == null) {
                    return method;
                } else {
                    method = getMethod(clazz.getSuperclass(), methodName,
                            classes);
                }
            }
        }
        return method;
    }

    /**
     *
     * @param obj
     *            调整方法的对象
     * @param methodName
     *            方法名
     * @param classes
     *            参数类型数组
     * @param objects
     *            参数数组
     * @return 方法的返回值
     */
    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes, final Object[] objects) {
        try {
            Method method = getMethod(obj.getClass(), methodName, classes);
            method.setAccessible(true);// 调用private方法的关键一句话
            return method.invoke(obj, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes) {
        return invoke(obj, methodName, classes, new Object[] {});
    }

    public static Object invoke(final Object obj, final String methodName) {
        return invoke(obj, methodName, new Class[] {}, new Object[] {});
    }

    /**
     * 测试反射调用
     *
     * @param args
     */
    public static void main(String[] args) {
        PrivateUtil.invoke(new B(), "printlnA", new Class[] { String.class },
                new Object[] { "test" });
        PrivateUtil.invoke(new B(), "printlnB");
    }
}

class A {
    private void printlnA(String s) {
        System.out.println(s);
    }
}

class B extends A {
    private void printlnB() {
        System.out.println("b");
    }
}