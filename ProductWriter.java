import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String> People =new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\productData.txt");

        boolean done = false;
        /*
        a.	ID (a String)
        b.	FirstName
        c.	LastName
        d.	Title (a string like Mr., Mrs., Ms., Dr., etc.)
        e.	YearOfBirth (an int)
        */


        String personRec = "";
        String ID = "";
        String Name = "";
        String Des = "";
        double Cost = 0;
        do{
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]:");
            Name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            Des = SafeInput.getNonZeroLenString(in, "Enter Product description:");
            Cost = SafeInput.getDouble(in,"Enter Cost");

            personRec = ID + ", " + Name  + ", " + Des + ", " + Cost;
            People.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        }while(!done);

        for(String p: People)
                System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : People)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
