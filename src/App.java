import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)

            FileOutputStream is = new FileOutputStream("data/source.db");
            DataOutputStream dos = new DataOutputStream(is);
            
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();
                
                dos.writeInt(year);
                dos.write(industryAggregation.getBytes());
                dos.write(industryCode.getBytes());
                dos.write(industryName.getBytes());
                dos.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
