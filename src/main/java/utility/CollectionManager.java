package utility;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import data.Route;
import exception.IdOverflowException;

import java.util.Set;
import java.util.SortedSet;

public class CollectionManager {
    private static int freeNumberForId = 1;
    private static LocalDate lastInitTime;
    private static LocalDate saveTime;
    private static Set<Route> routeCollection = new LinkedHashSet<Route>();

    public static int getFreeNumberForId(){
        while (true) {
            try {
                boolean flag = true;
                for (Route route : routeCollection) {
                    if (route.getId() == freeNumberForId) {
                        flag = false;
                        break;
                    }
                }
                if(!flag) {
                    freeNumberForId += 1;
                    if(freeNumberForId < 0){throw new IdOverflowException();}
                }
                else{
                    return freeNumberForId;
                }
            }
            catch (IdOverflowException e){
                e.printStackTrace();
            }
        }
    }


    public static Set<Route> getRouteCollection(){
        return routeCollection;
    }

    public static void saveTimeCollection(){saveTime = LocalDate.now();}

    public static LocalDate getSaveTimeCollection(){return saveTime;}

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
