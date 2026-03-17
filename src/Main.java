import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj wartość k: ");
        int k = Integer.parseInt(scanner.nextLine());

        System.out.print("Podaj ścieżkę do pliku treningowego: ");
        String trainPath = scanner.nextLine();

        System.out.print("Podaj ścieżkę do pliku testowego: ");
        String testPath = scanner.nextLine();

        DataReader reader = new DataReader();
        List<Observation> trainSet = reader.readData(trainPath);
        List<Observation> testSet = reader.readData(testPath);

        System.out.println("\nWczytano " + trainSet.size() + " przykładów treningowych.");
        System.out.println("Wczytano " + testSet.size() + " przykładów testowych.\n");
        kNNAlgorithm knn = new kNNAlgorithm();

        int poprawne = 0;
        for (Observation obs : testSet) {
            String predictedLabel = knn.classify(trainSet, obs.getData(), k);
            
            if (predictedLabel.equals(obs.getEtykieta())) {
                poprawne++;
            }
        }
        double accuracy = (double) poprawne / testSet.size() * 100;
        System.out.println("Liczba poprawnych klasyfikacji: " + poprawne + " / " + testSet.size());
        System.out.println("Dokładność (accuracy): " + String.format("%.2f", accuracy) + "%");
        System.out.println("\n--- Tryb interaktywny (wpisz 'exit' aby zakończyć) ---");
        while (true) {
            System.out.print("Podaj wektor cech oddzielony spacjami: ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                String[] parts = input.split("\\s+");
                List<Double> userVector = new ArrayList<>();
                for (String part : parts) {
                    if (!part.isEmpty()) {
                        userVector.add(Double.parseDouble(part));
                    }
                }
                if (userVector.isEmpty()) continue;
                String prediction = knn.classify(trainSet, userVector, k);
                System.out.println("Przewidziana etykieta: " + prediction + "\n");
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Niepoprawny format danych. Upewnij się, że podajesz liczby oddzielone spacjami.");
            } catch (Exception e) {
                System.out.println("Wystąpił błąd: " + e.getMessage());
            }
        }
        scanner.close();
    }
}