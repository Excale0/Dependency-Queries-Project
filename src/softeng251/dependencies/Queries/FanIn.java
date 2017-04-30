package softeng251.dependencies.Queries;

/**
 * Created by Raymond on 4/18/2017.
 */

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Raymond on 4/12/2017.
 * The FanIn class solves the "FanIn" query which outputs the list of Module with an integer that shows how many
 * modules are dependent on the module (or is targeted by the module). It is a child class of OtherQueries.
 */
public class FanIn extends OtherQueries {
    public FanIn (DependencyFile File) {
        super(File);
    }

    public void printField(Module module){
        module.printField("Targeted");
    } //Prints the _targeted field

    public ArrayList<Module> createOutputModuleList(ArrayList<Module> Modules, ArrayList<FileLine> FileLines){
        for(softeng251.dependencies.Module Module: Modules){
            ArrayList<String> Counted = new ArrayList<>();
            for (FileLine line: FileLines){
                if (line.isDependency()) {
                    String targetLine = line.getAttribute("target");
                    String sourceLine = line.getAttribute("source");
                    //If statement checks the source is not in the Counted List and that the Module is being targeted
                    if (Counted.indexOf(sourceLine) == -1 && Module.equalsSource(targetLine)) {
                        if (!targetLine.equals(sourceLine)) { //Checks that the target of the dependency is not the source itself
                            Module.increaseTargeted(); //Increments _targeted field of module
                            Counted.add(sourceLine);
                        }
                    }
                }
            }
        }
        Collections.sort(Modules); //Collection is sorted in alphabetical order
        return Modules;
    }
}
