import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        File file = new File("data.db");
        try (
            BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"));
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            ) {
            String line = reader.readLine(); // skip column title (first line)


            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();
                dos.writeInt(year);
                byte[] lengthStr = new byte[10];
                writeFixStr(industryAggregation, lengthStr,8);
                dos.write(lengthStr);

                lengthStr = new byte[8];
                writeFixStr(industryCode, lengthStr, 8);
                dos.write(lengthStr);
                lengthStr = new byte[50];
                writeFixStr(industryName, lengthStr, 50);
                dos.write(lengthStr);

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
