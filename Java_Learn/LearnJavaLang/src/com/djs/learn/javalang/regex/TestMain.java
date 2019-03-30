
package com.djs.learn.javalang.regex;

import java.io.File;
import java.io.FileInputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain
{
	public void testFromFile(){
		try {
			File f = new File("TestInput.txt");
			FileInputStream fis = new FileInputStream(f);
			FileChannel fc = fis.getChannel();
			// ByteBuffer bb = ByteBuffer.allocate( (int)f.length() + 1 );
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, (int)fc.size());
			Charset cs = Charset.forName("8859_1");
			CharsetDecoder cd = cs.newDecoder();
			CharBuffer cb = cd.decode(bb);

			System.out.println("Source =\r\n" + cb);

			Pattern p = Pattern.compile("(^a*b*$)", Pattern.MULTILINE);
			// Pattern p = Pattern.compile( "(^a*b*$)" );
			Matcher m = p.matcher(cb);

			System.out.println("Pattern = " + p);

			while (m.find()) {
				System.out.println("-> (" + m.start() + "," + m.end() + ") " + m.group());
			}

			fc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test1(){
		try {
			String original = "(00,00)-(1,0.111)[1,3]qq(0,0.222)-(0.333,0.444)[2,4]ww(0,0.333)-(0.444,0.555)[3,5]123(0,0.444)-(0.555,0.666)[4,6]";
			String patten =
			                "\\(((\\d+)|((0)\\.(\\d{1,9}))|(1\\.0+)|(1)),((\\d+)|((0)\\.(\\d{1,9}))|(1\\.0+)|(1))\\)"
			                        + "\\-\\(((\\d+)|((0)\\.(\\d{1,9}))|(1\\.0+)|(1))" + ",((\\d+)|((0)\\.(\\d{1,9}))|(1\\.0+)|(1))\\)"
			                        + "\\[([1-9]+[0-9]*),([1-9]+[0-9]*)\\]";
			Pattern p2 = Pattern.compile(patten);
			Matcher m2 = p2.matcher(original);

			System.out.println("Source = " + original);
			System.out.println("Pattern = " + p2);

			while (m2.find()) {
				System.out.println("-> " + m2.group());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test2(){
		try {
			String source = "tel:+6512345678\ntel:+6523456789\ntel:6512345678\ntel:6523456789\ntel:34567890";

			System.out.println("Source = " + source);

			Pattern p = Pattern.compile("(^tel:\\+?(65)?[0-9]{8}$)", Pattern.MULTILINE);
			// Not matched.
			// Pattern p = Pattern.compile( "^tel:65[0-9]{7}$" );
			Matcher m = p.matcher(source);

			System.out.println("Pattern = " + p);

			while (m.find()) {
				System.out.println("-> (" + m.start() + "," + m.end() + ") " + m.group());
			}

			System.out.println("Replace = " + m.replaceFirst("ok"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test3(){
		try {
			String source = ",A-Field,\"\",\"B-Field\", ,\"CFie,ld\",,D\"Field,\"E\"\"F\"\"ield\",,";

			System.out.println("Source = " + source);

			// Pattern p = Pattern.compile("(\"[^\"]*\")|([^,]*)");
			Pattern p = Pattern.compile("(\".*?\")|([^,]*)");
			Matcher m = p.matcher(source);

			System.out.println("Pattern = " + p);

			while (m.find()) {
				System.out.println("-> (" + m.start() + "," + m.end() + ": " + m.group().length() + ") <" + m.group() + ">");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		TestMain tr = new TestMain();

		System.out.println("========================================");

		tr.test1();

		System.out.println("========================================");

		tr.test2();

		System.out.println("========================================");

		tr.test3();

		System.out.println("========================================");
	}
}
