package softeng251.dependencies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Raymond Wang on 4/6/2017.
 * This class deals with DependencyFile objects which are constructed from the input file that is to be read.
 * The DependencyFile object has only one field, _file which contains the file with dependency information.
 * The only method in this class is the ReadFile method which reads the file for relevant file lines and returns them in
 * an array of FileLine objects.
 */

public class DependencyFile {
    private File _file;

    public DependencyFile(String filePath) {
        _file = new File(filePath); //constructor creates a new file from the filepath string
    }

    public ArrayList<FileLine> readFile() {
        Scanner line;
        ArrayList<FileLine> DependencyList = new ArrayList<>();

        try {
            line = new Scanner(_file);

            while (line.hasNextLine()) {
                String lineString = line.nextLine();

                //This if statement checks whether the file line contains module information. It checks for the # as the
                //starting character and empty strings which indicate that the file line does not contain module info.
                if (lineString.indexOf('#') != 0 && !lineString.isEmpty()) { //
                    FileLine Line = new FileLine(lineString);
                    DependencyList.add(Line);
                }
            }

            line.close();

        }
        catch (FileNotFoundException e) { // if the file is not found then an exception is caught
            System.out.println("Error: File not found");
            return null;
        }

        return DependencyList;
    }
}
