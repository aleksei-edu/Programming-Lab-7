package utility;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import data.Route;
import java.util.Set;
public class CollectionManager {
    private static int freeNumberForId = 0;
    private static LocalDate lastInitTime;
    private static Set<Route> routeCollection = new LinkedHashSet<>();

    public static int getFreeNumberForId(){
        freeNumberForId+=1;
        return freeNumberForId;

    }

    public static Set<Route> getRouteCollection(){
        return routeCollection;
    }

    public static void initCollection(){
        lastInitTime = LocalDate.now();
    }

    public static LocalDate getLastInitTime() {
        return lastInitTime;
    }
    public static void loadCollection(){
        lastInitTime = LocalDate.now();
        FileManager.readCollection();
    }

    public static void clear(){
        routeCollection.clear();
        System.out.println("Коллекция успешно очищена.");
    }
}
