public class ValueChecker {
    
    public static void checkValue(int value, int min, int max) throws IntRangeException{
        if(value<min || value>max)
        {
            throw new IntRangeException(value + " is not in the valid option range");
        }
    }

    public static void checkValue(char value) throws YNException{
        if(value!='Y' && value!='N')
        {
            throw new YNException(value + " is not a Y or N");
        }
    }
}
