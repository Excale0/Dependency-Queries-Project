package softeng251.dependencies;

/**
 * Created by Raymond Wang on 4/5/2017.
 * The purpose of the module class is to represent source Module, print them and increment their fields. This class
 * provides a natural order for comparing modules with the compare method and contains methods to compare module
 * information such as the source names and whether the module is seen or not.
 */
public class Module implements Comparable<Module> {
    private String _source;
    private String _type;
    private int _dependencyCount = 0;
    private int _targetsCount = 0;
    private int _targetedCount = 0;
    private int _usesCount = 0;
    private int _staticCount = 0;
    private int _fieldUsesCount = 0;
    private boolean _seen; //field indicating whether the module is seen or not

    public Module(String sourceName, String typeName) { //constructor takes the source and type of the module as a string
        _source = sourceName;
        _type = typeName;

        if(sourceName.endsWith("*")) { //Checking whether module is seen or not and assigns it the field _declared
            _seen = false;
        } else {
            _seen = true;
        }
    }

    //This constructor for Module is used for a method in summary where the type of the target source is not directly specified.
    public Module(String sourceName){
        _source = sourceName;
    }

    //The following methods increment the fields of the Module which are all initialised to 0. Each increase method corresponds
    //to a type of query in the OtherQueries class
    public void increaseAggregate() {
        _fieldUsesCount++;
    } //used for Aggregates

    public void increaseTargets() {
        _targetsCount++;
    } //used for FanOut

    public void increaseTargeted(){
        _targetedCount++;
    } //used for FanIn

    public void increaseDependencies(){
        _dependencyCount++;
    } //used for DepCount

    public void increaseUses(){
        _usesCount++;
    } //used for Uses

    public void increaseStaticUses(){
        _staticCount++;
    } //used for StaticUses

    //A compare method that orders the modules based on their _source string in alphabetical order.
    public int compareTo(Module M1) { //The default compare method used in sorting which uses the String _source to order outputs in alphabetical order
        return _source.compareTo(M1._source);
    }


    /*This compare method is used to sort the collection in DepCount which has a different sorting order to
    the other queries.
     */
    public int compareModulesDepCount(Module M1) {
        //The two modules are first compared by their number of dependencies. The module with the higher dependency count
        //is 'smaller'
        if (_dependencyCount > M1._dependencyCount) {
            return -1;
        } else if (_dependencyCount < M1._dependencyCount) {
            return 1;
        } else {
            return _source.compareTo(M1._source); //If the dependency count is the same, it is compared by their names using the above compareTo method.
        }
    }


    // The print field method takes a field (as a string) as input and prints the field for the module it is invoked on.
    public void printField(String field) {
        System.out.print("" +_source+" ("+_type+")\t"); //Prints the source module and its type

        //Prints the appropriate field based on the input String field
        if (field.equals("Targets")){
            System.out.println(""+_targetsCount);
        }

        if (field.equals("Targeted")){
            System.out.println(""+_targetedCount);
        }

        if (field.equals("Uses")){
            System.out.println(""+_usesCount);
        }

        if (field.equals("StaticUses")){
            System.out.println(""+_staticCount);
        }

        if (field.equals("FieldUsesCount")){
            System.out.println(""+_fieldUsesCount);
        }

        if(field.equals("Dependencies")){
            System.out.println("" +_dependencyCount);
        }
    }

    /*This method overrides the equals method inherited from java.lang.object and is used to compare the equality of
    modules in the query class. This overridden method is used for the indexOf method invocations in ArrayLists when
    */
    public boolean equals(Object ModuleObject){
        Module other = (Module)ModuleObject;
        return _source.equals(other._source); //Two modules are the same when their sources are the same.
    }



    // This method returns whether a source module has been seen or not.
    public boolean isSeen(){
        return _seen == true;
    }

    //This method returns whether the input module name (as a string) is the same as the Module's source.
    public boolean equalsSource(String moduleName){
        return _source.equals(moduleName);
    }

}
