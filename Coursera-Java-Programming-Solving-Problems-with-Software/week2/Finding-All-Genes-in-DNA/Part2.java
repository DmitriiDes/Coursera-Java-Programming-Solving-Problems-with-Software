
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String stringa, String stringb){
        int k = 0;
        
        while (stringb != ""){
            if (stringb.indexOf(stringa)== -1){break;};
            stringb = stringb.substring(stringb.indexOf(stringa)+stringa.length(),stringb.length());
            k += 1;
            
        }
        return k;
    }

}
