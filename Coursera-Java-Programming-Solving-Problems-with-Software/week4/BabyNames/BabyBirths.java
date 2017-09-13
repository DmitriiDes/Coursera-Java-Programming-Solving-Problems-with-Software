/**
 *
 * @author Dmitrii Desiatkov 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames =0;
		int totalGirlsN = 0;
		int totalBoysN = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			totalNames += 1;
		    int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalBoysN +=1;
			}
			else {
				totalGirls += numBorn;
				totalGirlsN += 1;
			}
		}
		System.out.println("total names = " + totalNames);
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("fgirls names = " + totalGirlsN);
		System.out.println("boys names = " + totalBoysN);
		
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource();
		totalBirths(fr);
	}
	
	public int getRank(int year, String name, String gender){
	    FileResource fr = new FileResource("data/yob"+year+".csv");
	    int k = 0;
	    for (CSVRecord rec : fr.getCSVParser(false)) {
	        if (rec.get(1).equals(gender)){
	           k += 1;
	           if (rec.get(0).equals(name)){return k;};
	           };
	       };
	    return -1;
	    };
	    
	public int getRankFromFile(FileResource fr, String name, String gender){
	    int k = 0;
	    for (CSVRecord rec : fr.getCSVParser(false)) {
	        if (rec.get(1).equals(gender)){
	           k += 1;
	           if (rec.get(0).equals(name)){return k;};
	           };
	       };
	    return -1;
	    };    
	  
    public String getName (int year, int rank, String gender){
        if (rank == -1){return "NO NAME";};
        FileResource fr = new FileResource("data/yob"+year+".csv");
	    int k = 0;
	    for (CSVRecord rec : fr.getCSVParser(false)) {
	        if (rec.get(1).equals(gender)){
	           k += 1;
	           if (k == rank){return rec.get(0);};
	           };
	       };
	    return "NO NAME";
       };
       
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println("Your new name in the year of "+newYear+" is "+newName);
       };
       
    public String yearOfHighestRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        String HighestRankFile = "NO NAME";
        int MaxRank = 9999999;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rank = getRankFromFile(fr, name, gender);
            if(MaxRank > rank && rank != -1){MaxRank = rank; HighestRankFile = f.getPath();};
        };
        System.out.println(MaxRank);
        return HighestRankFile;
    };
    
    public void highTest(){
    String name = "Mich";
    String gender = "M";
    System.out.println(yearOfHighestRank(name, gender));
    }
    
    public double getAverageRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double AverRank = 0.0;
        int k = 0;
        for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        int rank = getRankFromFile(fr, name, gender);
        if (rank == -1){System.out.println("File "+f.getPath()+" contains no name "+name); return -1;};
        k += 1;
        AverRank += rank;
        };
        AverRank = AverRank/k;
        return AverRank;
    };
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year, name, gender);
        if (rank == -1){return -1;};
        int k = 0;
        int sum = 0;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
	            k += 1;
                if (k == rank){return sum;};
                sum += Integer.parseInt(rec.get(2));
	           };
        };
        return -1;
    };
    
    public void beforeTest(){
    String name = "Isabella";
    String gender = "F";
    int year = 2012;
    System.out.println(getTotalBirthsRankedHigher(year, name, gender));
    }
}