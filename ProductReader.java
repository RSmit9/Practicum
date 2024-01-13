import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;

        try {
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                BufferedReader reader = Files.newBufferedReader(file);

                // Display column names
                System.out.println("ID\tProduct Name\tDescription\tCost");
                System.out.println("============================================");

                int line = 0;
                while (reader.ready()) {
                    String rec = reader.readLine();
                    line++;


                    if (line > 1) {
                        String[] data = rec.split("\\s+");


                        System.out.printf("%s\t%s\t%s\t%s\t%s\n", data[0], data[1], data[2], data[3], data[4]);
                    }
                }

                reader.close();
                System.out.println("\n\nData file read!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

