import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
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
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("data.db");
        DataOutputStream dos = new DataOutputStream(fos);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");
                //
                int year = scanner.nextInt();
                //String Stringyear = Integer.toBinaryString(year);
                

                String industryAggregation = scanner.next();
                byte[] ByteIndustryAgg = industryAggregation.getBytes();

                String industryCode = scanner.next();
                byte[] ByteIndustryCode = industryCode.getBytes();

                String industryName = scanner.next();
                byte[] ByteIndustryName = industryName.getBytes();

                double value = scanner.nextDouble();
                Byte ByteValue = (byte) value;
                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode,
                        industryName, value);

                /////
                dos.writeInt(year);

                byte[] fixed_Aggergation = new byte[10];
                writeFixedString(industryAggregation, fixed_Aggergation, 10);
                dos.write(fixed_Aggergation);

                byte[] fixed_industryCode = new byte[8];
                writeFixedString(industryCode, fixed_industryCode, 8);
                dos.write(fixed_industryCode);

                byte[] fixed_IndustryName = new byte[50];
                writeFixedString(industryName, fixed_IndustryName, 50);
                dos.write(fixed_IndustryName);

                dos.writeDouble(value);

            }
            dos.close();
            reader.close();

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