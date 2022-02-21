package utility;

import data.Coordinates;
import data.LocationFrom;
import data.LocationTo;
import data.Route;

import java.util.Scanner;

public class CreateNewElementManager {
    private static final double MAX_COORDINATE_X = 699;

    public static void update(Route oldroute){
        Route route = new Route((Integer.valueOf(oldroute.getId()).toString()),
                askName(),
                new Coordinates(askCoordinateX(),askCoordinateY()),
                oldroute.getCreationDate().toString(),
                new LocationFrom(askLocationFromX(), askLocationFromY(), askLocationFromZ()),
                new LocationTo(askLocationToX(), askLocationToY(), askLocationToName()),
                askDistance());
        updateCollection(1,route,oldroute);
    }

    public static Route createNewElement(){
        Route route = new Route("",
                askName(),
                new Coordinates(askCoordinateX(), askCoordinateY()),
                "",
                new LocationFrom(askLocationFromX(), askLocationFromY(), askLocationFromZ()),
                new LocationTo(askLocationToX(), askLocationToY(), askLocationToName()),
                askDistance());
        System.out.println("Preview:");
        System.out.println(route.toString());
        return route;
    }


    public static void add(){
        Route route = new Route("",
                askName(),
                new Coordinates(askCoordinateX(), askCoordinateY()),
                "",
                new LocationFrom(askLocationFromX(), askLocationFromY(), askLocationFromZ()),
                new LocationTo(askLocationToX(), askLocationToY(), askLocationToName()),
                askDistance());
        updateCollection(0, route, null);
    }


    private static void updateCollection(Integer mode,Route newRoute, Route oldRoute){
        while (true) {
            try {
                if (mode == 0) {
                    System.out.println("Добавить элемент в коллекцию?");
                }
                else if (mode == 1){
                    System.out.println("Обновить элемент в коллекции?");
                }
                System.out.println(newRoute.toString());
                System.out.println("Введите y/n");
                String userPrint = ConsoleManager.getUserPrint();
                if (userPrint.equals("y")){
                    if (mode == 0) {
                        CollectionManager.getRouteCollection().add(newRoute);
                        System.out.println("Элемент добавлен в коллекцию");
                    }
                    else if(mode == 1){
                        oldRoute.updateRoute(newRoute);
                        System.out.println("Элемент обновлен");
                    }
                    break;
                }
                else if(userPrint.equals("n")){
                    if(mode == 0){System.out.println("Добаление элемента в коллекцию ОТМЕНЕНО.");}
                    else if (mode == 1){System.out.println("Обновление элемента ОТМЕНЕНО.");}
                    break;
                }
                else throw new IllegalArgumentException("Введено что-то не то. Повторите попытку.");
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }


    private static String askName(){
        while (true) {
            System.out.println("Введите Route name:");
            String userPrint = ConsoleManager.getUserPrint();
            try {
                if (userPrint.equals("")) {
                    throw new IllegalArgumentException("Значение Route name не может быть пустой строкой.");
                }
                return userPrint;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static Double askCoordinateX(){
        while (true) {
            System.out.println("Введите Coordinate X (тип: double, не более 699):");
            double userPrint = Double.parseDouble(ConsoleManager.getUserPrint());
            try {
                if (userPrint > MAX_COORDINATE_X) {
                    throw new IllegalArgumentException("Значение Coordinate X " +
                            "превышает максимально допустимое: " + MAX_COORDINATE_X);
                }
                return userPrint;
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }
    }

    private static Double askCoordinateY(){
        while (true) {
            try {
                System.out.println("Введите Coordinate Y :");
                return Double.parseDouble(ConsoleManager.getUserPrint());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Integer askLocationFromX(){
        while (true) {
            try {
                System.out.println("Введите LocationFrom X:");
                return Integer.parseInt(ConsoleManager.getUserPrint());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static float askLocationFromY(){
        while (true) {
            try {
                System.out.println("Введите LocationFrom Y:");
                return Float.parseFloat(ConsoleManager.getUserPrint());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static double askLocationFromZ(){
        while (true) {
            try {
                System.out.println("Введите LocationFrom Z:");
                return Double.parseDouble(ConsoleManager.getUserPrint());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static Float askLocationToX(){
        while (true) {
            try {
                System.out.println("Введите LocationTo X:");
                return Float.parseFloat(ConsoleManager.getUserPrint());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static Long askLocationToY(){
        while (true) {
            try {
                System.out.println("Введите LocationTo Y:");
                return Long.parseLong(ConsoleManager.getUserPrint());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static String askLocationToName(){
        while (true) {
            try {
                System.out.println("Введите LocationTo name:");
                return ConsoleManager.getUserPrint();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static Long askDistance(){
        while (true) {
            try {
                System.out.println("Введите distance:");
                var userPrint = Long.parseLong(ConsoleManager.getUserPrint());
                if (userPrint < 2) {
                    throw new IllegalArgumentException("Значение distance должно быть больше 1");
                }
                return userPrint;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
