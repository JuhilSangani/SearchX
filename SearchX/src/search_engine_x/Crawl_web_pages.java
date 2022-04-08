package search_engine_x;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class Crawl_web_pages {
	
	public static void main(String[] args) throws Exception {
		int c_depth = 1;
//		link_crawling("https://www.javatpoint.com/", c_depth); // Call the crawling function
	}

	// write array content into the given file
	public static void save_to_file(String f_name, String data) throws IOException {
		
		FileWriter wtr = new FileWriter("./HTML_files/" + f_name + ".html");
		wtr.write(data);
		wtr.close();
	}

	// crawl website with the given length upto given depth and save it in folder
	// with title as name of file
	public static void link_crawling(String c_url, int c_depth) throws Exception {

		// Current depth
		int crt_depth = 0;
		
		ArrayList<Integer> dpth = new ArrayList<Integer>();
		dpth.add(0);
		
		ArrayList<String> lnks = new ArrayList<String>();
		lnks.add(c_url); // Add link to the queue
		
		Set<String> viewed = new HashSet<String>();
		
		// Crawl pages according to the limit
		for (int i = 0; i < 75; i++) {

			if (dpth.get(i) > c_depth) {
				break;
			} // Exit the loop according to the depth
			
			String lnk = lnks.get(i);

			// skip the loop if the current link is already visited
			if (viewed.contains(lnk)) {
				continue;
			}
			
			viewed.add(lnk);
			System.out.println("Crawling : " + lnk);
			try {
				
				org.jsoup.nodes.Document dc = Jsoup.connect(lnk).get(); // Get the HTML document

				save_to_file(dc.title(), dc.html()); // Save the HTML files to the destination folder
				
				Elements page_lnks = dc.select("a[href]");
				crt_depth++;

				// add all hyperlink to the crawling list
				for (org.jsoup.nodes.Element pg : page_lnks) {
					lnks.add(pg.attr("abs:href"));
					dpth.add(crt_depth);
				}
			} catch (IOException e) {
				 System.out.println("Error:" + e);
			}
		}
	}
}
