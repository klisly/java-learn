package multithread;

/**
 * Created by wizardholy on 2017/3/28.
 */
public class WaitNotify {
    public static void main(String[] args){
        final Method method = new Method();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (method.loop < 50){
                    method.sub();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (method.loop < 50){
                    method.main();
                }
            }
        }).start();
    }
}

class Method{
    public int loop = 0;
    private boolean isMain = true;
    public synchronized void main(){
        while (!isMain){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("loop "+loop);
        for(int i = 0; i < 20; i++){
            System.out.println("main "+i);
        }
        loop++;
        isMain = false;
        this.notify();
    }
    public synchronized void sub(){
        while (isMain){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 10; i++){
            System.out.println("sub "+i);
        }
        isMain = true;
        this.notify();
    }
}

