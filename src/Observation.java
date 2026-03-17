import java.util.List;

public class Database {
   private List<Double> data;
   private String etykieta = null;

   Database(List<Double> data, String etykieta) {
      this.data = data;
      this.etykieta = etykieta;
   }

    public String getEtykieta() {
        return etykieta;
    }

    public List<Double> getData() {
        return data;
    }


}
