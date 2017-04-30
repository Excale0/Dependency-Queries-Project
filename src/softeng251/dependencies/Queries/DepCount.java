package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Raymond on 4/10/2017.
 * This subclass of query deals with the "DepCount" query whicgh prints out the number of dependencies for each module
 * in the dependency file. It is a child class of OtherQueries.
 */
public class DepCount extends OtherQueries {
    public DepCount(DependencyFile File) {super(File);}

    public void printField(Module module){
        module.printField("Dependencies");
    } //Prints the _dependencyCount field

    public ArrayList<Module> createOutputModuleList(ArrayList<Module> Modules, ArrayList<FileLine> FileLines) {
        for (softeng251.dependencies.Module Module : Modules) {
            for (FileLine line : FileLines) {
                String sourceLine = line.getAttribute("source");
                if (line.isDependency() && Module.equalsSource(sourceLine)) { //Checks if the line is a dependency with the module
                    Module.increaseDependencies(); //Increments the _dependencyCount field
                }

            }
        }

        Collections.sort(Modules, new DepCountComparator() ); //Sorts the collection using the DepCountComparator object

        return Modules;
    }

}
