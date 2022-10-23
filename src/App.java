import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)
            RandomAccessFile raf = new RandomAccessFile("data/data.db", "rw");

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();

               raf.write(year);
                raf.write(fillString(industryAggregation, 10));
                raf.write(fillString(industryCode, 8));
                raf.write(fillString(industryName, 10));
                raf.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    private static byte[] fillString(String str, int len){
        byte Byte[]= new byte[len];
        
        for(int i= 0; i < len ; i++){
            if (i < str.length()) {
                Byte [i] = (byte) str.charAt(i);
            } else {
                return Byte;
            }
        }
        return Byte;
            
       
         }



}
