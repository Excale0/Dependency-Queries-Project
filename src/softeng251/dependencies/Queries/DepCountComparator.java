package softeng251.dependencies.Queries;

import softeng251.dependencies.Module;

import java.util.Comparator;

/**
 * Created by Raymond on 4/13/2017.
 * This class creates the comparator object for establishing an order for the DepCount outputs which is different from the
 * order of the output from other queries. It invokes the method used to compare DepCount modules in the modules class
 */
public class DepCountComparator implements Comparator<Module> {

    public int compare(Module M1, Module M2){
        return M1.compareModulesDepCount(M2);
    } //Calls method from Module class to order the output
}
