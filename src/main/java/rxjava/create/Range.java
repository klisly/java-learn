package rxjava.create;


import rx.Observable;

//ranger(x, y) 指定数字从X开始发射N个数字
public class Range {
    public static void main(String[] args) {
        Observable.range(10, 10)
                .subscribe(number->{
                   System.out.println(number);
                });
    }

}
