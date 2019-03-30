
package com.djs.learn.javalang.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Iterator;

public class TestVisitV7
{
	String directoryName = "etc";

	public void testDirectory(){
		System.out.println("Test = Directory");

		Path path = Paths.get(directoryName);
		System.out.println("Path = " + path);
		System.out.println("--------------------");

		try {
			Iterator<Path> iterator = path.iterator();

			while (iterator.hasNext()) {
				System.out.println("-> " + iterator.next());
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path item : stream) {
				System.out.println("-> " + item);
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*{ANSI,UTF8}*")) {
			for (Path item : stream) {
				System.out.println("-> " + item);
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*{ANSI,UTF8}*");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path item : stream) {
				if (matcher.matches(item.getFileName())) {
					System.out.println("-> " + item);
				}
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testVisit1(){
		try {
			Path path = Paths.get(directoryName);
			Files.walkFileTree(path, new PathLogger());
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testVisit2(){
		try {
			Path path = Paths.get(directoryName);
			Files.walkFileTree(path, new HashSet<FileVisitOption>(), 1, new PathLogger());
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestVisitV7 test = new TestVisitV7();

		System.out.println("========================================");

		test.testDirectory();

		System.out.println("========================================");

		test.testVisit1();

		System.out.println("========================================");

		test.testVisit2();

		System.out.println("========================================");
	}
}

class PathLogger implements FileVisitor<Path>
{
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
		System.out.println("--------------------");
		System.out.println("preVisitDirectory = " + dir);

		System.out.println("Basic: creationTime = " + attrs.creationTime());
		System.out.println("Basic: fileKey = " + attrs.fileKey());
		System.out.println("Basic: isDirectory = " + attrs.isDirectory());
		System.out.println("Basic: isOther = " + attrs.isOther());
		System.out.println("Basic: isRegularFile = " + attrs.isRegularFile());
		System.out.println("Basic: isSymbolicLink = " + attrs.isSymbolicLink());
		System.out.println("Basic: lastAccessTime = " + attrs.lastAccessTime());
		System.out.println("Basic: lastModifiedTime = " + attrs.lastModifiedTime());

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
		System.out.println("--------------------");
		System.out.println("visitFile = " + file);

		System.out.println("Basic: creationTime = " + attrs.creationTime());
		System.out.println("Basic: fileKey = " + attrs.fileKey());
		System.out.println("Basic: isDirectory = " + attrs.isDirectory());
		System.out.println("Basic: isOther = " + attrs.isOther());
		System.out.println("Basic: isRegularFile = " + attrs.isRegularFile());
		System.out.println("Basic: isSymbolicLink = " + attrs.isSymbolicLink());
		System.out.println("Basic: lastAccessTime = " + attrs.lastAccessTime());
		System.out.println("Basic: lastModifiedTime = " + attrs.lastModifiedTime());

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException{
		System.out.println("--------------------");
		System.out.println("visitFileFailed = " + file);

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException{
		System.out.println("--------------------");
		System.out.println("postVisitDirectory = " + dir);

		return FileVisitResult.CONTINUE;
	}
}
