package designpattern.decorator;

public class RedShapeDecorator  extends ShapeDecorator{


    public RedShapeDecorator(Shape decorateShape) {
        super(decorateShape);
    }

    @Override
    public void draw() {
        decorateShape.draw();
        setRedBorder(decorateShape);
    }

    private void setRedBorder(Shape decorateShape) {
        System.out.println("Border color:red");
    }
}
