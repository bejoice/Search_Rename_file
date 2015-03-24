package example;

import java.io.*;
import java.util.*;


public class SearchFile {

	/**
	 * @param args
	 */
	//Apparently this is supposed to be simple but I can't seem to get it - "Write a program that searches for a particular file name in a given directory." I've found a few examples of a hardcoded filename and directory, but I need both the dir and file name to be as entered by the user.

	public static void main(String[] args) {/*
        //String fileName = args[0]; // For the filename declaration
        String fileName = "Vnlsc03"; // For the filename declaration
        String directory = "C:\\Integral_source_code\\Life7.7-M1.16-M_20140314";     
        boolean found;

        File dir = new File(directory);

        File[] matchingFiles = dir.listFiles(new FilenameFilter() {
               public boolean accept(File dir, String fileName) {
                   return true;
                }
        });
        System.out.println("FIle path "+matchingFiles[0]);

      */
/*			SearchFile ff = new SearchFile();	   
	       String fileName = "Vnlsc03.java";
	        String directory = "C:\\Integral_source_code\\Life7.7-M1.16-M_20140314";
	        System.out.println("Start Search");
	        ff.findFile(fileName,new File(directory));
	        System.out.println("End Search");*/
		SearchFile ff = new SearchFile();	
		String directory = "C:\\Users\\bchemmannoor\\Desktop\\Latest VPM files_20140507";
		String fileName = "abc.txt";
		File[] folderList = listf(directory);
		//System.out.println("No of Subfolders "+folderList.length);
		for(File folder:folderList){
			System.out.println("Folder List =>"+folder.toString());
			 ff.findFile(fileName,new File(folder.toString()));
		}
	}
	
	public void findFile(String name,File file)
    {
        File[] list = file.listFiles();
        if(list!=null)
        for (File fil : list)
        {
            if (fil.isDirectory())
            {
                findFile(name,fil);
            }
            else if (name.equalsIgnoreCase(fil.getName()))
            {
                System.out.println(fil.getParentFile()+"\\"+name);
            }
        }
    }

	public static File[] listf(String directoryName) {

	    // .............list file
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();

	    for (File file : fList) {
	        if (file.isFile()) {
	            System.out.println(file.getAbsolutePath());
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath());
	        }
	    }
	    //System.out.println(fList);
	    return fList;
	} 
}
