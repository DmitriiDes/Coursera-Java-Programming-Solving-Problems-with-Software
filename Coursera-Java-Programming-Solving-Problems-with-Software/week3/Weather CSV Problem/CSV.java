
/**
 * Write a description of CSV here.
 * 
 * @author Dmitrii Desiatkov 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSV {
    
public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    };
    
public CSVRecord lowestHourInFile(CSVParser parser, String field) {
        
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, field);
        }
        
        return lowestSoFar;
    };
    

    
public void printTheFileTemp(CSVParser parser) {
        
         for (CSVRecord currentRow : parser) {
            
         System.out.println(currentRow.get("DateUTC")+" "+currentRow.get("TemperatureF"));
         
        }
    };  
    
    public void testHottestInDay () {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("largest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    };
    
public void testLowestInDay () {
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHourInFile(fr.getCSVParser(), "TemperatureF");
        System.out.println("lowest temperature was " + lowest.get("TemperatureF") +
                   " at " + lowest.get("DateUTC"));
    };
    
public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    };
    
public CSVRecord lowestInManyDays(String field) {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = lowestHourInFile(fr.getCSVParser(), field);
            
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, field);
        }
        
        return lowestSoFar;
    };
    
public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    };
    
public CSVRecord getLowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar, String field) {
        
        if (lowestSoFar == null) {
           lowestSoFar = currentRow;
        }
        
        else {
            double currentField;
            double lowestField;
            try{
            currentField = Double.parseDouble(currentRow.get(field));}
            catch (NumberFormatException e){return lowestSoFar;}
            try{
            lowestField = Double.parseDouble(lowestSoFar.get(field));}
            catch (NumberFormatException e){return lowestSoFar;}            
            
            if (currentField < lowestField && currentField > -9000) {
              lowestSoFar = currentRow;
            };
        
        }
        return lowestSoFar;
    };
    
public String fileWithColdestTemperature(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String lowestFile = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHourInFile(fr.getCSVParser(), "TemperatureF");
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, "TemperatureF");
            if (lowestSoFar == currentRow){
            lowestFile = f.getPath();};
        }
        
        return lowestFile; 
       };
       
public String fileWithLowestHumidity(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String lowestFile = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHourInFile(fr.getCSVParser(), "Humidity");
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, "Humidity");
            if (lowestSoFar == currentRow){
            lowestFile = f.getPath();};
        }
        
        return lowestFile; 
       };       

public double averageFieldInFile(CSVParser parser, String field){
    double sumField = 0;
    int k = 0;
    for (CSVRecord currentRow : parser) {
        k +=1; 
        sumField += Double.parseDouble(currentRow.get(field));
        }
    return sumField/k;
    };
    
    public void testaverField(){
    FileResource fr = new FileResource();
    System.out.println("Average temperature in file is "+averageFieldInFile(fr.getCSVParser(), "TemperatureF"));
    };

public double averageFieldInFileWithParam(CSVParser parser, String field, int value, String param){
    double sumField = 0;
    int k = 0;
    for (CSVRecord currentRow : parser) {
        if (Double.parseDouble(currentRow.get(param)) >= value){
        k +=1; 
        sumField += Double.parseDouble(currentRow.get(field));
    };
        }
    return sumField/k;
    };
    
    public void testaverageFieldInFileWithParam(){
    FileResource fr = new FileResource();
    System.out.println("Average temperature in file is "+averageFieldInFileWithParam(fr.getCSVParser(), "TemperatureF", 80, "Humidity"));
    };
    
public void testFileWithColdestTemperature(){
        String lowestFile = fileWithColdestTemperature();
        System.out.println("the file with lowest temperature is:"+lowestFile);
        
        FileResource fr = new FileResource(lowestFile);
        CSVRecord lowest = lowestHourInFile(fr.getCSVParser(), "TemperatureF");
        System.out.println("lowest temperature was " + lowest.get("TemperatureF") +
                   " at " + lowest.get("TimeEST"));
        System.out.println("All the Temperatures on the coldest day were:");
        printTheFileTemp(fr.getCSVParser());
        
       };

public void testFileWithLowestHumidity(){
       String lowestFile = fileWithLowestHumidity();
       System.out.println("the file with lowest humidity is:"+lowestFile);
       FileResource fr = new FileResource(lowestFile);
        CSVRecord lowest = lowestHourInFile(fr.getCSVParser(), "Humidity");
        System.out.println("lowest humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
        System.out.println("All the Humidity on the driest day were:");
        printTheFileTemp(fr.getCSVParser());
};
       
public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    };
}
