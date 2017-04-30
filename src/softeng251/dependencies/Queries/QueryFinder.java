package softeng251.dependencies.Queries;

import softeng251.dependencies.DependencyFile;

/**
 * Created by Raymond on 4/13/2017.
 * This class was created in order to tidy up the main method in CLI. It finds the appropriate query based on the input
 * string argument in the CL and constructs the appropriate query object that deals with the query. Its sole purpose is
 * to determine the query that is input into the CLI
 */
public class QueryFinder {
    public Query findQuery(String query, DependencyFile file) {
        Query Query = null;

            //if the input query argument satisfies one of the queries, then the appropriate query object is constructed.
            if (query.equals("Summary")) {
                Query = new Summary(file);
            } else if (query.equals("Aggregates")) {
                Query = new Aggregate(file);
            } else if (query.equals("DepCount")) {
                Query = new DepCount(file);
            } else if (query.equals("FanIn")) {
                Query = new FanIn(file);
            } else if (query.equals("FanOut")) {
                Query = new FanOut(file);
            } else if (query.equals("Uses")) {
                Query = new Uses(file);
            } else if (query.equals("Static")) {
                Query = new Static(file);
            }

            //getQueryData will only be null when the file does not exist.
            if (Query.getQueryData()== null){
                return null; //returns null if the file does not exist.
            }

        return Query;
    }
}
