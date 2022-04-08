package search_engine_x;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class SeachX_Engine {
public static void main(String[] args) throws Exception{
		
		System.out.println("\n\n------------Welcome to SearchX------------");
		String pathForHTML = "HTML_files";
		String pathForTxt = "Text_files/";
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			int a = 0;
			
			// Menu
			System.out.println("\n\n Which feature you want to use : \n  "
					+ "1. Crawling the Webpages\n  "
					+ "2. Convert HTML Pages to Text Pages\n  "
					+ "3. Search by keyword\n  "
					+ "4. Spell Check\n  "
					+ "5. Find URLs\n  "
					+ "6. Exit\n\nChoose the Options :");
			a = sc.nextInt();
			
			String search;
		

			// WEB Crawler
			if( a == 1) {
				int c_depth = 2;
				String s_lnk;
				System.out.println("\nPlease Enter the link that you want to crawl : ");
				s_lnk = sc.next();
				try {
				Crawl_web_pages.link_crawling(s_lnk, c_depth);
			
				}catch (IOException e) {
					continue;
//					 System.out.println("Error:" + e);
				}
			}
			
			// HTML to Text convert
			else if( a == 2) {
				HTML_to_Text.convert_file(pathForHTML, pathForTxt); //Code for HTML to TEXT Conversion
			}
			
			// Search Keyword, Rank, Occurrence, Time Count
			else if( a == 3) {
				Search_keyword.pRank();
			}
			
			// Suggest word, Spell check
			else if( a == 4) {
				Set<String> suggestedWords;
				String option;
				while(true) {
					System.out.println("\nEnter the keyword for spell check / word suggest : ");
					search = sc.next();
					search = search.toLowerCase();
					
					suggestedWords = Spell_check.spellCheck("Text_files_spell/", search);
					for(String s:suggestedWords) {
						search = s;
						System.out.println(s);
//						break;
					}
					if(suggestedWords.size() == 0)
					{
						break;
					}
					else {
						System.out.println("\nDid you mean ?(Y/N) ");
						option = sc.next().toLowerCase();
						if(option.equals("y"))
						{
							System.out.println("\nYour word changed to : " + search);
							break;
						}
						else
						{
							continue;
						}
						
					}
				}
			}
			
			
			// Find URLs
			else if( a == 5) {
				// This Code of block is used to fetch the URLs
				System.out.println("\nEnter the keyword : ");
				search = sc.next();
				search = search.toLowerCase();
				System.out.println("\nDo you want to check the associated links that contains keyword? (Y/N)");
				String reply = sc.next().toLowerCase();
				if(reply.equals("y"))
					Find_URLs.urlFinder(pathForTxt, search);
				else
					System.out.println("Thank you for using SearchX!");
			}
			
			// Exit
			else if( a == 6) {
				System.out.println("\nThank you \n  SearchX : Don't you think I am better than Google?");
				break;
			}
			else {
				System.out.println("please input a valid number!!");
			}
			
		}
	}
}
