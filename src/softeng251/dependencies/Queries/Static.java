package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Raymond on 4/12/2017.
 * This subclass of query deals with the "Static" query and prints out the static uses for each module in dependency file.
 * It is a child class of OtherQueries.
 */
public class Static extends OtherQueries {
    public Static(DependencyFile File){
        super(File);
    }

    public void printField(Module module){
        module.printField("StaticUses"); //Prints the staticUses field
    }

    public ArrayList<Module> createOutputModuleList(ArrayList<Module> Modules, ArrayList<FileLine> FileLines){
        for(softeng251.dependencies.Module Module: Modules){
            ArrayList<String> Counted = new ArrayList<>(); //ArrayList for counted modules
            for (FileLine line: FileLines){
                if (line.isDependency()) {
                    String targetLine = line.getAttribute("target");
                    String categoryLine = line.getAttribute("category");
                    String sourceLine = line.getAttribute("source");

                    if (Module.equalsSource(sourceLine) && categoryLine.contains("Static")) { //Category line only contains "Static" on Static invocations
                        if (!targetLine.equals(sourceLine) && Counted.indexOf(targetLine) == -1) { //Only executes if the target is not the source and if it has not been counted
                            Module.increaseStaticUses(); //Increments the _staticCount field of the Module for every static use.
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
