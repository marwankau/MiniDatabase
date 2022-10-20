import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)


            FileOutputStream fos = new FileOutputStream("data.db");
            DataOutputStream dos = new DataOutputStream(fos);
            // FileInputStream fis = new FileInputStream("data2.db");
            // DataInputStream dis = new DataInputStream(fis);
           
            byte[] fixed_name = new byte[15];

                
                
  

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                dos.writeInt(year);
                // dis.read();
                String industryAggregation = scanner.next(); 
                dos.writeBytes(industryAggregation);
                // dos.read(industryAggregation);
                String industryCode = scanner.next();
                // writeFixedString(industryCode, fixed_name, 15);
                dos.writeBytes(industryCode);

                String industryName = scanner.next();
                // writeFixedString(industryName, fixed_name, 15);
                dos.writeBytes(industryName);

                double value = scanner.nextDouble();
                // writeFixedString(value, fixed_name, 15);
                dos.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);
   
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
