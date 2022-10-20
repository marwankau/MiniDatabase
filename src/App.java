import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
     try{
        BufferedWriter reader = new BufferedWriter(new FileWriter("data/source.csv")

     }
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
                int lineNumber = 0;
                String strLine = "";
                while( (strLine =reader.readLine()) != null)
                {   
                   lineNumber++;
                      String industryAggregation = dis.readUTF();
                      String industryCode = dis.readUTF();
                      double value = dis.readDouble();
                      String industryname = dis.readUTF();

                  
                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
