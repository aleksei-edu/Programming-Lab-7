package utility;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import data.Route;
import exception.IdOverflowException;

import java.util.Set;

/**
 * Класс управляет коллекцией
 */
public class CollectionManager {
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
    private static Set<Route> routeCollection = new LinkedHashSet<Route>();

    /**
     * Метод ищет новое уникальное id
     * @return уникальное id
     */
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

    /**
     * Возвращает коллекцию из <b>Route</b>.
     * @return коллекция из Route.
     */
    public static Set<Route> getRouteCollection(){
        return routeCollection;
    }

    /**
     * Записывает последнее время сохранения коллекции.
     */
    public static void saveTimeCollection(){saveTime = LocalDate.now();}

    /**
     * Возвращает последнюю дату сохранения коллекции.
     * @return Последнюю дату сохранения.
     */
    public static LocalDate getSaveTimeCollection(){return saveTime;}

    /**
     * Возвращает последнюю дату инициализации коллекции.
     * @return последнюю дату инициализации.
     */
    public static LocalDate getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Загружает коллекцию из файла
     */
    public static void loadCollection(){
        lastInitTime = LocalDate.now();
        FileManager.readCollection();
    }

    /**
     * Очищает коллекцию
     */
    public static void clear(){
        routeCollection.clear();
        System.out.println("Коллекция успешно очищена.");
    }
}
