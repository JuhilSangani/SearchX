package search_engine_x;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Search_keyword {
	public String readFileContents(String fileName) throws IOException {
		byte[] byteFile = Files.readAllBytes(Paths.get("Text_files/" + fileName));
		String f_content = new String(byteFile, StandardCharsets.US_ASCII);
		return f_content;
	}

	public static int pRV = 1;

	public static void pRank() throws IOException {
		Scanner sc = new Scanner(System.in);
		Search_keyword patternSearching = new Search_keyword();
		System.out.println("\nPlease Enter a string to search : ");
		String u_query = sc.nextLine();

		// Time Count
		double s_time = System.currentTimeMillis();
		Hashtable<String, Integer> pRank = patternSearching.searchPatterns(u_query); // Call the searchpattern function
		double e_time = System.currentTimeMillis();

		System.out.println("\nWait a min I am not Google....\n");
		
		// Count the occurrence and initializing it to 0
		int total_occ = 0;
		for (int occ : pRank.values())
			total_occ += occ;

		System.out.println("matches found in " + pRank.size() + " files.\n");
		System.out.println("Index	Occurrence   file");

		//this is the sorting function for sorting the pages according to the occurrence.
		Map<String, Integer> sortPages = pRank.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())//by default sorting function to sort the files
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));//sorting the files
		sortPages.forEach((key, value) -> {
			System.out.println(String.format("%02d", pRV) + "	" + String.format("%02d", value) + "          " + key);//returning the sorted files
			pRV++;//increment the ranking counter 
		});
		System.out.println("\n\nGot these much results in " + (e_time - s_time)/1000 + " Seconds");
	}

	public Hashtable<String, Integer> searchPatterns(String patToFind) throws IOException {
		patToFind = patToFind.toLowerCase();//to lower case because of perfect result
		Hashtable<String, Integer> pRank = new Hashtable<String, Integer>();
		File finalPath = new File("Text_files/");//inputing the file
		String f_nameList[] = finalPath.list();
		String txtJoin = "";//initializing an empty string
		for (int i = 0; i < f_nameList.length; i++) {//lopping till the end of the file
			String f_name = f_nameList[i];
			txtJoin = readFileContents(f_name);//reading the content of the file
			txtJoin = txtJoin.toLowerCase();//converting it to the lower case
			Pattern p = Pattern.compile(patToFind);//inputing the user defined value to find in the file 
			Matcher m = p.matcher(txtJoin);//comparing the file with the pattern using matcher method
			while (m.find()) {
				pRank.merge(f_name, 1, Integer::sum);//adding the file if the pattern is found
			}
		}
		return pRank;
	}
}
