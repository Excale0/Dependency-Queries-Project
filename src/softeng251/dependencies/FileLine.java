package softeng251.dependencies;

/**
 * Created by Raymond on 4/13/2017.
 * This class creates FileLine objects which represent a line with module information obtained from the DependencyFile
 * There are 2 fields for this object, the actual fileLine given as a string and a boolean field which tells us whether
 * the line contains a dependency or not. The purpose of this class is to analyse file line information.
 */
public class FileLine {
    private String _fileLine;
    private boolean _isDependency;

    // the FileLine constructor takes an input string which is the line in the file
    public FileLine(String fileLine){
        _fileLine = fileLine;

        String[] splitFileLine = _fileLine.split("\\t");//Splitting up the line into attributes
        _isDependency = splitFileLine.length > 4; // If there are more than 5 attributes then the line is a dependency
    }

    // the getAttribute function returns attributes of the module from a FileLine object such as the source module or target module
    // It takes the attribute (as a string) as input and retrieves the appropriate attribute from the _fileLine field
    public String getAttribute(String attribute){
            String[] splitFileLine = _fileLine.split("\\t"); //Splits the line into it's attributes which are separated by the tab character

            //These conditionals return the appropriate attribute based on the input.
            if (attribute.equals("source")) {
                return splitFileLine[0];                            //Source is always the first attribute
            } else if (attribute.equals("target")) {
                if (isDependency())
                    return splitFileLine[4];                        //Target is the 5th attribute
            } else if (attribute.equals("category")) {
                if (isDependency())
                    return splitFileLine[7];                        //category and target will only be exist in the fileLine if it contains a dependency

            } else if (attribute.equals("type")) {
                //this if statement checks for the presence of * or ? which are not part of the type name and removes them
                if(splitFileLine[2].endsWith("*")|| splitFileLine[2].endsWith("?")) {
                    String type = splitFileLine[2];
                    int length = type.length();
                    type = type.substring(0,length-1);
                    return type;

                } else{
                    return splitFileLine[2];
                }
            }

        return "";
    }

    // This function determines whether a fileLine object has a dependency. It checks state of the field isDependency.
    public boolean isDependency(){
        return _isDependency == true;
    }


}
