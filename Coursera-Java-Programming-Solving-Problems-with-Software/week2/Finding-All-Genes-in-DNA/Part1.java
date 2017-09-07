
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public Integer findStopCodon(String dna, int startIndex, String stopCodon){
    if (dna!="" && Character.isLowerCase(dna.charAt(0))){
            stopCodon = stopCodon.toLowerCase();
        };        
        int stopIndex = dna.indexOf(stopCodon,startIndex);
        if (stopIndex!=-1){
            if ((stopIndex-startIndex)%3 == 0){
            return stopIndex;
        }
    }
        return dna.length();
    }
    
    public String findGene(String dna){
    
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){return "";};
        int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
        int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
        int stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
        if (stopIndexTAA<stopIndexTAG && stopIndexTAA<stopIndexTGA){
           return dna.substring(startIndex,stopIndexTAA+3); 
        }
        if (stopIndexTAG<stopIndexTAA && stopIndexTAG<stopIndexTGA){
            return dna.substring(startIndex,stopIndexTAG+3);
        }
        if (stopIndexTGA<stopIndexTAA && stopIndexTGA<stopIndexTAG){
            return dna.substring(startIndex,stopIndexTGA+3);
        }
        return "";
    }


    public void  testFindGene(){
    String dna1 = "ATTTLBNLGRFFDETAA"; ///new strings good one according to 6
    String dna2 = "ASDATGDSSFDSDFGGSDGGGT";
    String dna3 = "ADSADSFFDSFSDGSLSDGSDGSDGSLD";
    String dna4 = "ASDATGASDWERTGFASDTAA";
    String dna5 = "ATGAWQWERREWWETAA";
    System.out.println(findGene(dna1));
    System.out.println(findGene(dna2));
    System.out.println(findGene(dna3));
    System.out.println(findGene(dna4));
    System.out.println(findGene(dna5));
    }
        
    public void  printAllGenes(String dna){
        String gene = findGene(dna);
        while (gene != ""){
            dna = dna.substring(dna.indexOf(gene)+gene.length(),dna.length());
            gene = findGene(dna);
            System.out.println(gene);
            
        }
    }    
    public void testFindStopCodon(){
    
    String startC = "ATG";
    String stopC = "TAA";
    String dna1 = "ATTTLBNLGRFFDETAA";
    String dna2 = "ASDATGDSSFDSDFGGSDGGGT";
    String dna3 = "ADSADSFFDSFSDGSLSDGSDGSDGSLD";
    String dna4 = "ASDATGASDWERTGFASDTAA";
    String dna5 = "ATGAWQWERREWWETAA";
    System.out.println(findStopCodon(dna1, 14, stopC));
    System.out.println(findStopCodon(dna2, 3, stopC));
    System.out.println(findStopCodon(dna3, 2, stopC));
    System.out.println(findStopCodon(dna4, 0, stopC));
    System.out.println(findStopCodon(dna5, 0, stopC));
    System.out.println(findStopCodon(dna4.toLowerCase(), 0, stopC));   
    
    }
}
