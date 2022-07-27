package com.lapin.common.utility;

import com.lapin.common.controllers.ConsoleManager;
import com.lapin.common.data.Coordinates;
import com.lapin.common.data.LocationFrom;
import com.lapin.common.data.LocationTo;
import com.lapin.common.data.Route;
import com.lapin.di.context.ApplicationContext;


/**
 * Класс создаёт/обновляет элементы {@link Route} в интерактивном режиме.
 */
public class CreateNewElementManager {
    /**
     * Максимальное значение для координаты x
     */
    private static final double MAX_COORDINATE_X = 699;
    private static final ConsoleManager consoleManager = ApplicationContext.getInstance().getBean(ConsoleManager.class);

    /**
     * Создать новый элемент {@link Route}
     * @return Route
     */
    public static Route createNewElement() {
        Route route = new Route(
                askName(),
                new Coordinates(askCoordinateX(), askCoordinateY()),
                new LocationFrom(askLocationFromX(), askLocationFromY(), askLocationFromZ()),
                new LocationTo(askLocationToX(), askLocationToY(), askLocationToName()),
                askDistance());
        System.out.println("Preview:");
        System.out.println(route.toStringWithoutId());
        return route;
    }

    private static String askName() {
        while (true) {
            System.out.println("Enter Route name (type: String, can't be empty):");
            try {
                String userPrint = consoleManager.getUserPrint();
                if (userPrint.equals("")) {
                    throw new IllegalArgumentException("The value of Route name cannot be an empty string.");
                }
                return userPrint;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Double askCoordinateX() {
        while (true) {
            System.out.println("Enter Coordinate X (type: double, not greater than 699):");
            try {
                double userPrint = Double.parseDouble(consoleManager.getUserPrint());
                if (userPrint > MAX_COORDINATE_X) {
                    throw new IllegalArgumentException("The Coordinate X value exceeds" +
                            " the maximum allowed" + MAX_COORDINATE_X);
                }
                return userPrint;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Double askCoordinateY() {
        while (true) {
            try {
                System.out.println("Enter Coordinate Y (type: double):");
                return Double.parseDouble(consoleManager.getUserPrint());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Integer askLocationFromX() {
        while (true) {
            try {
                System.out.println("Enter LocationFrom X (type: int):");
                return Integer.parseInt(consoleManager.getUserPrint());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static float askLocationFromY() {
        while (true) {
            try {
                System.out.println("Enter LocationFrom Y (type: float):");
                return Float.parseFloat(consoleManager.getUserPrint());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static double askLocationFromZ() {
        while (true) {
            try {
                System.out.println("Enter LocationFrom Z (type: double):");
                return Double.parseDouble(consoleManager.getUserPrint());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Float askLocationToX() {
        while (true) {
            try {
                System.out.println("Enter LocationTo X (type: float):");
                return Float.parseFloat(consoleManager.getUserPrint());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Long askLocationToY() {
        while (true) {
            try {
                System.out.println("Enter LocationTo Y (type: long):");
                return Long.parseLong(consoleManager.getUserPrint());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static String askLocationToName() {
        while (true) {
            try {
                System.out.println("Enter LocationTo name (type: String):");
                return consoleManager.getUserPrint();
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Long askDistance() {
        while (true) {
            try {
                System.out.println("Enter distance (type: long, greater than 1):");
                var userPrint = Long.parseLong(consoleManager.getUserPrint());
                if (userPrint < 2) {
                    throw new IllegalArgumentException("The distance value must be greater than 1");
                }
                return userPrint;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
