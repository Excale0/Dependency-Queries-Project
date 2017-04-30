package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.FileLine;
import softeng251.dependencies.Module;

import java.util.ArrayList;

/**
 * Created by Raymond Wang on 4/7/2017.
 * The main purpose of the query class is to retrieve all the module information from the file lines of the dependency file
 * and return it in an ArrayList of Module so that the modules can be individually processed by the specific queries that
 * are a sub class of this class. It is an abstract class that has the abstract method solveQuery which all the specific
 * query objects invoke in the main method.
 */
 abstract public class Query {
    private DependencyFile _queryFile;

    public Query(DependencyFile Dependencies) {
        _queryFile = Dependencies;
    }

    public ArrayList<FileLine> getQueryData(){
        return _queryFile.readFile();
    } //Reads the data in a DependencyFile and converts it into file lines

    abstract public void solveQuery(); //abstract method which all the subclasses of query use

    /* This method returns 3 lists of Module which are created from the Dependency file. The 3 lists of Module are
    Module that have dependencies, modules that don't have dependencies and a list of the previous two combined. Although
    the first two lists are only used in the Summary query, returning them does not break the abstraction of this class
    which is to return the Source modules. If the first two lists were created using another method in Summary, then there
    would also likely be duplicate code with my implementation method. The 3rd list is used by all other queries apart
    from Summary.
     */
    public ArrayList<ArrayList<Module>> returnSourceModuleLists() { //returns all sources in an ArrayList consisting of an ArrayList of 3 modules
        if(!getQueryData().isEmpty()) { //Checking the query data has module information
            ArrayList<ArrayList<Module>> Sources = new ArrayList<>();
            ArrayList<Module> DepSources = new ArrayList<>(); //collection for source modules with dependencies
            ArrayList<Module> NoDepSources = new ArrayList<>(); //collection for source modules without dependencies
            ArrayList<Module> allSources = new ArrayList<>(); // collection for all unique source modules

            for (FileLine line : getQueryData()) {
                String source = line.getAttribute("source");
                String type = line.getAttribute("type");
                Module Module = new Module(source,type); //Constructing module using source and type provided in the file line

                if(Module.isSeen()) { //Module is only added to any collection if it is seen

                    if (DepSources.indexOf(Module) == -1) {
                        if (line.isDependency()) {
                            DepSources.add(Module);
                        } else if (!line.isDependency() && NoDepSources.indexOf(Module) == -1) {
                            NoDepSources.add(Module);
                        }
                    }

                    if (allSources.indexOf(Module) == -1) {
                        allSources.add(Module);
                    }

                    Sources.add(DepSources);
                    Sources.add(NoDepSources);
                    Sources.add(allSources);
                }
            }

            if(allSources.isEmpty()){
                return null;
            }

            return Sources;
        }

        return null; //if there is no module information null is returned.
    }

    //This method returns all source Module from the Dependency file by retrieving the 3rd list of modules from returnSources
    public ArrayList<Module> AllModules() {
        if(returnSourceModuleLists()!= null){
            return returnSourceModuleLists().get(2);
        }

        return null;
    }

}
