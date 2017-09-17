package singlePack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

class FileOperations {

	///////// CREATE A FILE REFERENCE FOR SAVING THE RESULT-LIST////////
	static boolean createTheFile(Path textFilePath, String extension)
	{

		String pathString = textFilePath.toString();
		if (!pathString.endsWith(extension))
		{
			pathString = pathString + extension;
			textFilePath = Paths.get(pathString);
		}

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

		String firstLine;

		if (fOrD == "directory")
			firstLine = noOfD + dOrID + hOrN + " directories could be accessed in this directory: "
					+ selectedDirectoryPath.toString();
		else if (fOrD == "file")
			firstLine = noOfF + dOrIF + hOrN + " files could be accessed in this directory: "
					+ selectedDirectoryPath.toString();
		else
			// if(fOrD=="both")
			firstLine = noOfD + dOrID + hOrN + " directories & " + noOfF + dOrIF + hOrN
					+ " files could be accessed in this directory: " + selectedDirectoryPath.toString();

		try
		{
			BufferedWriter bw = Files.newBufferedWriter(textFilePath, StandardCharsets.UTF_8);

			bw.write(firstLine);
			bw.newLine();
			bw.write("(A DIRECTORY/FILE BELOW WITHOUT A LOCATION MEANS THAT THE DIRECTORY/FILE IS THE DIRECT MEMBER OF THE SELECTED DIRECTORY.)");
			bw.newLine();
			bw.newLine();

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