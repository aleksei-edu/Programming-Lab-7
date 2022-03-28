package data;

import annotation.ClassMeta;
import lombok.Getter;

/**
 * Объекты, которые используются в Route.
 */
@ClassMeta(name = "coordinates")
@Getter
public class Coordinates {
    private Double x; //Максимальное значение поля: 669, Поле не может быть null
    private double y;

    public Coordinates(Double x, double y){
        try {
            if (x > 669){
                throw new IllegalArgumentException();
            }
            else this.x= x;
            this.y = y;
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return "Coordinate x: " + x + ", Coordinate y: " + y;
    }
}
