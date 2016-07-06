
package dj.test.javalang.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;

public class TestMainPathV7
{
	String fileNameRelative = "etc/data/SampleWords_ANSI_Eng.txt";
	String fileNameAbosolute = "X:\\TestJavaLang\\etc\\data\\SampleWords_ANSI_Eng.txt";
	String directoryNameRelative = "etc/data";
	String directoryAbosolute = "X:\\Java_Test\\TestJavaLang\\etc\\data";

	String directoryName = "etc/data";

	String[] fileNames = {fileNameRelative, fileNameAbosolute, directoryNameRelative, directoryAbosolute};
	String[] pathNames = {fileNameRelative, directoryNameRelative};

	String[] rawFileNames = {"etc/../data/SampleWords.txt", "etc/./data/SampleWords.txt"};

	public void testPathInfo1(){
		System.out.println("Test = Path info");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try {
				Path path = Paths.get(fileName);

				System.out.println("toString = " + path.toString());
				System.out.println("getFileName = " + path.getFileName());
				System.out.println("getNameCount = " + path.getNameCount());
				System.out.println("getRoot = " + path.getRoot());
				System.out.println("getParent = " + path.getParent());
				System.out.println("getName(0) = " + path.getName(0));
				System.out.println("subpath(0, 2) = " + path.subpath(0, 2));
				System.out.println("toAbsolutePath = " + path.toAbsolutePath());
				System.out.println("isDirectory = " + Files.isDirectory(path));
				System.out.println("isExecutable = " + Files.isExecutable(path));
				System.out.println("isReadable = " + Files.isReadable(path));
				System.out.println("isRegularFile = " + Files.isRegularFile(path));
				System.out.println("isSymbolicLink = " + Files.isSymbolicLink(path));
				System.out.println("isWritable = " + Files.isWritable(path));

				if (Files.exists(path)) {
					System.out.println("isHidden = " + Files.isHidden(path));
					System.out.println("toRealPath = " + path.toRealPath());
					System.out.println("getLastModifiedTime = " + Files.getLastModifiedTime(path));
					System.out.println("getOwner = " + Files.getOwner(path));
				}
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileAttributes(){
		System.out.println("Test = File Attributes");
		System.out.println("--------------------");

		for (String fileName : pathNames) {
			System.out.println("File name = " + fileName);

			try {
				Path path = Paths.get(fileName);

				{
					BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);

					System.out.println("Basic: creationTime = " + attributes.creationTime());
					System.out.println("Basic: fileKey = " + attributes.fileKey());
					System.out.println("Basic: isDirectory = " + attributes.isDirectory());
					System.out.println("Basic: isOther = " + attributes.isOther());
					System.out.println("Basic: isRegularFile = " + attributes.isRegularFile());
					System.out.println("Basic: isSymbolicLink = " + attributes.isSymbolicLink());
					System.out.println("Basic: lastAccessTime = " + attributes.lastAccessTime());
					System.out.println("Basic: lastModifiedTime = " + attributes.lastModifiedTime());
					System.out.println("Basic: size = " + attributes.size());
				}

				{
					DosFileAttributes attributes = Files.readAttributes(path, DosFileAttributes.class);

					System.out.println("Dos: isArchive = " + attributes.isArchive());
					System.out.println("Dos: isHidden = " + attributes.isHidden());
					System.out.println("Dos: isReadOnly = " + attributes.isReadOnly());
					System.out.println("Dos: isSystem = " + attributes.isSystem());
				}

				if (false) {
					PosixFileAttributes attributes = Files.readAttributes(path, PosixFileAttributes.class);

					System.out.println("Posix: group = " + attributes.group());
					System.out.println("Posix: owner = " + attributes.owner());
					System.out.println("Posix: permissions = " + attributes.permissions());
				}
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileAttributeView(){
		System.out.println("Test = FileAttributeView");
		System.out.println("--------------------");

		for (String fileName : pathNames) {
			System.out.println("File name = " + fileName);

			try {
				Path path = Paths.get(fileName);
				BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);

				System.out.println("Basic View: name = " + view.name());

				BasicFileAttributes attributes = view.readAttributes();

				System.out.println("Basic: creationTime = " + attributes.creationTime());
				System.out.println("Basic: fileKey = " + attributes.fileKey());
				System.out.println("Basic: isDirectory = " + attributes.isDirectory());
				System.out.println("Basic: isOther = " + attributes.isOther());
				System.out.println("Basic: isRegularFile = " + attributes.isRegularFile());
				System.out.println("Basic: isSymbolicLink = " + attributes.isSymbolicLink());
				System.out.println("Basic: lastAccessTime = " + attributes.lastAccessTime());
				System.out.println("Basic: lastModifiedTime = " + attributes.lastModifiedTime());
				System.out.println("Basic: size = " + attributes.size());
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testNormalize1(){
		System.out.println("Test = Normalize");
		System.out.println("--------------------");

		for (String fileName : rawFileNames) {
			System.out.println("File name = " + fileName);

			try {
				Path path = Paths.get(fileName);

				System.out.println("normalize = " + path.normalize());
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testResolve1(){
		System.out.println("Test = Resolve");
		System.out.println("--------------------");

		try {
			Path path = Paths.get(directoryName);

			System.out.println("resolve(TestSubPath) = " + path.resolve("../TestSubPath"));
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");
	}

	public void testCompare(){
		System.out.println("Test = Compare");
		System.out.println("--------------------");

		try {
			Path path1 = Paths.get(fileNameRelative);
			Path path2 = Paths.get(fileNameRelative);

			System.out.println("Source file = " + path1);
			System.out.println("Destination file = " + path2);

			System.out.println("isSameFile = " + Files.isSameFile(path1, path2));
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestMainPathV7 test = new TestMainPathV7();

		System.out.println("========================================");

		test.testPathInfo1();

		System.out.println("========================================");

		test.testFileAttributes();

		System.out.println("========================================");

		test.testFileAttributeView();

		System.out.println("========================================");

		test.testNormalize1();

		System.out.println("========================================");

		test.testResolve1();

		System.out.println("========================================");

		test.testCompare();

		System.out.println("========================================");
	}
}
