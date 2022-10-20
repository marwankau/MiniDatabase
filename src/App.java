import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class App {
    public static void main(String[] args) throws IOException {
    
        FileOutputStream fos = new FileOutputStream("data/data.db");
        DataOutputStream dos = new DataOutputStream(fos);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv", StandardCharsets.UTF_8))) {
            String line = reader.readLine(); // skip column title (first line)

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();

                
                byte A[] = new byte [10] ;
                writeFixedString(industryAggregation, A,10);

                byte B[] = new byte [8] ; 
                writeFixedString(industryCode, B , 8);

                byte C[] = new byte[50] ;
                writeFixedString(industryName, C, 50);
                dos.writeInt(year);
                 dos.write(A);
                 dos.write(B);
                 dos.write(C);
                dos.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode,
                        industryName, value);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }

    private static void writeFixedString(String str, byte[] buff, int length) {
        for (int i = 0; i < length; i++) {
            if (str.length() <= i) {
                buff[i] = 0;
            } else {
                buff[i] = (byte) str.charAt(i);
            }
        }
    }
}
