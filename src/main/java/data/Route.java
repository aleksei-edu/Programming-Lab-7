package data;
import utility.CollectionManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Route implements Comparable<Route> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocationFrom from; //Поле не может быть null
    private LocationTo to; //Поле не может быть null
    private Long distance; //Поле не может быть null, Значение поля должно быть больше 1
    private List<String> routeList = new ArrayList<>();

    public Route(String id,String name, Coordinates coordinates, String creationDate,
                    LocationFrom from, LocationTo to, Long distance ){
        try {
            if (id.equals("")) this.id = CollectionManager.getFreeNumberForId();
            else this.id = Integer.parseInt(id);
            if (name == null || name.equals("")) throw new IllegalArgumentException(); else this.name = name;
            if(coordinates == null) throw new IllegalArgumentException(); else this.coordinates = coordinates;
            if (creationDate.equals("")) this.creationDate = CollectionManager.getLastInitTime();
            else this.creationDate = LocalDate.parse(creationDate);
            if (from == null) throw new IllegalArgumentException(); else this.from = from;
            if (to == null) throw new IllegalArgumentException(); else this.to = to;
            if(distance < 1 || distance == null) throw new IllegalArgumentException("distance incorrect");
            else this.distance = distance;

            routeList.add(((Integer)this.id).toString());
            routeList.add(name);
            routeList.add(coordinates.getX().toString());
            routeList.add(((Double)coordinates.getY()).toString());
            routeList.add(this.creationDate.toString());
            routeList.add(from.getX().toString());
            routeList.add(((Float)from.getY()).toString());
            routeList.add(((Double)from.getZ()).toString());
            routeList.add(to.getX().toString());
            routeList.add(to.getY().toString());
            routeList.add(to.getName());
            routeList.add(distance.toString());
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public String getName(){return name;}

    public Coordinates getCoordinates(){return coordinates;}

    public LocalDate getCreationDate(){return creationDate;}

    public LocationFrom getFrom(){return from;}

    public LocationTo getTo(){return to;}

    public Long getDistance(){return distance;}

    @Override
    public String toString(){
        return "id: " + id + ", name: " + name + ", " + coordinates.toString() +
                ", creationDate: " + creationDate + ", " + from.toString() + ", " + to.toString() +
                ", distance: " + distance;
    }

    public List<String> getRouteList(){
        return routeList;
    }
    public int compareTo(Route routeObj){
        Integer compareId = id;
        return compareId.compareTo(routeObj.getId());
    }
}
