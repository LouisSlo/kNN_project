import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    public List<Observation> readData(String path) {
        List<Observation> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                List<Double> data = new ArrayList<>();
                for (int i = 0; i < parts.length - 1; i++) {
                    data.add(Double.parseDouble(parts[i]));
                }
                String etykieta = parts[parts.length - 1];
                list.add(new Observation(data, etykieta));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Uwaga! Nie znaleziono pliku pod ścieżką: " + path);
            e.printStackTrace();
        }
        return list;
    }
}