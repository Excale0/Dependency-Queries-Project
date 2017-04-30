package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Raymond on 4/12/2017.
 * This subclass of OtherQueries deals with the query "Aggregates" which counts the number of field uses from other modules
 * that are used by the each module. It is a child class of OtherQueries
 */
public class Aggregate extends OtherQueries {
    public Aggregate(DependencyFile File) {
        super(File);
    } //Uses parent constructor

    public void printField(Module module){
        module.printField("FieldUsesCount");
    } //Prints the _fieldUses field

    public ArrayList<Module> createOutputModuleList(ArrayList<Module> Modules, ArrayList<FileLine> FileLines) {
        for (softeng251.dependencies.Module Module : Modules) {
            ArrayList<String> Counted = new ArrayList<>(); // Array List for counted targets
            for (FileLine line : FileLines) {
                if (line.isDependency()) {
                    String targetLine = line.getAttribute("target");
                    String categoryLine = line.getAttribute("category");
                    String sourceLine = line.getAttribute("source");

                    if (Module.equalsSource(sourceLine) && (categoryLine.equals("Field"))) {
                        if (!targetLine.equals(sourceLine) && Counted.indexOf(targetLine) == -1) {
                            Module.increaseAggregate(); // Increments the _fieldUses field of the Module for every field use.
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



