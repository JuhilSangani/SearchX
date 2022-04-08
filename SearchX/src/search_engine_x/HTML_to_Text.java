package search_engine_x;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTML_to_Text {
	public static void convert_file(String in_path, String ot_path) {

		String str;
		String fileName = "";
		try {
			// creating a file to store html pages
			File address = new File(in_path);
//			HTML_to_Text obj = new HTML_to_Text();
			// array of files
			File[] myfiles = address.listFiles();
			
			// for every html page in array of files
			for (File f : myfiles) {
				System.out.println(f);
				Document doc = Jsoup.parse(f, "utf-8");
				str = doc.text();
				fileName = f.getName();
				fileName = fileName.split("\\.", 2)[0];
				fileName = fileName + ".txt";
				BufferedWriter bfwriter = new BufferedWriter(new FileWriter(ot_path + fileName));
				bfwriter.write(str);
				bfwriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
