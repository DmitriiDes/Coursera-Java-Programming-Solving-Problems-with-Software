
/**
 * Methods to print a list of links from a file or a link based on prespecified part of the link.
 * 
 * @author Dmitrii Desiatkov aka DDK256 
 * @version Aug 31 2017
 */
import edu.duke.*;
import java.util.*;

public class Part4FindingWebLinks {
     public List<String> getLinkfromFile(String strlink, String file_lock){
        FileResource fr = new FileResource(file_lock);
        //int k = 0;
        //String[] links = new String[];
        List<String> links = new ArrayList<>();
        for (String line: fr.lines()){
        String tempLine = line.toLowerCase();
        String tempLink = strlink.toLowerCase();
        int idx = tempLine.indexOf(tempLink);
        if (idx != -1) {
            int idxs = line.lastIndexOf("\"", idx+1);
            int idxe = line.indexOf("\"",idx+strlink.length());
            if (idxs!= -1 && idxe != -1){
                //links[k] = line.substring(idxs,idxe+1);
                links.add(line.substring(idxs,idxe+1));
                //k += k;
        }
        }
        
        }
        return links;
    }
     public List<String> getLinkfromLink(String strlink, String file_URL){
        URLResource fr = new URLResource(file_URL);
        //int k = 0;
        //String[] links = new String[];
        List<String> links = new ArrayList<>();
        for (String line: fr.lines()){
        String tempLine = line.toLowerCase();
        String tempLink = strlink.toLowerCase();
        int idx = tempLine.indexOf(tempLink);
        if (idx != -1) {
            int idxs = line.lastIndexOf("\"", idx+1);
            int idxe = line.indexOf("\"",idx+strlink.length());
            if (idxs!= -1 && idxe != -1){
                //links[k] = line.substring(idxs,idxe+1);
                //k += k;
                links.add(line.substring(idxs,idxe+1));
        }
        }
        
        }
        return links;
        }
     public void testing(){
        System.out.println(getLinkfromLink("youtube","http://www.dukelearntoprogram.com/course2/data/manylinks.html"));
        }
}
