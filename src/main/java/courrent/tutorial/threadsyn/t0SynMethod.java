package courrent.tutorial.threadsyn;

/**
 * Created by wizardholy on 2016/12/25.
 */
public class t0SynMethod {
    public static void main(String[] args){
        Account account = new Account();
        account.setBalance(1000);
        Company company = new Company(account);
        Thread thread = new Thread(company);

        Bank bank = new Bank(account);
        Thread thread1 = new Thread(bank);

        System.out.printf("Account : Initial Balance: %f\n",account.getBalance());
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Account : Final Balance: %f\n",account.getBalance());
    }
}

class Company implements Runnable{
    private Account account ;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            account.addMount(1000);
        }
    }
}

class Bank implements Runnable{
    private Account account;

    public Bank(Account account) {

        this.account = account;
    }

    @Override
    public void run() {
        for(int i = 0; i < 9; i++){
            account.substarctMount(1000);
        }
    }
}

class Account{
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void addMount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }

    public synchronized void substarctMount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        balance = tmp;
    }

}