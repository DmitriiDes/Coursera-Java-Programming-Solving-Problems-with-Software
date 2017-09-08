/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
	public void listExporters(CSVParser parser, String exportOfInterest) {
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			//Check if it contains exportOfInterest
			if (export.contains(exportOfInterest)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}
    public String countryInfo (CSVParser parser, String country){
        for (CSVRecord record : parser) {
        
        if (record.get("Country").contains(country)){
            return record.get("Country")+":"+record.get("Exports")+":"+record.get("Value (dollars)");};
    };    
        return "NOT FOUND";
    }   
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
    for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			
			if (export.contains(exportItem1) && export.contains(exportItem2)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
    int k = 0;
        for (CSVRecord record : parser) {
        if (record.get("Exports").contains(exportItem)){k +=1;};
        }
    return k;}
    public void bigExporters(CSVParser parser, String amount){
    for (CSVRecord record : parser) {
        String val = record.get("Value (dollars)");
        if (val.length()>amount.length()){
        System.out.println(record.get("Country")+":"+":"+val);};
    }
}
	public void whoExportsCoffee() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
	}
	public void tester(){
	   FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       System.out.println(countryInfo(parser, "Malawi"));
       parser = fr.getCSVParser();
       listExportersTwoProducts(parser, "gold", "diamonds");
       parser = fr.getCSVParser();
       bigExporters(parser, "$999,999,999,999");
       //parser = fr.getCSVParser();
	   }
}
