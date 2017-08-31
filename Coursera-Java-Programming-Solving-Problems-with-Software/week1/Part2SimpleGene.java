
/**
 * findSimpleGene Method 
 * Recieves a string of values.
 * Checks for presence of startCodon and stopCodon.
 * Extracts first substring starting with start and finishing with end codones.
 * Verifies sumstring.length() mod 3 is zero = valid gene
 * Prints the result
 * 
 * @author Dmitrii Desiatkov aka DDK256 
 * @version Aug 31 2017
 */
public class Part2SimpleGene {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        if (dna!="" && Character.isLowerCase(dna.charAt(0))){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        };
        int start = dna.indexOf(startCodon);
        if (start == -1){return "thre are no genes";};
        int end = dna.indexOf(stopCodon, start+startCodon.length());
        if (end == -1){return "thre are no genes";};
        if (dna.substring(start,end+3).length()%3 == 0){
            return dna.substring(start,end+3);}
            else{
                return "There are no valid genes";         
    }
    
}
public void testSimpleGene(){
    String startC = "ATG";
    String stopC = "TAA";
    String dna1 = "ATTTLBNLGRFFDETAA";
    String dna2 = "ASDATGDSSFDSDFGGSDGGGT";
    String dna3 = "ADSADSFFDSFSDGSLSDGSDGSDGSLD";
    String dna4 = "ASDATGASDWERTGFASDTAA";
    String dna5 = "ATGAWQWERREWWETAA";
    System.out.println(findSimpleGene(dna1, startC, stopC));
    System.out.println(findSimpleGene(dna2, startC, stopC));
    System.out.println(findSimpleGene(dna3, startC, stopC));
    System.out.println(findSimpleGene(dna4, startC, stopC));
    System.out.println(findSimpleGene(dna5, startC, stopC));
    System.out.println(findSimpleGene(dna4.toLowerCase(), startC, stopC));
}
}
