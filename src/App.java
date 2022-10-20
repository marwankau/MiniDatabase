import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.Scanner;

import javax.xml.catalog.Catalog;
import javax.xml.crypto.Data;

public class App {
    public static void main(String[] args) throws IOException {
        int year = 0;
        String industryAggregation = "";
        String industryCode = "";
        String industryName = "";
        double value = 0;
        FileOutputStream fos = new FileOutputStream("data/data.db");
        DataOutputStream dos = new DataOutputStream(fos);

        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv", StandardCharsets.UTF_8))) {
            String line = reader.readLine(); // skip column title (first line)

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                year = scanner.nextInt();
                industryAggregation = scanner.next();
                industryCode = scanner.next();
                industryName = scanner.next();
                value = scanner.nextDouble();

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year,
                        industryAggregation, industryCode,
                        industryName, value);
                dos.writeInt(year);
                byte[] fixedString = new byte[10];

                writeFixedString(industryAggregation, fixedString, 10);
                dos.write(fixedString);

                fixedString = new byte[8];

                writeFixedString(industryCode, fixedString, 8);
                dos.write(fixedString);
                fixedString = new byte[50];

                writeFixedString(industryName, fixedString, 50);
                dos.write(fixedString);
                dos.writeDouble(value);

            }
            reader.close();
            dos.close();
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
