
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
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
    
   public StorageResource getAllGenes(String dna){
       StorageResource genes = new StorageResource();
       int startIndex = 0;
       while (startIndex != -1 && startIndex <= dna.length()-6){
         String gene = findGene(dna.substring((startIndex),dna.length()));
         if (gene == ""){break;};
         genes.add(gene);
         startIndex = dna.indexOf(gene)+gene.length();
        }
       return genes;
    }    
    public float cgRatio(String dna){
    float k = 0;
    int i = 0;
    int c = dna.indexOf("C");
    int g = dna.indexOf("G");
    while (i < dna.length()){
    if (c != -1){k +=1; c = dna.indexOf("C", c+1);};
    if (g != -1){k +=1; g = dna.indexOf("G", g+1);};
    if (c == -1 && g == -1){break;};
    i += 1;
    }
        return k/dna.length();
    }
    public int countCTG(String dna){
    int k = 0;
    int i = 0;
    while (i + 3 < dna.length()){
    i = dna.indexOf("CTG",i);
    if (i != -1){k += 1; i += 3;}else{break;};
    }
    return k;
    }
    public void testgetAllGenes(){
    String dna = "ATG111111111111111111TAAATG123TGAATGATGATGATGTGATAGATG46645645121212154DAS";
    String dna1 = "CTGCGTCCTG";
    StorageResource genes = getAllGenes(dna);
    for (String gene: genes.data()){System.out.println(gene);};
    System.out.println(countCTG(dna1));    
    }
    public void processGenes(StorageResource sr){
    int k = 0;
    int cg = 0;
    String maxg = "";
        for (String gene: sr.data()){
    if (gene.length()>60){
        System.out.println("Longer 60 = "+ gene);
        k += 1;
    };
    if (cgRatio(gene)>0.35){System.out.println("CG > 0.35 = "+gene); cg += 1;
    };
    if (maxg.length()<gene.length()){maxg = gene;};

};
    System.out.println("Num longer 60 = "+k);
    System.out.println("Num cg = "+cg);
    System.out.println("length of longest = "+ maxg.length());
    }
   public void testProcessGenes(){
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = fr.asString().toUpperCase();
    System.out.println(dna);
    StorageResource genes = getAllGenes(dna);
    processGenes (genes);
    } 
}
