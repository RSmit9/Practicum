import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

interface Filter {
    boolean accept(String word);
}


class ShortWordFilter implements Filter {
    @Override
    public boolean accept(String word) {
        return word.length() < 5;
    }
}


public class ShortLister {

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));


        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();


            processFile(selectedFile);
        } else {
            System.out.println("cancelled");
        }
    }

    private static void processFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            ShortWordFilter shortWordFilter = new ShortWordFilter();


            while ((line = reader.readLine()) != null) {

                String[] words = line.split("\\s+");


                for (String word : words) {
                    if (shortWordFilter.accept(word)) {
                        System.out.println(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
