import java.util.Map;
import java.util.Scanner;

import com.sun.net.httpserver.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Search {
	HttpExchange t;
	String search;
	File resultFile;
	FileWriter result;
	
	public Search(File resultFile, HttpExchange t) {
		this.resultFile = resultFile;
		this.t= t;
	}
	
	private void initResultFile() throws IOException {
		result = new FileWriter(resultFile);	
	}
	
	private void fnlsResultFile() throws IOException {
		result.flush();
		result.close();
	}
	
	public boolean doSearch() {
		File text;
		Scanner input;
		Map<String, String> params = WebServer.parseQuery(t.getRequestURI().getRawQuery());
		
    	if (!params.containsKey("searchString")) {
    		System.out.println("Got nothing");
        	return false;
        } else {
        	String word = params.get("searchString");
        	if (word.contains(" ")) {word = word.substring(0, word.indexOf(" "));}
    		System.out.println("Got " + params.get("searchString"));
    	
	    	try {
	    		initResultFile();
	    		
	    		result.write("<html>\n");
	    		result.write("<head>\n");
	    		result.write("<link rel=\"stylesheet\" href=\"style.css\" />\n");
	    		result.write("<title>" + word + " - results</title>\n");
	    		result.write("</head>\n");
	    		result.write("<body>\n");
	    		result.write("<table>\n");
	    		result.write("<tr><td>\n");
	    		result.write("<p class=\"miniTitle\">\n");
	    		result.write("<a class=\"invisibleLink\" href=\"/\">\n");
	    		result.write("<span class=\"blue\">H</span>\n");
	    		result.write("<span class=\"red\">u</span>\n");
	    		result.write("<span class=\"yellow\">n</span>\n");
	    		result.write("<span class=\"blue\">d</span>\n");
	    		result.write("<span class=\"green\">r</span>\n");
	    		result.write("<span class=\"red\">e</span>\n");
	    		result.write("<span class=\"yellow\">d</span>\n");
	    		result.write("</a></p></td>\n");
	    		result.write("<td>\n");
	    		result.write("<form method=\"get\" action=\"\">");
	    		result.write("<input class=\"search\" type=\"text\" name=\"searchString\" size=\"70\" />\n");
	    		result.write("<input class=\"search\" type=\"submit\" value=\"Search\" /></form>\n");
	    		result.write("</td></tr>\n");
	    		result.write("<tr><td></td>\n");
	    		result.write("<td>\n");
	    		result.write("<p>Found <span id = \"pageCount\"></span> web pages that contain the phrase \"" + word + "\".</p>\n");
	    		
	    		int count = 0;
	    		for (int index = 0; index < 100; index++) {
	    			if (findWord(index, word)) {
	    				count++;
	    				text = new File(".\\data\\" + index + ".txt");
	    				input = new Scanner(text);
	    				result.write("<p>" + count + ": <a target=\"_blank\" href = \"" + input.nextLine() + "\">" + input.nextLine() + "</a></p>\n");
	    			};
	    		}
				
	    		result.write("</td></tr></table>\n");
				result.write("<script>document.getElementById(\"pageCount\").innerHTML = \"" + count + "\";</script>\n");
	    		result.write("</body>\n");
				result.write("</html>\n");

				fnlsResultFile();
			} catch (IOException e) {e.printStackTrace();}
	    	return true;
        }
	}
	
	private boolean findWord(int num, String word) {
		Scanner input;
		boolean here = false;

		try {
			File text = new File(".\\data\\" + num + ".txt");
			input = new Scanner(text);

			while(input.hasNext()){
				if (word.toLowerCase().equals(input.next().toLowerCase())) {
					here = true;
				}
			}
			
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return here;
	}

}
