
/**
 * twoOccurrences returns true is stringa accures in string b >=2 times, false otherwise.
 * lastPart method returns part of stringb following ocurance of stringa 
 * 
 * @author Dmitrii Desiatkov aka DDK256 
 * @version Aug 31 2017
 */
public class Part3ProblemSolvingWithStrings {
    public boolean twoOccurrences(String stringa, String stringb){
        int idx = stringb.indexOf(stringa);
        if (idx != -1){idx = stringb.indexOf(stringa, idx+stringa.length());};
        if (idx != -1){return true;};
        return false;
    }
    public String lastPart(String stringa, String stringb){
        int idx = stringb.indexOf(stringa);
        if (idx!=-1){return stringb.substring(idx+stringa.length());};
        return stringb;
    }
    public void testing(){
        String test1 = "banana";
        String test2 = "Alabama";
        String test3 = "phd";
        String test4 = "A";
        String test5 = "a";
        System.out.println(twoOccurrences(test4,test1));
        System.out.println(twoOccurrences(test4,test2));
        System.out.println(twoOccurrences(test4,test3));
        System.out.println(twoOccurrences(test5,test1));
        System.out.println(twoOccurrences(test5,test2));
        System.out.println(twoOccurrences(test5,test3));
        System.out.println(lastPart(test5,test2));
        System.out.println(lastPart(test5,test3));
    }
}
