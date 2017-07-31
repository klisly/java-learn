package designpattern.singleton;

/**
 * Created by wizardholy on 2017/4/6.
 */
public class EnumSingleton {
    static class Resource{
        public void say(){
            System.out.println("say");
        }
    }

    public enum SomeThing {
        INSTANCE;
        private Resource instance;
        SomeThing() {
            System.out.println("something construct");
            instance = new Resource();
        }
        public Resource getInstance() {
            return instance;
        }
    }

    public static void main(String[] args){
        SomeThing.INSTANCE.getInstance().say();
    }
}
