import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv", StandardCharsets.UTF_8))) {


        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)

            try (RandomAccessFile w = new RandomAccessFile("data/data.db", "rw")){
                while ((line = reader.readLine()) != null) {
                    Scanner scanner = new Scanner(line);
                    scanner.useDelimiter(",");

                    int year = scanner.nextInt();
                    String industryAggregation = scanner.next();
                    String industryCode = scanner.next();
                    String industryName = scanner.next();
                    double value = scanner.nextDouble();


            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");
                    w.writeInt(year);
                    w.write(FixedString(industryAggregation.getBytes(), 10));
                    w.write(FixedString(industryCode.getBytes(), 8));
                    w.write(FixedString(industryName.getBytes(), 50));
                    w.writeDouble(value);

                    System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

                }
            } catch (FileNotFoundException e) {}

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);
    public static byte[] FixedString(byte[] text, int len){
        byte[] arr = new byte[len];

        for (int i = 0; i < len; i++) {
            if(i < text.length){
                arr[i] = text[i];
            }else{
                arr[i] = ' ';
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return arr;
    }

}