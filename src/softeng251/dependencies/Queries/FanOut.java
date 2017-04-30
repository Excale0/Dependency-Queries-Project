package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Raymond on 4/12/2017.
 * The FanOut class resolves the "FanOut" query which outputs the list of Module with an integer that shows how many
 * modules depend on the module (or targets the module). It is a child class of OtherQueries.
 */
public class FanOut extends OtherQueries {
    public FanOut(DependencyFile File) {super(File);}

    public void printField(Module module){
        module.printField("Targets");
    } //Prints the _targets field

    public ArrayList<Module> createOutputModuleList(ArrayList<Module> Modules, ArrayList<FileLine> FileLines){
        for(softeng251.dependencies.Module Module: Modules){
            ArrayList<String> Counted = new ArrayList<>();
            for (FileLine line: FileLines){
                if (line.isDependency()) {
                    String targetLine = line.getAttribute("target");
                    String sourceLine = line.getAttribute("source");
                    //if statement checks the source is not in the Counted List and that the Module is the target
                    if (Counted.indexOf(targetLine) == -1 && Module.equalsSource(sourceLine)) { //checks that the target of the dependency is not the source itself
                        if (!targetLine.equals(sourceLine)) {
                            Module.increaseTargets(); //Increments _targets field of module
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
