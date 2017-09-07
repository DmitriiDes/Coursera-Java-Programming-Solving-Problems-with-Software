
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
public int howMany(String stringa, String stringb){
        int k = 0;
        while (stringb != ""){
            if (stringb.indexOf(stringa)== -1){break;};
            stringb = stringb.substring(stringb.indexOf(stringa)+stringa.length(),stringb.length());
            k += 1;
            
        }
        return k;
    }
public Integer findStopCodon(String dna, int startIndex, String stopCodon){
    if (dna!="" && Character.isLowerCase(dna.charAt(0))){
            stopCodon = stopCodon.toLowerCase();
        };        
        int stopIndex = dna.indexOf(stopCodon,startIndex);
        if (stopIndex!=-1){
            if ((stopIndex-startIndex)%3 == 0){
            return stopIndex;
        };
    };
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
public void  printAllGenes(String dna){
        String gene = findGene(dna);
        System.out.println(gene);
        while (gene != ""){
            
            dna = dna.substring(dna.indexOf(gene)+gene.length(),dna.length());
            gene = findGene(dna);
            System.out.println(gene);
            
        }
    }        
public int countGenes(String dna){
        String gene = findGene(dna);
        int k = 0;
        while (gene != ""){
            dna = dna.substring(dna.indexOf(gene)+gene.length(),dna.length());
            gene = findGene(dna);
            k +=1;
            
        }
        return k;
    }    
}
