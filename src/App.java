import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)
           
            FileOutputStream fos = new FileOutputStream("data/data.db");
            DataOutputStream dos = new DataOutputStream(fos);

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();


                dos.writeInt(year);
                dos.write(writeFixedString (industryAggregation.getBytes(),10));
                dos.write(writeFixedString (industryCode.getBytes(),8));
                dos.write(writeFixedString (industryName.getBytes(),50));
                dos.writeDouble(value);



                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }
            
            dos.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
        public static byte[] writeFixedString (byte[] b, int len){
            byte[] arr = new byte[len];
    
            for (int i = 0; i < len; i++) {
                if(i < b.length){
                    arr[i] = b[i];
                }else{
                    arr[i] =' ';
                }
            }
    
            return arr;
        }
    }

