import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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
               

        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv", StandardCharsets.UTF_8))
       
        )
        
  
        {
                 File db = new File("src/data.db");
        FileInputStream fis = new FileInputStream("data/source.csv");
        DataInputStream dis = new DataInputStream(fis);
        FileOutputStream fos = new FileOutputStream("src/data.db");
        DataOutputStream dos = new DataOutputStream(fos); 
            String line = reader.readLine(); // skip column title (first line)
            String  name = " ";


            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

                dos.writeInt(year);
                byte[] Industryaggregation	 = new byte[10];
                byte[] fixedIndustrycode= new byte[8];
                byte[] Industryname = new byte[50];
                dos.writeDouble(value);

                


                
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void writeFixedString(byte[] name, byte[] buff, int length) {
        for (int i = 0; i < length; i++) {
            if (name.length <= i) {
                buff[i] = 0;
            } else {
                buff[i] = (byte)name.length;
            }
        }
    }
}
