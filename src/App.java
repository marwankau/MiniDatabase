import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
    public static void main(String[] args) throws FileNotFoundException {
       
        FileOutputStream fos = new FileOutputStream("data/Data.db");
        DataOutputStream dos = new DataOutputStream(fos);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
    
                String line = reader.readLine();

                while ((line = reader.readLine()) != null) {
                    Scanner scanner = new Scanner(line);
                    scanner.useDelimiter(",");
    
                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();
                dos.writeInt(year);
                byte[] Getfixed = new byte[10];
                writeFixStr(industryAggregation, Getfixed,8);
                dos.write(Getfixed);

                Getfixed = new byte[8];
                writeFixStr(industryCode, Getfixed, 8);
                dos.write(Getfixed);
                Getfixed = new byte[50];
                writeFixStr(industryName, Getfixed, 50);
                dos.write(Getfixed);

                dos.writeDouble(value);

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

            }
            reader.close();
            dos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void writeFixStr(String st, byte[] len, int length) throws IOException{
        for(int i = 0; i < length; i++){
            if (st.length() <= i){
                len[i]= 0;


            }else {
                len[i] =(byte)st.charAt(i);
            }
        }
    }
}
