// how to create new file called data.db as binary file and write contents of source.csv as binary
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.Scanner;

public class App {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        DataOutputStream out = new DataOutputStream(new FileOutputStream("data/data.db"));

for (int i = 50000; i <= 60000; i++)
{
    ByteBuffer dbuf = ByteBuffer.allocate(2);
    dbuf.putShort((short) i);
    byte[] bytes = dbuf.array();
            out.writeByte(bytes[0]);
            out.writeByte(bytes[1]);
}
                out.flush();
            out.close();


        try (
            BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)
            DataInputStream in = new DataInputStream(new FileInputStream("data/source.csv"));
            byte[] buf = new byte[2];
             {
              
                
                while (in.available() > 0)
                {
                    

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();
                buf[0] = in.readByte();
                buf[1] = in.readByte();
            
                // Since Short keyword cannot be used, we could use the following way to get the numbers:
                int num = (0xff & buf[0]) << 8  |
                                (0xff & buf[1]);
            
                                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value); 
            }
               

            }
            
            in.close();

        } }catch (IOException e) {
            System.err.println(e.getMessage());
        }
       
    
    

    }
    }
                