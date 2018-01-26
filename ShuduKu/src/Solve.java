import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
//this class is to solve sudoku
public class Solve {

	public static  List<String> load(){
		BufferedReader bufferedReader=null;
		FileReader fileReader=null;
		  JFileChooser chooser = new JFileChooser();
		    File file = null;
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       file = chooser.getSelectedFile();
		    }
		List<String> list=new ArrayList<String>();
		try {
			Class clazz=Class.forName("Solve");
			String filepath=clazz.getClassLoader().getResource("").getPath()+"file.txt";
			fileReader=new FileReader(file);
			bufferedReader=new BufferedReader(fileReader);
			String fieltext="";
			while((fieltext=bufferedReader.readLine())!=null){
				list.add(fieltext);
				System.out.println(fieltext);
			}
			System.out.println(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	  
	}

	public static void main(String[] args) {
		load();
	}

}
	
	
