package designpattern.template;

/**
 * Created by wizardholy on 2017/4/6.
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // 模板
    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
}
