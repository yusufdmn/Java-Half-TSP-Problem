import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputData {

    public static List<City> readCitiesFromFile(String filename) {
        List<City> cities = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                cities.add(new City(id, x, y));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return cities;
    }


    public static void writeOutPutFile(String path, String output){
        try {
            File file = new File(path);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
