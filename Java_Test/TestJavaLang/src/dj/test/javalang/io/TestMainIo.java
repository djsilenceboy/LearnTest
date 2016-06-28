
package dj.test.javalang.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;

import dj.test.common.Utils;

public class TestMainIo
{
	String fileNameAnsi_Eng = "etc/data/SampleWords_ANSI_Eng.txt";
	String fileNameUtf8_Eng = "etc/data/SampleWords_UTF8_Eng.txt";
	String fileNameUtf16_Eng = "etc/data/SampleWords_UTF16_Eng.txt";

	String fileNameAnsi_Chs = "etc/data/SampleWords_ANSI_Chs.txt";
	String fileNameUtf8_Chs = "etc/data/SampleWords_UTF8_Chs.txt";
	String fileNameUtf16_Chs = "etc/data/SampleWords_UTF16_Chs.txt";

	String[] fileNames = {fileNameAnsi_Eng, fileNameUtf8_Eng, fileNameUtf16_Eng, fileNameAnsi_Chs, fileNameUtf8_Chs, fileNameUtf16_Chs};

	public void moveFile(){
		String[] srcFileNames = new String[2];
		String[] dstFileNames = new String[2];

		srcFileNames[0] = "/etc/data/src/";
		srcFileNames[1] = "sample.txt";
		dstFileNames[0] = "/etc/data/dst/";
		dstFileNames[1] = "sample.txt";

		File srcFile = new File(srcFileNames[0], srcFileNames[1]);
		File dstFile = new File(dstFileNames[0], dstFileNames[1]);

		System.out.println("Src file = " + srcFile.getAbsolutePath());
		System.out.println("Src file = " + srcFile.getPath());
		System.out.println("Src file = " + srcFile.getParent());
		System.out.println("Src file = " + srcFile.getName());

		System.out.println("Dst file = " + dstFile.getAbsolutePath());
		System.out.println("Dst file = " + dstFile.getPath());
		System.out.println("Dst file = " + dstFile.getParent());
		System.out.println("Dst file = " + dstFile.getName());

		boolean result = srcFile.renameTo(dstFile);

		System.out.println("Result = " + result);
	}

	public void testFileInputStream1(){
		System.out.println("Test = FileInputStream");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (InputStream fis = new FileInputStream(fileName)) {
				byte[] buffer = new byte[100];
				int size = fis.read(buffer);

				byte[] result = Arrays.copyOf(buffer, size);

				System.out.println("Size = " + size);
				System.out.println("Content Hex = " + Utils.arrayToHexString(result));
				System.out.println("Content Org = " + Arrays.toString(result));
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileInputStream2(){
		System.out.println("Test = BufferedInputStream(FileInputStream)");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
				byte[] buffer = new byte[100];
				int size = bis.read(buffer);

				byte[] result = Arrays.copyOf(buffer, size);

				System.out.println("Size = " + size);
				System.out.println("Content Hex = " + Utils.arrayToHexString(result));
				System.out.println("Content Org = " + Arrays.toString(result));
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileInputStream3(){
		System.out.println("Test = DataInputStream(BufferedInputStream(FileInputStream))");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
				byte[] buffer = new byte[100];
				int size = dis.read(buffer);

				byte[] result = Arrays.copyOf(buffer, size);

				System.out.println("Size = " + size);
				System.out.println("Content Hex = " + Utils.arrayToHexString(result));
				System.out.println("Content Org = " + Arrays.toString(result));
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileReader1(){
		System.out.println("Test = FileReader");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (FileReader fr = new FileReader(fileName)) {
				char[] buffer = new char[100];
				int size = fr.read(buffer);

				char[] result = Arrays.copyOf(buffer, size);

				System.out.println("Size = " + size);
				System.out.println("Content Hex = " + Utils.arrayToHexString(result));
				System.out.println("Content Org = " + Arrays.toString(result));

			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileReader2(){
		System.out.println("Test = BufferedReader(FileReader)");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				String line;

				while ((line = br.readLine()) != null) {
					System.out.println("Line (" + line.length() + ") = " + line);
				}
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileReader3(){
		System.out.println("Test = BufferedReader(InputStreamReader(FileInputStream,UTF-8))");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
				String line;

				while ((line = br.readLine()) != null) {
					System.out.println("Line (" + line.length() + ") = " + line);
				}
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testFileReader4(){
		System.out.println("Test = BufferedReader(InputStreamReader(FileInputStream,UTF-16))");
		System.out.println("--------------------");

		for (String fileName : fileNames) {
			System.out.println("File name = " + fileName);

			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-16"))) {
				String line;

				while ((line = br.readLine()) != null) {
					System.out.println("Line (" + line.length() + ") = " + line);
				}
			} catch (Exception e) {
				System.err.println("Exception = " + e);
			}

			System.out.println("--------------------");
		}
	}

	public void testOutputStream1(){
		System.out.println("Test = DataOutputStream(ByteArrayOutputStream)");
		System.out.println("--------------------");

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); DataOutputStream dos = new DataOutputStream(baos)) {
			dos.write(123);
			dos.writeShort(123);
			dos.writeInt(123);
			dos.writeLong(123);
			dos.writeFloat(123);
			dos.writeDouble(123);
			System.out.println("Content Hex = " + Utils.arrayToHexString(baos.toByteArray()));
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testFormatter(){
		System.out.println("Test = Formatter");
		System.out.println("--------------------");

		StringBuilder temp = new StringBuilder();
		Formatter fmt = new Formatter(temp);

		fmt.format("%s, It has %5.2f.", "Hello", 3.14f);

		System.out.println(temp.toString());
	}

	public static void main(String[] args){
		TestMainIo test = new TestMainIo();

		System.out.println("========================================");

		// test.moveFile();

		System.out.println("========================================");

		test.testFileInputStream1();

		System.out.println("========================================");

		test.testFileInputStream2();

		System.out.println("========================================");

		test.testFileReader1();

		System.out.println("========================================");

		test.testFileReader2();

		System.out.println("========================================");

		test.testFileReader3();

		System.out.println("========================================");

		test.testFileReader4();

		System.out.println("========================================");

		test.testOutputStream1();

		System.out.println("========================================");

		test.testFormatter();

		System.out.println("========================================");
	}
}
