package data;

public class LocationTo {
    private Float x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private String name; //Поле не может быть null

    public LocationTo(Float x, Long y, String name){
        try{
            if(x == null || y == null || name == null){
                throw new NullPointerException();
            }
            else{
                this.x = x;
                this.y = y;
                this.name = name;
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public String toString(){
        return "LocationTo x: " + x.toString() + ", LocationTo y: " + y.toString() + ", LocationTo name: " + name;
    }

    public Float getX(){
        return x;
    }

    public Long getY(){
        return y;
    }

    public String getName(){
        return name;
    }
}
