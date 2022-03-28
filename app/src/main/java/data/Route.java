package data;
import annotation.ClassMeta;
import annotation.Inject;
import commands.impl.MaxByCreationDate;
import commands.impl.MinByDistance;
import lombok.Getter;
import utility.CollectionManager;
import utility.JavaCollectionManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Элементы коллекцией, которыми управляет программа
 */
@Getter
@ClassMeta(name = "route")
public class Route implements Comparable<Route> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocationFrom from; //Поле не может быть null
    private LocationTo to; //Поле не может быть null
    private Long distance; //Поле не может быть null, Значение поля должно быть больше 1
    /**
     * Все поля Route в виде String
     */
    private List<String> routeList = new ArrayList<>();
    private CollectionManager collectionManager = JavaCollectionManager.getInstance();

    /**
     *
     * @param id если есть, иначе ""
     * @param name Route name
     * @param coordinates Route coordinates
     * @param creationDate если есть, иначе ""
     * @param from Route from
     * @param to Route to
     * @param distance Route distance
     */
    public Route(String id,String name, Coordinates coordinates, String creationDate,
                    LocationFrom from, LocationTo to, Long distance ){
        try {
            if (id.equals("")) this.id = collectionManager.getFreeNumberForId();
            else this.id = Integer.parseInt(id);
            if (name == null || name.equals("")) throw new IllegalArgumentException(); else this.name = name;
            if(coordinates == null) throw new IllegalArgumentException(); else this.coordinates = coordinates;
            if (creationDate.equals("")) this.creationDate = collectionManager.getLastInitTime();
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
            String[] str = {routeList.get(0), routeList.get(1), routeList.get(2), routeList.get(3), routeList.get(4),
            routeList.get(5), routeList.get(6), routeList.get(7), routeList.get(8), routeList.get(9), routeList.get(10),
            routeList.get(11)};
            collectionManager.addStringRouteCollection(str);
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    /**
     * Обновить поля элемента Route
     * @param route новый элемент Route
     */
    public void updateRoute(Route route){
        this.name = route.name;
        this.coordinates = route.coordinates;
        this.from = route.from;
        this.to = route.to;
        this.distance = route.distance;
    }

    @Override
    public String toString(){
        return "id: " + id + ", name: " + name + ", " + coordinates.toString() +
                ", creationDate: " + creationDate + ", " + from.toString() + ", " + to.toString() +
                ", distance: " + distance;
    }

    @Override
    public int compareTo(Route routeObj){
        Integer compareId = id;
        return compareId.compareTo(routeObj.getId());
    }

    /**
     * Класс для сравнения элементов Route по дистанции
     * @see MinByDistance
     */
    public static class ComparatorByDistance implements Comparator<Route>{
        @Override
        public int compare(Route o1, Route o2) {
            return o1.getDistance().compareTo(o2.getDistance());
        }
    }

    /**
     * Класc для сравнения элементов Route по дате создания
     * @see MaxByCreationDate
     */
    public static class ComparatorByCreationDate implements Comparator<Route>{
        @Override
        public int compare(Route o1, Route o2) {
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    }
}
