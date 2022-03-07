package utility;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import data.Coordinates;
import data.LocationFrom;
import data.LocationTo;
import data.Route;
import exception.NullEnvException;

/**
 * Класс управляет записью/чтением из файлов
 */
public class FileManager  {
    /**
     * Переменная окружения
     */
    private static String env;

    /**
     * Установить переменную окружения
     * @param env переменная окружения
     */
    public static void setEnv(String env){
        FileManager.env = env;
    }

    public static String getEnv(){
        return env;
    }

    /**
     * Сохранить коллекцию в файл
     */
    public static void saveCollection() {
        BufferedWriter writer = null;
        try {
            if (System.getenv(env) == null) throw new NullEnvException();
            OutputStream os = new FileOutputStream(System.getenv(env));
            writer = new BufferedWriter(new OutputStreamWriter(os));
            for (Route route : CollectionManager.getRouteCollection()) {
                String str = String.join(",", route.getRouteList());
                writer.write(str + "\n");
            }
        } catch (NullEnvException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                System.out.println("Коллекция успешно сохранена в " + env + ".");
                CollectionManager.saveTimeCollection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Загрузить коллекцию из файла
     */
    public static void readCollection(){
        BufferedReader reader = null;
        String line = "";
        try {
            if (System.getenv(env) == null) throw new NullEnvException();
            reader = new BufferedReader(new FileReader(System.getenv(env)));
            CSVReader reader1 = new CSVReader(reader);
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                for (int i = 0; i < row.length; i++){
                    row[i] = row[i].trim().toLowerCase();
                }
                CollectionManager.getRouteCollection().add(new Route(row[0],row[1],
                        new Coordinates(Double.parseDouble(row[2]),Double.parseDouble(row[3])),row[4],
                        new LocationFrom(Integer.parseInt(row[5]),Float.parseFloat(row[6]),Double.parseDouble(row[7])),
                        new LocationTo(Float.parseFloat(row[8]), Long.parseLong(row[9]),row[10]), Long.parseLong(row[11])));
            }
        }
        catch(NullEnvException e){
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                reader.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Выполнить скрипт из файла
     * @param fileName
     */
    public static void readScript(String  fileName){
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(fileName));
            File file = new File(fileName);
            InputStream fileInput = new FileInputStream(file);
            Scanner userScanner = new Scanner(fileInput);
            ConsoleManager.setUserScanner(userScanner);
            while ((line = reader.readLine()) != null) {
                ConsoleManager.interactiveMode();
            }
            userScanner = new Scanner(System.in);
            ConsoleManager.setUserScanner(userScanner);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                reader.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
}