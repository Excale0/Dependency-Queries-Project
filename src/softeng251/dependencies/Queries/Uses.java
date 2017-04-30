package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Raymond on 4/12/2017.
 * This class resolves the "Uses" query which requires a list of all the modules and the number of times they use
 * methods or fields from other modules. It is a child class of OtherQueries.
 */
public class Uses extends OtherQueries {
    public Uses(DependencyFile File){
        super(File);
    }


    public void printField(Module module){
        module.printField("Uses");
    } //Prints the _usesCount field

    public ArrayList<Module> createOutputModuleList(ArrayList<Module> Modules, ArrayList<FileLine> FileLines) {
        for (softeng251.dependencies.Module Module : Modules) {
            ArrayList<String> Counted = new ArrayList<>();//ArrayList for counted modules
            for (FileLine line : FileLines) {
                if (line.isDependency()) {
                    String targetLine = line.getAttribute("target");
                    String categoryLine = line.getAttribute("category");
                    String sourceLine = line.getAttribute("source");

                    //Category line contains "Invoke" to access methods or "Get" to access fields from other modules
                    if (Module.equalsSource(sourceLine) && (categoryLine.contains("Invoke") || categoryLine.contains("Get"))) {
                        if (!targetLine.equals(sourceLine) && Counted.indexOf(targetLine) == -1) { //Only executes if the target is not the source and if it has not been counted
                            Module.increaseUses(); //Increments the _usesCount field of the Module for every use
                            Counted.add(targetLine);
                        }
                    }
                }
            }
        }

        Collections.sort(Modules); //Collection is sorted in alphabetical order
        return Modules;
    }
}
