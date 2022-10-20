import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode,
                        industryName, value);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        
        FileOutputStream fos = new FileOutputStream("data/data.db");

        fos.write(byteConverter(null, null, null, 0));

        

    

    }

    public static byte [] byteConverter(String industryAgg, String industryCode, String industryName, double value){
      industryAgg.getBytes();
      industryCode.getBytes();
      industryName.getBytes();

      byte [] ba = new byte[30];

      return ba;
     




        


    }
}
