package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by wizardholy on 2016/12/13.
 */
public class RelectTest {
    public static void main(String[] args) {
        showMethods();
        showDeclaredMethods();
        showDeclaredFields();
        showFields();
        getSuperClass();
        getInterFace();
    }

    private static void getInterFace() {
        Student student = new Student("mr.simple");
        Class<?>[] interfaceses = student.getClass().getInterfaces();
        for (Class<?> class1 : interfaceses) {
            System.out.println("Student's interface is : " + class1.getName());
        }
    }

    private static void getSuperClass() {
        Student student = new Student("mr.simple");
        Class<?> superClass = student.getClass().getSuperclass();
        while (superClass != null) {
            System.out.println("Student's super class is : " + superClass.getName());
            // 再获取父类的上一层父类,直到最后的Object类,Object的父类为null
            superClass = superClass.getSuperclass();
        }
    }

    private static void showFields() {
        Student student = new Student("mr.simple");
        // 获取当前类和父类的所有公有字段
        Field[] publicFields = student.getClass().getFields();
        for (Field field : publicFields) {
            System.out.println("field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有字段
            Field ageField = student.getClass().getField("mAge");
            System.out.println(" age is : " + ageField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showDeclaredMethods() {
        Student student = new Student("mr.simple");
        Method[] methods = student.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("declared method name : " + method.getName());
        }

        try {
            Method learnMethod = student.getClass().getDeclaredMethod("learn", String.class);
            // 获取方法的参数类型列表
            Class<?>[] paramClasses = learnMethod.getParameterTypes();
            for (Class<?> class1 : paramClasses) {
                System.out.println("learn 方法的参数类型 : " + class1.getName());
            }
            // 是否是private函数,字段是否是private也可以使用这种方式判断
            System.out.println(learnMethod.getName() + " is private "
                    + Modifier.isPrivate(learnMethod.getModifiers()));
            learnMethod.invoke(student, "java ---> ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showMethods() {
        Student student = new Student("mr.simple");
        // 获取所有方法
        Method[] methods = student.getClass().getMethods();
        for (Method method : methods) {
            System.out.println("method name : " + method.getName());
        }

        try {
            // 通过getMethod只能获取公有方法,如果获取私有方法则会抛出异常,比如这里就会抛异常
            Method learnMethod = student.getClass().getMethod("learn", String.class);
            // 是否是private函数,字段是否是private也可以使用这种方式判断
            System.out.println(learnMethod.getName() + " is private " + Modifier.isPrivate(learnMethod.getModifiers()));
            // 调用learn函数
            learnMethod.invoke(student, "java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showDeclaredFields() {
        Student student = new Student("mr.simple");
        // 获取当前类和父类的所有公有字段
        Field[] publicFields = student.getClass().getDeclaredFields();
        for (Field field : publicFields) {
            System.out.println("declared field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有字段
            Field gradeField = student.getClass().getDeclaredField("mGrade");
            // 获取属性值
            System.out.println(" my grade is : " + gradeField.getInt(student));
            // 设置属性值
            gradeField.set(student, 10);
            System.out.println(" my grade is : " + gradeField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class Person {
    String mName;

    public Person(String aName) {
        mName = aName;
    }

    private void sayHello(String friendName) {
        System.out.println(mName + " say hello to " + friendName);
    }

    protected void showMyName() {
        System.out.println("My name is " + mName);
    }

    public void breathe() {
        System.out.println(" take breathe ");
    }
}

class Student extends Person implements Examination {
    // 年级
    int mGrade;

    public Student(String aName) {
        super(aName);
    }

    public Student(int grade, String aName) {
        super(aName);
        mGrade = grade;
    }

    private void learn(String course) {
        System.out.println(mName + " learn " + course);
    }

    public void takeAnExamination() {
        System.out.println(" takeAnExamination ");
    }

    public String toString() {
        return " Student :  " + mName;
    }
}

interface Examination {
    public void takeAnExamination();
}

// 呼吸接口
interface Breathe {
    public void breathe();
}