import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {

            FileOutputStream f = new FileOutputStream("data/data.db");
          //  RandomAccessFile raf = new RandomAccessFile(f, "rw");
          DataOutputStream D = new DataOutputStream(f) ;
            String line = reader.readLine(); // skip column title (first line)


            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                D.writeInt(year);

                String industryAggregation = scanner.next();
                byte b[] = new byte [10] ; 
                writeFixedString(industryAggregation, b,10);
                D.write(b);


                String industryCode = scanner.next();
                byte b2[] = new byte [8] ; 
                 writeFixedString(industryCode, b2 , 8);
                 D.write(b2);


                String industryName = scanner.next();
                byte b3[] = new byte[50] ;
                 writeFixedString(industryName, b3, 50);
                 D.write(b3);

                double value = scanner.nextDouble();
                D.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }

            D.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void writeFixedString(String str, byte[] buff, int length) {
        for (int i = 0; i < length; i++) {
            if (str.length() <= i) {
                buff[i] = 0;
            } else {
                buff[i] = (byte)str.charAt(i);
            }
        }
    
    }

}
