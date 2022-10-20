import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;


import java.util.Scanner;

public class App {
    final static int Industry_AggregationL =10;
    final static int Industry_codeL =8;
    final static int Industry_nameL =50;
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)
            RandomAccessFile raf = new RandomAccessFile("data/data.db","rw");
            int pos=(int) raf.getChannel().position();
            System.out.println(pos);
            byte[] fixed_string ;
            int i=0;
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();
                raf.seek(raf.length());
                raf.write(year);
                writeFixedString(industryAggregation, raf,10);
                writeFixedString(industryAggregation, raf,8);
                writeFixedString(industryAggregation, raf,50);
                raf.writeDouble(value);

               

                


                System.out.printf("%d %5d %-10s %-10s %-50s %10.2f\n", i, year, industryAggregation, industryCode, industryName, value);
                i++;
            } 


     System.out.println(raf.length());
     
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    private static void writeFixedString(String name, RandomAccessFile raf,int len) throws IOException {
        if (name.length() > len) {
            name = name.substring(0, len);
        }
        for (int i = 0; i < len; i++) {
            byte b = 0;
            if (i < name.length()) {
                b = (byte) name.charAt(i);
            }
            raf.write(b);
        }
    }

    


    
}
