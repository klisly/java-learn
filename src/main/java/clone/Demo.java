package clone;

public class Demo {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 18);
        Student s2 = (Student) s1.clone();
        s2.name = "lisi";
        s2.age = 20;
        System.out.println("s1:"+s1);
        System.out.println("s2:"+s2);

    }
}

class Student implements Cloneable {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object clone() {
        Object o = null;
        try {
            o = (Student) super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}