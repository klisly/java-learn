package designpattern.prototype;

public class Demo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Circle clonedShape = (Circle) ShapeCache.getShape("1").clone();
        clonedShape.setId("1323");
        System.out.println("Shape : " + (clonedShape.getId()));

        System.out.println("Shape : " + (ShapeCache.getShape("1").getId()));
//
//        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
//        System.out.println("Shape : " + clonedShape2.getType());
//
//        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
//        System.out.println("Shape : " + clonedShape3.getType());

    }
}