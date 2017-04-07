package designpattern.decorator;

/**
 * Created by wizardholy on 2017/4/6.
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decorateShape;

    public ShapeDecorator(Shape decorateShape) {
        this.decorateShape = decorateShape;
    }

    @Override
    public void draw() {
        decorateShape.draw();
    }
}
