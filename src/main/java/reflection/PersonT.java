package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PersonT {
    private String name;//私有属性
    public String address;
    public PersonT(String name){
        this.name=name;
    }
    private void setAddress(String address){ //私有方法
        this.address=address;
    }
    public static void main(String[] args) {
        try{
            PersonT person=new PersonT("zhangsan");
            Class<PersonT> cls= PersonT.class;
            //获得私有方法
            Method method=cls.getDeclaredMethod("setAddress", new Class[]{String.class});
            //设置私有方法可以被访问
            method.setAccessible(true);
            //调用私有方法
            method.invoke(person, new Object[]{"BJUT"});
            System.out.println(person.address);//输出BJUT

            //访问私有变量
            Field field=cls.getDeclaredField("name");
            method.setAccessible(true);
            System.out.println(field.get(person));//输出zhangsan

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}