package utility;

import java.io.*;
import java.lang.annotation.Native;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import data.Coordinates;
import data.LocationFrom;
import data.LocationTo;
import data.Route;
import exception.NullEnvException;

public class FileManager  {
    private static String env;

    public static void setEnv(String env){
        FileManager.env = env;
    }

    public static String getEnv(){
        return env;
    }

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void readCollection(){
        BufferedReader reader = null;
        String line = "";
        try {
            if (System.getenv(env) == null) throw new NullEnvException();
            reader = new BufferedReader(new FileReader(System.getenv(env)));
           // reader.readLine();
            NavigableSet<Route> tempSortedRouteCollection = new TreeSet<>();
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                for (int i = 0; i < row.length; i++){
                    row[i] = row[i].trim().toLowerCase();
                }
                tempSortedRouteCollection.add(new Route(row[0],row[1],
                        new Coordinates(Double.parseDouble(row[2]),Double.parseDouble(row[3])),row[4],
                        new LocationFrom(Integer.parseInt(row[5]),Float.parseFloat(row[6]),Double.parseDouble(row[7])),
                        new LocationTo(Float.parseFloat(row[8]), Long.parseLong(row[9]),row[10]), Long.parseLong(row[11])));
            }
            CollectionManager.getRouteCollection().addAll(tempSortedRouteCollection);
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
    
}