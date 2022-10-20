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

import java.io.*;

public class App {
    public static void main(String[] args) {
        File f = new File("data/data.db");
        BufferedWriter write = null;
        try {
            f.createNewFile();
            write = new BufferedWriter(new FileWriter(f));
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/source.csv"))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                int years = scanner.nextInt();
                String industryAggregation = scanner.nextLine();
                String industryCode = scanner.nextLine();
                String industryName = scanner.nextLine();
                double value = scanner.nextDouble();

                System.out.printf("%5d %-10s %-10s %-50s %10.2f\n", years, industryAggregation, industryCode,
                        industryName, value);

                String yearSg = "";
                yearSg = yearSg + years;
                writerFixedString(yearSg, write, 6);

                writerFixedString(industryAggregation, write, 10);
                writerFixedString(industryCode, write, 8);
                writerFixedString(industryName, write, 50);

                String valueString = "";
                valueString += value;
                writerFixedString(valueString, write, 8);

                write.write("\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                write.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void writerFixedString(String Name, BufferedWriter br, int lngth) {
        if (Name.length() > lngth) {
            Name = Name.substring(0, lngth);
        }
        char[] c = new char[lngth];
        for (int i = 0; i < lngth; i++) {
            if (i < Name.length()) {
                c[i] = Name.charAt(i);
            } else {
                c[i] = ' ';
            }

        }
        try {
            br.write(b);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}