package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;

/**
 * Created by Raymond Wang on 4/7/2017.
 * The Summary class is the class that resolves the "Summary" Query. It provides a summary of the file
 * and prints the number of dependencies within the file; the number of source modules with dependencies; the
 * number of source modules without dependencies and the number of target modules that do not have dependencies.
 */
public class Summary extends Query {
    public Summary(DependencyFile File) {
        super(File);
    }

    public void solveQuery() {
        int DEPS, SRCNODEPS, SRCDEPS, SOURCENOTARGET;
        ArrayList<ArrayList<Module>> Sources = returnSourceModuleLists();
        if(returnSourceModuleLists() == null){ //If there is no relevant query data, the output is 0 for all labels.
            DEPS = 0;
            SRCNODEPS = 0;
            SRCDEPS = 0;
            SOURCENOTARGET = 0;
        } else {
            DEPS = countDependencies();
            SRCNODEPS = (Sources.get(1).size()); //Gets the size of the NoDepSources List
            SRCDEPS = (Sources.get(0).size());//Gets the size of the DepSources List
            SOURCENOTARGET = returnTargetSourcesWithoutDep();
        }
        System.out.println("DEPS\t" + DEPS);
        System.out.println("SRCWITHDEPS\t" + SRCDEPS);
        System.out.println("SRCNODEPS\t" + SRCNODEPS);
        System.out.println("TGTNOTSRC\t" + SOURCENOTARGET);
    }

    /*This method counts the number of dependencies within a an ArrayList of FileLines obtained from the
    dependency file.
     */
    public int countDependencies() {
        int Dependencies = 0;
        for (FileLine s : getQueryData()) {
            if (s.isDependency()) {
                Dependencies++;
            }
        }
        return Dependencies;
    }

    /*This method counts the number of target modules without a dependency and includes the targets
    which have not been seen. In order to achieve this, the type of every module created in the module list
    from the query class has to be removed as the class is not specified for target modules.
     */
    public int returnTargetSourcesWithoutDep() {
        ArrayList<Module> DepSources = (returnSourceModuleLists().get(0)); //Gets all source modules with dependencies
        ArrayList<Module> TargetSourcesNoDep = new ArrayList<>();

        for (FileLine line : getQueryData()) {
            if (line.isDependency()) {
                String Target = line.getAttribute("target");
                Module TargetModule = new Module(Target); //Creates new module from target module

                //If the target module is not in the DepSources list or the TargetSourceNoDepList, then it is added to the latter
                if (DepSources.indexOf(TargetModule) == -1 && TargetSourcesNoDep.indexOf(TargetModule) == -1) {
                    TargetSourcesNoDep.add(TargetModule);
                }
            }
        }
        return TargetSourcesNoDep.size(); //Returning the number of elements in the ArrayList
    }



}
