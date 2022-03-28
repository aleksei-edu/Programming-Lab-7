package utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import annotation.Inject;
import data.Route;
import exception.IdOverflowException;
import lombok.Getter;

import java.util.List;
import java.util.Set;

/**
 * Класс управляет коллекцией
 */
public class JavaCollectionManager implements CollectionManager{
    /**
     * Свободный номер для уникального id.
     */
    private static int freeNumberForId = 1;
    /**
     * Дата последней инициализации коллекции.
     */
    private static LocalDate lastInitTime;
    /**
     * Дата последнего сохранения коллекции.
     */
    private static LocalDate saveTime;
    /**
     * Коллекция в которой хранятся {@link Route}
     */
    @Inject(singleton = false)
    private static LinkedHashSet<Route> routeCollection;
    @Inject(singleton = false)
    private static ArrayList<String[]> stringRouteCollection;
    @Inject
    private FileManager fileManager;
    private static CollectionManager COLLECTION_MANAGER = new JavaCollectionManager();
    public static CollectionManager getInstance(){return COLLECTION_MANAGER;}
    /**
     * Метод ищет новое уникальное id
     * @return уникальное id
     */
    public int getFreeNumberForId(){
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

    /**
     * Возвращает коллекцию из <b>Route</b>.
     * @return коллекция из Route.
     */
    public Set<Route> getRouteCollection(){
        return routeCollection;
    }

    /**
     * Записывает последнее время сохранения коллекции.
     */
    public void saveTimeCollection(){saveTime = LocalDate.now();}

    /**
     * Возвращает последнюю дату сохранения коллекции.
     * @return Последнюю дату сохранения.
     */
    public LocalDate getSaveTimeCollection(){return saveTime;}

    /**
     * Возвращает последнюю дату инициализации коллекции.
     * @return последнюю дату инициализации.
     */
    public LocalDate getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Загружает коллекцию из файла
     */
    public void loadCollection(){
        lastInitTime = LocalDate.now();
        fileManager.readCollection();
    }

    /**
     * Очищает коллекцию
     */
    public void clear(){
        routeCollection.clear();
        System.out.println("Коллекция успешно очищена.");
    }


    public static ArrayList<String[]> getStringRouteCollection() {
        return stringRouteCollection;
    }

    public void addStringRouteCollection(String[] stringRoute) {
        stringRouteCollection.add(stringRoute);
    }
}
