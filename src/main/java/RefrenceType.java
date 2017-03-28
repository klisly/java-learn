import java.lang.ref.*;
import java.util.HashSet;
import java.util.Set;

class User {

    private String name;

    public User()
    {}

    public User(String name)
    {
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void finalize(){
        System.out.println("Finalizing ... "+name);
    }
}

/**
 * Created by jinxu on 15-4-25.
 */
public class RefrenceType {

    private static ReferenceQueue<User> referenceQueue = new ReferenceQueue<User>();
    private static final int size = 10;

    public static void checkQueue(){
       /* Reference<? extends User> reference = null;
        while((reference = referenceQueue.poll())!=null){
            System.out.println("In queue : "+reference.get());
        }*/
        Reference<? extends User> reference = referenceQueue.poll();
        if(reference!=null){
            System.out.println("In queue : "+reference.get());
        }
    }

    public static void testSoftReference()
    {
        Set<SoftReference<User>> softReferenceSet = new HashSet<SoftReference<User>>();
        for (int i = 0; i < size; i++) {
            SoftReference<User> ref = new SoftReference<User>(new User("Soft " + i), referenceQueue);
            System.out.println("Just created: " + ref.get());
            softReferenceSet.add(ref);
        }
        System.gc();
        checkQueue();
    }

    public static void testWeaKReference()
    {
        Set<WeakReference<User>> weakReferenceSet = new HashSet<WeakReference<User>>();
        for (int i = 0; i < size; i++) {
            WeakReference<User> ref = new WeakReference<User>(new User("Weak " + i), referenceQueue);
            System.out.println("Just created: " + ref.get());
            weakReferenceSet.add(ref);
        }
        System.gc();
        checkQueue();
    }

    public static void testPhantomReference()
    {
        Set<PhantomReference<User>> phantomReferenceSet = new HashSet<PhantomReference<User>>();
        for (int i = 0; i < size; i++) {
            PhantomReference<User> ref =
                    new PhantomReference<User>(new User("Phantom " + i), referenceQueue);
            System.out.println("Just created: " + ref.get());
            phantomReferenceSet.add(ref);
        }
        System.gc();
        checkQueue();
    }

    public static void main(String[] args) {
        testSoftReference();
        testWeaKReference();
        testPhantomReference();
    }
}