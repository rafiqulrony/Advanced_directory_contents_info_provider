package singlePack;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

class Traversar extends SimpleFileVisitor<Path> {

	ArrayList<Path> fileList = new ArrayList<Path>();
	ArrayList<Path> directoryList = new ArrayList<Path>();

	@Override
	public FileVisitResult visitFile(Path p, BasicFileAttributes attr)
	{

		fileList.add(p);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path p, BasicFileAttributes attr)
	{

		directoryList.add(p);
		return FileVisitResult.CONTINUE;
	}

}