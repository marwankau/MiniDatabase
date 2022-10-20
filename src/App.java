import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        File f = new File("data/data.db");
        BufferedWriter writer = null;
        try {
            f.createNewFile();
            writer = new BufferedWriter(new FileWriter(f));
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine(); // skip column title (first line)


            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int year = scanner.nextInt();
                String industryAggregation = scanner.next();
                String industryCode = scanner.next();
                String industryName = scanner.next();
                double value = scanner.nextDouble();

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", year, industryAggregation, industryCode, industryName, value);

                String yearString = "";
                yearString += year;
                writeFixedString(yearString, writer, 5);
                

                writeFixedString(industryAggregation, writer, 10);
                writeFixedString(industryCode, writer, 8);
                writeFixedString(industryName, writer, 50);
                
                String valueString = "";
                valueString += value;
                writeFixedString(valueString, writer, 8);
                
                writer.write("\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void writeFixedString(String name, BufferedWriter bf, int length) {
        if (name.length() > length) {
            name = name.substring(0, length);
        }
        char[] b = new char[length];
        for (int i = 0; i < length; i++) {
            if (i < name.length()) {
                b[i] = name.charAt(i);
            } else {
                b[i] = ' ';
            }
            
        }
        try {
            bf.write(b);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}