import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class App {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("data/data.db", "rw");
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

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode,
                        industryName, value);

                // writing binary
                raf.writeInt(year);
                convertString2Byte(industryAggregation, 10, raf);
                convertString2Byte(industryCode, 8, raf);
                convertString2Byte(industryName, 50, raf);
                raf.writeDouble(value);

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void convertString2Byte(String string, int length, RandomAccessFile raf) throws IOException {
        byte[] data = new byte[length];
        for (int i = 0; i < data.length; i++) {
            if (string.length() <= i)
                break;

            data[i] = (byte) string.charAt(i);

        }
        raf.write(data);

    }

}
