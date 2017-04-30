package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;
import softeng251.dependencies.Module;
import softeng251.dependencies.FileLine;

import java.util.ArrayList;

/**
 * Created by Raymond on 4/17/2017.
 * This abstract class extends queries and contains subclasses of all queries apart from summary. This abstract class
 * has an implementation for solveQuery. This is because all other queries are resolved in a very similar manner
 * so a polymorphic invocation of solveQuery would reduce duplicate code.
 * The abstract class also has the abstract methods printField and createSourceModules which each child class will implement
 */
abstract public class OtherQueries extends Query {
    public OtherQueries(DependencyFile File){super(File);}

    /* This abstract method takes an ArrayList of Module and an ArrayList of FileLines as input. Every Module will
     have its relevant field for the query counted/incremented based on the information in the file lines.
      */
    abstract public ArrayList<Module> createOutputModuleList(ArrayList<Module> AllModules, ArrayList<FileLine> FileLines);

    //The abstract printField method prints every module with the relevant field for the output of the specific query.
    abstract public void printField(Module module);

    /* The solveQuery method is the method used to output the results of the query in the main CLI. It prints all the
    modules and the relevant fields based on the query.
     */
    public void solveQuery() {
        if(AllModules() == null){
            System.out.println("No results"); //Outputs no results when there are no seen modules in the list of all modules.
        }
        else {
            ArrayList<Module> Modules = createOutputModuleList(AllModules(), getQueryData()); //polymorphic invocation
            for (Module M : Modules) {
                printField(M);//polymorphic invocation; printing out the relevant field for the query.
            }
        }
    }
}
