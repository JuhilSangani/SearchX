package search_engine_x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Spell_check {
	public static int editDistance(String w1, String w2) {
		int l1 = w1.length();
		int l2 = w2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] ed = new int[l1 + 1][l2 + 1];
	 
		for (int i = 0; i <= l1; i++) {
			ed[i][0] = i;
		}
	 
		for (int j = 0; j <= l2; j++) {
			ed[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < l1; i++) {
			char ch1 = w1.charAt(i);
			for (int j = 0; j < l2; j++) {
				char ch2 = w2.charAt(j);
	 
				//if last two chars equal
				if (ch1 == ch2) {
					//update dp value for +1 length
					ed[i + 1][j + 1] = ed[i][j];
				} else {
					int replace = ed[i][j] + 1;
					int insertion = ed[i][j + 1] + 1;
					int deletion = ed[i + 1][j] + 1;
	 
					int minimum = replace > insertion ? insertion : replace;
					minimum = deletion > minimum ? minimum : deletion;
					ed[i + 1][j + 1] = minimum;
				}
			}
		}
	 
		return ed[l1][l2];
	}
	
	// Spell check
	public static Set<String> spellCheck(String pathTxt, String find) throws IOException
	{
		ArrayList<String> tkn = new ArrayList<String>(); 
		tkn = findTokens(pathTxt);
		ArrayList<String> sgn= new ArrayList<String>();
		
		// Loop to to count edit-distance
		for(int i=0; i < tkn.size(); i++)
		{
			int editD = editDistance(tkn.get(i), find);
			if(editD == 0) {
				break;
			}
			// loop to seperate the word 
			else if(editD==1) {				
				if(!(tkn.get(i).contains(".") || tkn.get(i).contains(",") || tkn.get(i).contains(" ")))
				{
					sgn.add(tkn.get(i));
				}
			}
			
		}
		Set<String> spellCheck = new HashSet<>(sgn);
		return spellCheck;
	}
	
	
	
	
	public static ArrayList<String> findTokens(String pathTxt) throws IOException
	{
		String str = "";
		ArrayList<String> tkn = new ArrayList<String>();
		File f_name = new File(pathTxt);
		File[] numberofF = f_name.listFiles();
		String input = "";
		for(File f : numberofF){
			//System.out.print(f.getName());
			BufferedReader br = new BufferedReader(new FileReader(f));   
			while((str = br.readLine()) != null) 
		   		   input =input + str;
		   	  StringTokenizer st = new StringTokenizer(input);  
		        while (st.hasMoreTokens())   
		      	  tkn.add(st.nextToken().toLowerCase());
			 br.close();
			 
		}
		return tkn;
	}
}
