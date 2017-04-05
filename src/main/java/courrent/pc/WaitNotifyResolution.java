package courrent.pc;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class WaitNotifyResolution {
    public static void main(String[] args){

    }

    private class Queue{
        int value;
        boolean full = false;
        public synchronized void put(int i){
            if(!full){
                value = i;
                full = true;
                notify();
            }

            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
