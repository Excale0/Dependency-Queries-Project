package softeng251.dependencies;

import softeng251.dependencies.Queries.Query;
import softeng251.dependencies.Queries.QueryFinder;

/**
 * Created by Raymond Wang on 4/5/2017.
 * This class is the class with the main method entry point for the code.
 */
public class CLI {

    public static void main(String [] args) {
        String QueryInput = args[1];
        String DataInput = args[0];
        DependencyFile file = new DependencyFile(DataInput);  //creates a new dependency file using the first input argument of filepath
        Query Query =new QueryFinder().findQuery(QueryInput,file);    //finding the correct query object

        if (Query!= null) {
            //printing the query and the data ID which was input into the command line
            System.out.println("QUERY\t" + QueryInput);
            System.out.println("DATAID\t" + DataInput);
            Query.solveQuery(); //polymorphic method invocation based on the type of query
        } else {
            System.out.println("Error: Incorrect input(s)"); //If the Query is null then that means the input could not be interpreted
        }
    }

}
