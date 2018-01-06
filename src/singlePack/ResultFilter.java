package singlePack;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class ResultFilter {

	ArrayList<String> desiredList = new ArrayList<String>();
	DirectoryStream<Path> ds;
	String directoryStreamStatus = "successful";
	int numberOfFiles, numberOfDirectories;

	Traversar traversar;

	ResultFilter(Traversar traversar)
	{
		this.traversar = traversar;
	}

	/////////////// DIRECT-INDIRECT PART//////////////////

	void filterDirectIndirectHiddenFiles(Path selectedDirectory)
	{
		for (Path fp : traversar.fileList)
			try
			{
				if (Files.isHidden(fp))
				{
					if (fp.getParent().equals(selectedDirectory))
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
					else
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
								+ "    LOCATION=" + fp.getParent().toString());
					numberOfFiles++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterDirectIndirectNonHiddenFiles(Path selectedDirectory)
	{
		for (Path fp : traversar.fileList)
			try
			{
				if (Files.isHidden(fp) == false)
				{
					if (fp.getParent().equals(selectedDirectory))
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
					else
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
								+ "    LOCATION=" + fp.getParent().toString());
					numberOfFiles++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterDirectIndirectFiles(Path selectedDirectory)
	{
		for (Path fp : traversar.fileList)
		{
			try
			{
				if (fp.getParent().equals(selectedDirectory))
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
				else
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
			}
			catch (IOException e)
			{
				continue;
			}
			numberOfFiles++;
		}
	}

	void filterDirectIndirectHiddenDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (Files.isHidden(dp))
				{
					if (dp.getParent().equals(selectedDirectory))
						desiredList.add(dp.getFileName().toString());
					else
						desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
				}
			}
			catch (IOException e)
			{
				continue;
			}
		}

		numberOfDirectories = desiredList.size();
	}

	void filterDirectIndirectNonHiddenDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (Files.isHidden(dp) == false)
				{
					if (dp.getParent().equals(selectedDirectory))
						desiredList.add(dp.getFileName().toString());
					else
						desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
				}
			}
			catch (IOException e)
			{
				continue;
			}
		}

		numberOfDirectories = desiredList.size();
	}

	void filterDirectIndirectDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			if (dp.getParent().equals(selectedDirectory))
				desiredList.add(dp.getFileName().toString());
			else
				desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
		}

		numberOfDirectories = desiredList.size();
	}

	void filterDirectIndirectHiddenFilesDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (Files.isHidden(dp))
				{
					if (dp.getParent().equals(selectedDirectory))
						desiredList.add(dp.getFileName().toString());
					else
						desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
					numberOfDirectories++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
		}

		for (Path fp : traversar.fileList)
			try
			{
				if (Files.isHidden(fp))
				{
					if (fp.getParent().equals(selectedDirectory))
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
					else
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
								+ "    LOCATION=" + fp.getParent().toString());
					numberOfFiles++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterDirectIndirectNonHiddenFilesDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (Files.isHidden(dp) == false)
				{
					if (dp.getParent().equals(selectedDirectory))
						desiredList.add(dp.getFileName().toString());
					else
						desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
					numberOfDirectories++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
		}

		for (Path fp : traversar.fileList)
			try
			{
				if (Files.isHidden(fp) == false)
				{
					if (fp.getParent().equals(selectedDirectory))
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
					else
						desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
								+ "    LOCATION=" + fp.getParent().toString());
					numberOfFiles++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterDirectIndirectFilesDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			if (dp.getParent().equals(selectedDirectory))
				desiredList.add(dp.getFileName().toString());
			else
				desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
			numberOfDirectories++;
		}

		for (Path fp : traversar.fileList)
		{
			try
			{
				if (fp.getParent().equals(selectedDirectory))
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
				else
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
			}
			catch (IOException e)
			{
				continue;
			}
			numberOfFiles++;
		}
	}

	///////////////////// INDIRECT PART ////////////////////

	void filterIndirectHiddenFiles(Path selectedDirectory)
	{
		for (Path fp : traversar.fileList)
			try
			{
				if (fp.getParent().equals(selectedDirectory) == false && Files.isHidden(fp))
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
			}
			catch (IOException e)
			{
				continue;
			}

		numberOfFiles = desiredList.size();
	}

	void filterIndirectNonHiddenFiles(Path selectedDirectory)
	{
		for (Path fp : traversar.fileList)
			try
			{
				if (fp.getParent().equals(selectedDirectory) == false && Files.isHidden(fp) == false)
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
			}
			catch (IOException e)
			{
				continue;
			}

		numberOfFiles = desiredList.size();
	}

	void filterIndirectFiles(Path selectedDirectory)
	{
		for (Path fp : traversar.fileList)
			if (fp.getParent().equals(selectedDirectory) == false)
				try
				{
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
				}
				catch (IOException e)
				{
					continue;
				}

		numberOfFiles = desiredList.size();
	}

	void filterIndirectHiddenDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (dp.getParent().equals(selectedDirectory) == false && Files.isHidden(dp))
					desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
			}
			catch (IOException e)
			{
				continue;
			}
		}

		numberOfDirectories = desiredList.size();
	}

	void filterIndirectNonHiddenDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (dp.getParent().equals(selectedDirectory) == false && Files.isHidden(dp) == false)
					desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
			}
			catch (IOException e)
			{
				continue;
			}
		}

		numberOfDirectories = desiredList.size();
	}

	void filterIndirectDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			if (dp.getParent().equals(selectedDirectory) == false)
				desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
		}

		numberOfDirectories = desiredList.size();
	}

	void filterIndirectHiddenFilesDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (dp.getParent().equals(selectedDirectory) == false && Files.isHidden(dp))
				{
					desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
					numberOfDirectories++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
		}

		for (Path fp : traversar.fileList)
			try
			{
				if (fp.getParent().equals(selectedDirectory) == false && Files.isHidden(fp))
				{
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
					numberOfFiles++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterIndirectNonHiddenFilesDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			try
			{
				if (dp.getParent().equals(selectedDirectory) == false && Files.isHidden(dp) == false)
				{
					desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
					numberOfDirectories++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
		}

		for (Path fp : traversar.fileList)
			try
			{
				if (fp.getParent().equals(selectedDirectory) == false && Files.isHidden(fp) == false)
				{
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
					numberOfFiles++;
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterIndirectFilesDirectories(Path selectedDirectory)
	{
		for (int i = 1; i < traversar.directoryList.size(); i++)
		{
			Path dp = traversar.directoryList.get(i);
			if (dp.getParent().equals(selectedDirectory) == false)
			{
				desiredList.add(dp.getFileName().toString() + "    LOCATION=" + dp.getParent().toString());
				numberOfDirectories++;
			}
		}

		for (Path fp : traversar.fileList)
			if (fp.getParent().equals(selectedDirectory) == false)
			{
				try
				{
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes"
							+ "    LOCATION=" + fp.getParent().toString());
				}
				catch (IOException e)
				{
					continue;
				}
				numberOfFiles++;
			}
	}

	////////////////// DIRECT PART//////////////////////

	void filterDirectHiddenFiles(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path fp : ds)
			try
			{
				if (Files.isRegularFile(fp) && Files.isHidden(fp))
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
			}
			catch (IOException e)
			{
				continue;
			}
		numberOfFiles = desiredList.size();
	}

	void filterDirectNonHiddenFiles(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path fp : ds)
			try
			{
				if (Files.isRegularFile(fp) && Files.isHidden(fp) == false)
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
			}
			catch (IOException e)
			{
				continue;
			}
		numberOfFiles = desiredList.size();
	}

	void filterDirectFiles(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path fp : ds)
			if (Files.isRegularFile(fp))
				try
				{
					desiredList.add(fp.getFileName().toString() + "    SIZE=" + Files.size(fp) + " bytes");
				}
				catch (IOException e)
				{
					continue;
				}
		numberOfFiles = desiredList.size();
	}

	void filterDirectHiddenDirectories(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path dp : ds)
			try
			{
				if (Files.isDirectory(dp) && Files.isHidden(dp))
					desiredList.add(dp.getFileName().toString());
			}
			catch (IOException e)
			{
				continue;
			}
		numberOfDirectories = desiredList.size();
	}

	void filterDirectNonHiddenDirectories(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path dp : ds)
			try
			{
				if (Files.isDirectory(dp) && Files.isHidden(dp) == false)
					desiredList.add(dp.getFileName().toString());
			}
			catch (IOException e)
			{
				continue;
			}
		numberOfDirectories = desiredList.size();
	}

	void filterDirectDirectories(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path dp : ds)
			if (Files.isDirectory(dp))
				desiredList.add(dp.getFileName().toString());
		numberOfDirectories = desiredList.size();
	}

	void filterDirectHiddenFilesDirectories(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path fdp : ds)
			try
			{
				if (Files.isHidden(fdp))
				{
					if (Files.isRegularFile(fdp))
					{
						desiredList.add(fdp.getFileName().toString() + "    SIZE=" + Files.size(fdp) + " bytes");
						numberOfFiles++;
					}
					else if (Files.isDirectory(fdp))
					{
						desiredList.add(fdp.getFileName().toString());
						numberOfDirectories++;
					}
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterDirectNonHiddenFilesDirectories(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path fdp : ds)
			try
			{
				if (Files.isHidden(fdp) == false)
				{
					if (Files.isRegularFile(fdp))
					{
						desiredList.add(fdp.getFileName().toString() + "    SIZE=" + Files.size(fdp) + " bytes");
						numberOfFiles++;
					}
					else if (Files.isDirectory(fdp))
					{
						desiredList.add(fdp.getFileName().toString());
						numberOfDirectories++;
					}
				}
			}
			catch (IOException e)
			{
				continue;
			}
	}

	void filterDirectFilesDirectories(Path selectedDirectory)
	{
		try
		{
			ds = Files.newDirectoryStream(selectedDirectory);
		}
		catch (IOException e1)
		{
			directoryStreamStatus = "failed";
			return;
		}
		for (Path fdp : ds)
		{
			if (Files.isRegularFile(fdp))
			{
				try
				{
					desiredList.add(fdp.getFileName().toString() + "    SIZE=" + Files.size(fdp) + " bytes");
				}
				catch (IOException e)
				{
					continue;
				}
				numberOfFiles++;
			}
			else if (Files.isDirectory(fdp))
			{
				desiredList.add(fdp.getFileName().toString());
				numberOfDirectories++;
			}
		}
	}

	void closeDirectoryStream()
	{
		if (ds != null)
			try
			{
				ds.close();
			}
			catch (IOException e)
			{
				return;
			}
	}
}
