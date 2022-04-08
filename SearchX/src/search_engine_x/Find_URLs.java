package search_engine_x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Find_URLs {
static ArrayList<String> urls= new ArrayList<>(); //final result
	
	public static void urlFinder(String pathTEXT, String searchString) { //
		String data="",line="";       //data= takes the text file  line= each line of the text
//		
		File location = new File(pathTEXT);  // Reads all files
		File[] listofFiles=location.listFiles();
		for(File f: listofFiles) { // for each file
			try {
				BufferedReader br = new BufferedReader( new FileReader(f)); // read one file at a time
				while((line=br.readLine()) != null) {
	 			   data=data+line; // take the whole content of the file in data variable
				}

				findUrls(data, searchString); // check the contents of the file, if any link in the file contains the searchString
				data = "";  //empty the variable
				br.close();  //close the file
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		System.out.println("\nLinks in documents : \n");
		for(String s: urls) { //
			System.out.println(s);	
		}
	}

	
	
	public static ArrayList<String> findUrls(String data, String searchString) {
		String checkword[];
		boolean flag;
		checkword=data.split(" "); //split the contents of the file by space and take them in an array
     
		for(String s: checkword) { // check each string in the file

			String pattern1 = "^(http://|https://|ftp://|www).*"+ searchString + ".*$"; // pattern for showing urls containing the searchword
			String pattern2 = "^(http://|https://|ftp://|www).*$"; //pattern for returning urls in the text files
//			flag=Pattern.matches("^(http://|https://|ftp://|www).*$", s);   

			flag=Pattern.matches(pattern1, s); //check with regex
			if(flag==true) { // found a match
				if(!urls.contains(s)) { // if the link already does not exist in the final result then add it
					urls.add(s);
				}
			}
		}  
		return urls;
	}
}
