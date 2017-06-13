package giz_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
	private File f;
	private FileReader reader ;
	private String sentence;
	
	public MyFileReader(String path){
		f = new File(path);
		try {
			
			reader = new FileReader(f);
			char[] buffer = new char[ (int)f.length() ];
		    reader.read(buffer);
		    sentence = new String(buffer);
		    reader.close();
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//	    System.out.println(" . read from the file:\n"+sentence); 
		System.out.println(">> The file is read into array");
	}

	public String getString(){
		return this.sentence;
	}
}
