import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)
            FileOutputStream fot =new FileOutputStream("data.db");
            DataOutputStream dot = new DataOutputStream(fot);
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                dot.write(year);
                String industryAggregation = scanner.next();
                dot.writeUTF(industryAggregation);
                String industryCode = scanner.next();
                dot.writeUTF(industryCode);
                String industryName = scanner.next();
                dot.writeUTF(industryName);
                double value = scanner.nextDouble();
                dot.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
