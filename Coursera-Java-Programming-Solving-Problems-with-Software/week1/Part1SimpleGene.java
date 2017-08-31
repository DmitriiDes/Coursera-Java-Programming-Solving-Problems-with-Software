
/**
 * findSimpleGene Method 
 * Recieves a string of values.
 * Checks for presence of start (ATG) and end (TAA) codones.
 * Extracts first substring starting with start and finishing with end codones.
 * Verifies sumstring.length() mod 3 is zero = valid gene
 * Prints the result
 * 
 * @author Dmitrii Desiatkov aka DDK256 
 * @version Aug 31 2017
 */
public class Part1SimpleGene {
    public String findSimpleGene(String dna){
        int start = dna.indexOf("ATG");
        if (start == -1){return "thre are no genes";};
        int end = dna.indexOf("TAA", start+3);
        if (end == -1){return "thre are no genes";};
        if (dna.substring(start,end+3).length()%3 == 0){
            return dna.substring(start,end+3);}
            else{
                return "There are no valid genes";         
    }
    
}
public void testSimpleGene(){
    String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
    String dna2 = "ASDATGDSSFDSDFGGSDGGGT";
    String dna3 = "ADSADSFFDSFSDGSLSDGSDGSDGSLD";
    String dna4 = "ASDATGASDWERTGFASDTAA";
    String dna5 = "ATGAWQWERREWWETAA";
    System.out.println(findSimpleGene(dna1));
    System.out.println(findSimpleGene(dna2));
    System.out.println(findSimpleGene(dna3));
    System.out.println(findSimpleGene(dna4));
    System.out.println(findSimpleGene(dna5));
}
}
