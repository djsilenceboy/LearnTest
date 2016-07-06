
package dj.test.javalang.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class TestMainNioV7
{
	String fileNameRelative = "etc/data/SampleWords_ANSI_Eng.txt";
	String directoryNameRelative = "etc/data";

	String directoryName = "etc/data";

	String[] pathNames = {fileNameRelative, directoryNameRelative};

	String[] rawFileNames = {"etc/../data/SampleWords.txt", "etc/./data/SampleWords.txt"};

	String[] srcFileNames = {"target/data/src/", "SampleToMove2.txt"};
	String[] dstFileNames = {"target/data/dst/", "SampleMoved2.txt", "SampleLines_readAllBytes.txt", "Sample_readAllLines.txt", "Sample_newBuffered.txt",
	                         "Sample_newStream.txt"};

	public void testFileSystems1(){
		System.out.println("Test = FileSystems");
		System.out.println("--------------------");

		try {
			Path path = FileSystems.getDefault().getPath(".");
			System.out.println("Default = " + path);
			System.out.println("toRealPath = " + path.toRealPath());
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");
	}

	public void testFileStore1(){
		System.out.println("Test = File store");
		System.out.println("--------------------");

		for (String fileName : pathNames) {
			System.out.println("File name = " + fileName);

			try {
				Path path = Paths.get(fileName);
				FileStore fileStore = Files.getFileStore(path);

				System.out.println("File store = " + fileStore);
				System.out.println("getTotalSpace (GB) = " + fileStore.getTotalSpace() / 1024 / 1024 / 1024);
				System.out.println("getUnallocatedSpace (GB) = " + fileStore.getUnallocatedSpace() / 1024 / 1024 / 1024);
				System.out.println("getUsableSpace (GB) = " + fileStore.getUsableSpace() / 1024 / 1024 / 1024);
				System.out.println("isReadOnly = " + fileStore.isReadOnly());
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileStore2(){
		System.out.println("Test = File stores");
		System.out.println("--------------------");

		for (FileStore fileStore : FileSystems.getDefault().getFileStores()) {
			System.out.println("File store = " + fileStore);

			try {
				System.out.println("getTotalSpace (GB) = " + fileStore.getTotalSpace() / 1024 / 1024 / 1024);
				System.out.println("getUnallocatedSpace (GB) = " + fileStore.getUnallocatedSpace() / 1024 / 1024 / 1024);
				System.out.println("getUsableSpace (GB) = " + fileStore.getUsableSpace() / 1024 / 1024 / 1024);
				System.out.println("isReadOnly = " + fileStore.isReadOnly());
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testCopyFile(){
		System.out.println("Test = Copy file");
		System.out.println("--------------------");

		try {
			Path path1 = Paths.get(fileNameRelative);
			Path path2 = Paths.get(srcFileNames[0], srcFileNames[1]);

			System.out.println("Source file = " + path1);
			System.out.println("Destination file = " + path2);

			Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testMoveFile(){
		System.out.println("Test = Move file");
		System.out.println("--------------------");

		try {
			Path path1 = Paths.get(srcFileNames[0], srcFileNames[1]);
			Path path2 = Paths.get(dstFileNames[0], dstFileNames[1]);

			System.out.println("Source file = " + path1);
			System.out.println("Destination file = " + path2);

			Files.move(path1, path2, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testFileIO(){
		System.out.println("Test = File IO");

		Charset charset = Charset.forName("US-ASCII");
		Path path1 = Paths.get(fileNameRelative);
		System.out.println("Source file = " + path1);
		System.out.println("--------------------");

		try {
			System.out.println("Test = readAllBytes / write bytes");

			Path path2 = Paths.get(dstFileNames[0], dstFileNames[2]);
			System.out.println("Destination file = " + path2);

			byte[] buffer = Files.readAllBytes(path1);

			Files.write(path2, buffer);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try {
			System.out.println("Test = readAllLines / write lines");

			Path path2 = Paths.get(dstFileNames[0], dstFileNames[3]);
			System.out.println("Destination file = " + path2);

			List<String> buffer = Files.readAllLines(path1, charset);

			Files.write(path2, buffer, charset);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try {
			System.out.println("Test = newBufferedReader / newBufferedWriter");

			Path path2 = Paths.get(dstFileNames[0], dstFileNames[4]);
			System.out.println("Destination file = " + path2);

			try (BufferedReader reader = Files.newBufferedReader(path1, charset); BufferedWriter writer = Files.newBufferedWriter(path2, charset)) {
				String line = null;

				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.write("\r\n");
				}
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try {
			System.out.println("Test = newInputStream / newOutputStream");

			Path path2 = Paths.get(dstFileNames[0], dstFileNames[5]);
			System.out.println("Destination file = " + path2);

			try (InputStream in = Files.newInputStream(path1);
			        InputStreamReader reader = new InputStreamReader(in);
			        OutputStream out = Files.newOutputStream(path2);
			        OutputStreamWriter writer = new OutputStreamWriter(out)) {
				char[] buffer = new char[10];
				int batchSize = 0;

				while ((batchSize = reader.read(buffer)) > 0) {
					if (batchSize == buffer.length) {
						writer.write(buffer);
					} else {
						char[] temp = Arrays.copyOf(buffer, batchSize);
						writer.write(temp);
					}
				}

				writer.flush();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");
	}

	public void testTempFile(){
		System.out.println("Test = Create temp file");
		System.out.println("--------------------");

		String tempFileFolder = "target/data/";
		String tempFilePrefix = "zzz_";
		String tempFilePostfix = ".tmp";

		System.out.println("tempFileFolder = " + tempFileFolder);
		System.out.println("tempFilePrefix = " + tempFilePrefix);
		System.out.println("tempFilePostfix = " + tempFilePostfix);

		try {
			Path path1 = Paths.get(tempFileFolder);
			Path path2 = Files.createTempFile(path1, tempFilePrefix, tempFilePostfix);

			System.out.println("temp file = " + path2);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestMainNioV7 test = new TestMainNioV7();

		System.out.println("========================================");

		test.testFileSystems1();

		System.out.println("========================================");

		test.testFileStore1();

		System.out.println("========================================");

		test.testFileStore2();

		System.out.println("========================================");

		test.testCopyFile();

		System.out.println("========================================");

		test.testMoveFile();

		System.out.println("========================================");

		test.testFileIO();

		System.out.println("========================================");

		test.testTempFile();

		System.out.println("========================================");
	}
}
