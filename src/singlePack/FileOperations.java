package singlePack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

class FileOperations {

	///////// CREATE A FILE REFERENCE FOR SAVING THE RESULT-LIST////////
	static boolean createTheFile(Path textFilePath)
	{
		try
		{
			textFilePath = Files.createFile(textFilePath);
		}

		catch (FileAlreadyExistsException e1)
		{
			JOptionPane.showMessageDialog(null, "A text file with this name already exists in the specified location. Please try with another name.", "File Already Exists", JOptionPane.ERROR_MESSAGE);
			return false;

		}
		catch (IOException e2)
		{
			JOptionPane.showMessageDialog(null, "File creation failed for some reason. Please Try again.", "File Creation Failed", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		/// when no error occurred and none of the catches above is catched:
		return true;

	}

	///// DELETE FILE/////
	static void deleteTheCreatedFile(Path textFilePath)
	{
		try
		{
			Files.deleteIfExists(textFilePath);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	//////////// CREATE THE FILE IN THE HARD-DRIVE & WRITE THE RESULT-LIST TO
	//////////// IT////////////

	static boolean writeToTheFile(Path selectedDirectoryPath, Path textFilePath, ArrayList<String> myList, int noOfF, int noOfD, String dOrIF, String dOrID, String hOrN, String fOrD)
	{

		String metaDataString;
		String sp = System.lineSeparator();

		if (fOrD == "directory")
			metaDataString = "||Source directory = " + selectedDirectoryPath.toString()
			+ sp+"||"+sp+"||" + "Directory statistics:"
			+ sp+"||" + "Number of directories = " + noOfD
			+ sp+"||" + "Direct or indirect? " + dOrID
			+ sp+"||" + "Normal or hidden? " + hOrN
			+ sp+"||"+sp+"||" + "(A directory below without a location means that the directory is the direct member of the selected directory.)"
			+ sp + "||________________________________________"
			+ sp + "The list is provided below:::"
			+ sp + "____________________________________________" + sp;
		else if (fOrD == "file")
			metaDataString = "||Source directory = " + selectedDirectoryPath.toString()
			+ sp+"||"+sp+"||" + "File statistics:"
			+ sp+"||" + "Number of files = " + noOfF
			+ sp+"||" + "Direct or indirect? " + dOrIF
			+ sp+"||" + "Normal or hidden? " + hOrN
			+ sp+"||"+sp+"||" + "(A file below without a location means that the file is the direct member of the selected directory.)"
			+ sp + "||________________________________________"
			+ sp + "The list is provided below:::"
			+ sp + "____________________________________________" + sp;
		else
			// if(fOrD=="both")
			metaDataString = "||Source directory = " + selectedDirectoryPath.toString()
			+ sp+"||"+sp+"||" + "Directory statistics:"
			+ sp+"||" + "Number of directories = " + noOfD
			+ sp+"||" + "Direct or indirect? " + dOrID
			+ sp+"||" + "Normal or hidden? " + hOrN
			+ sp+"||"+sp+"||" + "File statistics:"
			+ sp+"||" + "Number of files = " + noOfF
			+ sp+"||" + "Direct or indirect? " + dOrIF
			+ sp+"||" + "Normal or hidden? " + hOrN
			+ sp+"||"+sp+"||" + "(A directory/file below without a location means that the directory/file is the direct member of the selected directory.)"
			+ sp + "||________________________________________"
			+ sp + "The list is provided below:::"
			+ sp + "____________________________________________" + sp;

		try
		{
			BufferedWriter bw = Files.newBufferedWriter(textFilePath, StandardCharsets.UTF_8);

			bw.write(metaDataString);

			Collections.sort(myList);

			for (String item : myList)
			{
				bw.write(item);
				bw.newLine();
			}

			bw.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage()
					+ ". Please try again", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;

	}
}
