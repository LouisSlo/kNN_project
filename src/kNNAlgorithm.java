import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kNNAlgorithm {

    public static class Distance_resultate {
        public double distance;
        public String etyquette;

        public Distance_resultate(double distance, String etyquette) {
            this.distance = distance;
            this.etyquette = etyquette;
        }
    }

        public double calculateDistance(List<Double> vector1, List<Double> vector2) {
            double sumSquere = 0.0;
            for (int i = 0 ; i <vector1.size() ; i++) {
                sumSquere += Math.pow(vector1.get(i) - vector2.get(i), 2);
            }
            return Math.sqrt(sumSquere);
        }

    public String classify(List<Observation> trainSet, List<Double> newVector, int k){
        List<Distance_resultate> distances = new ArrayList<>();
        for (Observation observation : trainSet) {
            double distance = calculateDistance(newVector, observation.getData());
            distances.add(new Distance_resultate(distance, observation.getEtykieta()));
        }
        distances.sort((a, b) -> Double.compare(a.distance, b.distance));
        Map<String, Integer> votes = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String label = distances.get(i).etyquette;
            votes.put(label, votes.getOrDefault(label, 0) + 1);
        }
        String winningLabel = null;
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winningLabel = entry.getKey();
            }
        }
        return winningLabel;
    }

}
