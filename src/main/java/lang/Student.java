package lang;


public class Student implements Cloneable {
    String name;// 常量对象。
    int age;
    Professor p;// 学生1和学生2的引用值都是一样的。

    Student(String name, int age, Professor p) {
        this.name = name;
        this.age = age;
        this.p = p;
    }

    public Object clone() {
        Student o = null;
        try {
            o = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        o.p = (Professor) p.clone();
        return o;
    }

    public static void main(String[] args) {
        Professor p = new Professor("wangwu", 50);
        Student s1 = new Student("zhangsan", 18, p);
        Student s2 = (Student) s1.clone();
        s2.age = 12;
        s2.name = "lll";
        s2.p.name = "lisi";
        s2.p.age = 30;
        System.out.println("s1=" + s1+ "," + "s2=" + s2);
        //输出结果学生1和2的教授成为lisi,age为30。
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", p=" + p +
                '}';
    }
}

class Professor implements Cloneable{
    String name;
    int age;

    Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
