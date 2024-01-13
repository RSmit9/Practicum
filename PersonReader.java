import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonReader {

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
                System.out.println("ID\tFirst Name\tLast Name\tTitle\tYob");
                System.out.println("============================================");

                int line = 0;
                while (reader.ready()) {
                    String rec = reader.readLine();
                    line++;

                    // Skip the first line (header) and display the data with specific column names
                    if (line > 1) {
                        String[] data = rec.split("\\s+"); // Assuming space-separated values

                        // Display data without row number
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

