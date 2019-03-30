
package com.djs.learn.javalang.unicode;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class ChineseCodeTest
{
	// US-ASCII, ISO-8859-1, GB2312, UTF-8, UTF-16, UTF-16BE

	public static boolean isCharset(String charset, String v){
		byte bytearray[] = v.getBytes();

		CharsetDecoder d = Charset.forName(charset).newDecoder();

		try {
			CharBuffer r = d.decode(ByteBuffer.wrap(bytearray));
			r.toString();
		} catch (CharacterCodingException e) {
			return false;
		}
		return true;
	}

	public static boolean isCharset2(String charset, String v){
		CharsetEncoder asciiEncoder = Charset.forName(charset).newEncoder(); // or "ISO-8859-1" for ISO Latin 1

		return asciiEncoder.canEncode(v);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		try {
			System.out.println("----------------------------------------");

			System.out.println("Default Charset = " + java.nio.charset.Charset.defaultCharset().name());

			System.out.println("----------------------------------------");

			String original = "abc: �¼ף���ŵ��";
			String convert_cp1252_gb2312 = new String(original.getBytes(), "GB2312");
			String convert_cp1252_gb18030 = new String(original.getBytes(), "GB18030");
			String convert_cp1252_utf16be = new String(original.getBytes("UTF-16BE"));
			String convert_gb2312_utf16be = new String(convert_cp1252_gb2312.getBytes("UTF-16BE"));
			String convert_gb2312_cp1252 = new String(convert_cp1252_gb2312.getBytes(), "CP1252");

			/*
			 * System.out.println( "Original (cp1252)   = " + isCharset( "cp1252", original ) );
			 * System.out.println( "Original (cp1252)   = " + isCharset2( "cp1252", original ) );
			 * System.out.println( "Original (GB2312)   = " + isCharset( "GB2312", original ) );
			 * System.out.println( "Original (GB2312)   = " + isCharset2( "GB2312", original ) );
			 * System.out.println( "Original (UTF-8)    = " + isCharset( "UTF-8", original ) );
			 * System.out.println( "Original (UTF-8)    = " + isCharset2( "UTF-8", original ) );
			 * System.out.println( "Original (UTF-16)   = " + isCharset( "UTF-16", original ) );
			 * System.out.println( "Original (UTF-16)   = " + isCharset2( "UTF-16", original ) );
			 * System.out.println( "Original (UTF-16BE) = " + isCharset( "UTF-16BE", original ) );
			 * System.out.println( "Original (UTF-16BE) = " + isCharset2( "UTF-16BE", original ) );
			 * System.out.println( "Convert 1 (cp1252)  = " + isCharset( "cp1252", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (cp1252)  = " + isCharset2( "cp1252", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (GB2312)  = " + isCharset( "GB2312", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (GB2312)  = " + isCharset2( "GB2312", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (UTF-8)   = " + isCharset( "UTF-8", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (UTF-8)   = " + isCharset2( "UTF-8", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (UTF-16)  = " + isCharset( "UTF-16", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (UTF-16)  = " + isCharset2( "UTF-16", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (UTF-16)  = " + isCharset( "UTF-16BE", convert_cp1252_gb2312 ) );
			 * System.out.println( "Convert 1 (UTF-16)  = " + isCharset2( "UTF-16BE", convert_cp1252_gb2312 ) );
			 */

			System.out.println("----------------------------------------");

			System.out.println("Original (Cp1252)             = " + original);
			System.out.println("Convert  (Cp1252 -> GB2312)   = " + convert_cp1252_gb2312);
			System.out.println("Convert  (Cp1252 -> GB18030)  = " + convert_cp1252_gb18030);
			System.out.println("Convert  (Cp1252 -> UTF-16BE) = " + convert_cp1252_utf16be);
			System.out.println("Convert  (GB2312 -> UTF-16BE) = " + convert_gb2312_utf16be);
			System.out.println("Convert  (Cp1252 -> GB2312) = " + convert_gb2312_cp1252);

			System.out.println("----------------------------------------");

			System.out.println("Original (Cp1252)             = [" + UnicodeTest.bytesToHexString(original.getBytes()) + "]");
			System.out.println("Convert  (Cp1252 -> GB2312)   = [" + UnicodeTest.bytesToHexString(convert_cp1252_gb2312.getBytes("GB2312")) + "]");
			System.out.println("Convert  (Cp1252 -> GB18030)  = [" + UnicodeTest.bytesToHexString(convert_cp1252_gb18030.getBytes("GB18030")) + "]");
			System.out.println("Convert  (Cp1252 -> UTF-16BE) = [" + UnicodeTest.bytesToHexString(convert_cp1252_utf16be.getBytes("UTF-16BE")) + "]");
			System.out.println("Convert  (GB2312 -> UTF-16BE) = [" + UnicodeTest.bytesToHexString(convert_gb2312_utf16be.getBytes("UTF-16BE")) + "]");
			System.out.println("Convert  (Cp1252 -> GB2312)   = [" + UnicodeTest.bytesToHexString(convert_gb2312_cp1252.getBytes("Cp1252")) + "]");

			System.out.println("----------------------------------------");

			StringBuilder sb_gb2312 = new StringBuilder(convert_cp1252_gb2312);
			StringBuilder sb_gb2312_utf16be = new StringBuilder(new String(convert_cp1252_gb2312.getBytes("UTF-16BE")));
			StringBuilder sb_gb18030_utf16be = new StringBuilder(new String(convert_cp1252_gb18030.getBytes("UTF-16BE")));

			System.out.println("StringBuilder (GB2312)              = " + sb_gb2312);
			System.out.println("StringBuilder (GB2312 -> UTF-16BE)  = " + sb_gb2312_utf16be);
			System.out.println("StringBuilder (GB18030 -> UTF-16BE) = " + sb_gb18030_utf16be);

			System.out.println("StringBuilder (GB2312)              = [" + UnicodeTest.bytesToHexString(sb_gb2312.toString().getBytes("GB2312")) + "]");
			System.out.println("StringBuilder (GB2312 -> UTF-16BE)  = [" + UnicodeTest.bytesToHexString(sb_gb2312_utf16be.toString().getBytes("UTF-16BE"))
			        + "]");
			System.out.println("StringBuilder (GB18030 -> UTF-16BE) = [" + UnicodeTest.bytesToHexString(sb_gb18030_utf16be.toString().getBytes("UTF-16BE"))
			        + "]");

			System.out.println("----------------------------------------");

			StringBuilder sb_mix_gb2312 = new StringBuilder();
			StringBuilder sb_mix_gb2312_utf16be = new StringBuilder();

			sb_mix_gb2312.append("ABC: ");
			sb_mix_gb2312.append(convert_cp1252_gb2312);

			sb_mix_gb2312_utf16be.append("ABC: ");
			sb_mix_gb2312_utf16be.append((new String(convert_cp1252_gb2312.getBytes("UTF-16BE"))));

			System.out.println("StringBuilder (GB2312 -> Mix)   = " + sb_mix_gb2312);
			System.out.println("StringBuilder (UTF-16BE -> Mix) = " + sb_mix_gb2312_utf16be);

			System.out.println("StringBuilder (GB2312 -> Mix | Cp1252)     = [" + UnicodeTest.bytesToHexString(sb_mix_gb2312.toString().getBytes()) + "]");
			System.out.println("StringBuilder (GB2312 -> Mix | GB2312)     = [" + UnicodeTest.bytesToHexString(sb_mix_gb2312.toString().getBytes("GB2312"))
			        + "]");
			System.out.println("StringBuilder (UTF-16BE -> Mix | UTF-16BE) = ["
			        + UnicodeTest.bytesToHexString(sb_mix_gb2312_utf16be.toString().getBytes("UTF-16BE")) + "]");

			System.out.println("----------------------------------------");

			/*
			 * ----------------------------------------
			 * Default Charset = windows-1252
			 * ----------------------------------------
			 * ----------------------------------------
			 * Original (Cp1252) = abc: �¼ף���ŵ��
			 * Convert (Cp1252 -> GB2312) = abc: ??????
			 * Convert (Cp1252 -> GB18030) = abc: ??????
			 * Convert (Cp1252 -> UTF-16BE) =
			 * Convert (GB2312 -> UTF-16BE) =
			 * Convert (Cp1252 -> GB2312) = abc: ??????
			 * ----------------------------------------
			 * Original (Cp1252) = [0x61, 0x62, 0x63, 0x3A, 0x20, 0xB5, 0xC2, 0xBC, 0xD7, 0xA3, 0xBA, 0xBA, 0xBA, 0xC5, 0xB5, 0xCD, 0xFE]
			 * Convert (Cp1252 -> GB2312) = [0x61, 0x62, 0x63, 0x3A, 0x20, 0xB5, 0xC2, 0xBC, 0xD7, 0xA3, 0xBA, 0xBA, 0xBA, 0xC5, 0xB5, 0xCD, 0xFE]
			 * Convert (Cp1252 -> GB18030) = [0x61, 0x62, 0x63, 0x3A, 0x20, 0xB5, 0xC2, 0xBC, 0xD7, 0xA3, 0xBA, 0xBA, 0xBA, 0xC5, 0xB5, 0xCD, 0xFE]
			 * Convert (Cp1252 -> UTF-16BE) = [0x00, 0x00, 0x00, 0x61, 0x00, 0x00, 0x00, 0x62, 0x00, 0x00, 0x00, 0x63, 0x00, 0x00, 0x00, 0x3A, 0x00, 0x00, 0x00,
			 * 0x20, 0x00, 0x00, 0x00, 0xB5, 0x00, 0x00, 0x00, 0xC2, 0x00, 0x00, 0x00, 0xBC, 0x00, 0x00, 0x00, 0xD7, 0x00, 0x00, 0x00, 0xA3, 0x00, 0x00, 0x00,
			 * 0xBA, 0x00, 0x00, 0x00, 0xBA, 0x00, 0x00, 0x00, 0xBA, 0x00, 0x00, 0x00, 0xC5, 0x00, 0x00, 0x00, 0xB5, 0x00, 0x00, 0x00, 0xCD, 0x00, 0x00, 0x00,
			 * 0xFE]
			 * Convert (GB2312 -> UTF-16BE) = [0x00, 0x00, 0x00, 0x61, 0x00, 0x00, 0x00, 0x62, 0x00, 0x00, 0x00, 0x63, 0x00, 0x00, 0x00, 0x3A, 0x00, 0x00, 0x00,
			 * 0x20, 0x00, 0x5F, 0x00, 0xB7, 0x00, 0x75, 0x00, 0x32, 0x00, 0xFF, 0x00, 0x1A, 0x00, 0x6C, 0x00, 0x49, 0x20, 0x39, 0x00, 0xFA, 0x00, 0x5A, 0x00,
			 * 0x01]
			 * Convert (Cp1252 -> GB2312) = [0x61, 0x62, 0x63, 0x3A, 0x20, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F]
			 * ----------------------------------------
			 * StringBuilder (GB2312) = abc: ??????
			 * StringBuilder (GB2312 -> UTF-16BE) =
			 * StringBuilder (GB18030 -> UTF-16BE) =
			 * StringBuilder (GB2312) = [0x61, 0x62, 0x63, 0x3A, 0x20, 0xB5, 0xC2, 0xBC, 0xD7, 0xA3, 0xBA, 0xBA, 0xBA, 0xC5, 0xB5, 0xCD, 0xFE]
			 * StringBuilder (GB2312 -> UTF-16BE) = [0x00, 0x00, 0x00, 0x61, 0x00, 0x00, 0x00, 0x62, 0x00, 0x00, 0x00, 0x63, 0x00, 0x00, 0x00, 0x3A, 0x00, 0x00,
			 * 0x00, 0x20, 0x00, 0x5F, 0x00, 0xB7, 0x00, 0x75, 0x00, 0x32, 0x00, 0xFF, 0x00, 0x1A, 0x00, 0x6C, 0x00, 0x49, 0x20, 0x39, 0x00, 0xFA, 0x00, 0x5A,
			 * 0x00, 0x01]
			 * StringBuilder (GB18030 -> UTF-16BE) = [0x00, 0x00, 0x00, 0x61, 0x00, 0x00, 0x00, 0x62, 0x00, 0x00, 0x00, 0x63, 0x00, 0x00, 0x00, 0x3A, 0x00,
			 * 0x00, 0x00, 0x20, 0x00, 0x5F, 0x00, 0xB7, 0x00, 0x75, 0x00, 0x32, 0x00, 0xFF, 0x00, 0x1A, 0x00, 0x6C, 0x00, 0x49, 0x20, 0x39, 0x00, 0xFA, 0x00,
			 * 0x5A, 0x00, 0x01]
			 * ----------------------------------------
			 * StringBuilder (GB2312 -> Mix) = ABC: abc: ??????
			 * StringBuilder (UTF-16BE -> Mix) = ABC:
			 * StringBuilder (GB2312 -> Mix | Cp1252) = [0x41, 0x42, 0x43, 0x3A, 0x20, 0x61, 0x62, 0x63, 0x3A, 0x20, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F]
			 * StringBuilder (GB2312 -> Mix | GB2312) = [0x41, 0x42, 0x43, 0x3A, 0x20, 0x61, 0x62, 0x63, 0x3A, 0x20, 0xB5, 0xC2, 0xBC, 0xD7, 0xA3, 0xBA, 0xBA,
			 * 0xBA, 0xC5, 0xB5, 0xCD, 0xFE]
			 * StringBuilder (UTF-16BE -> Mix | UTF-16BE) = [0x00, 0x41, 0x00, 0x42, 0x00, 0x43, 0x00, 0x3A, 0x00, 0x20, 0x00, 0x00, 0x00, 0x61, 0x00, 0x00,
			 * 0x00, 0x62, 0x00, 0x00, 0x00, 0x63, 0x00, 0x00, 0x00, 0x3A, 0x00, 0x00, 0x00, 0x20, 0x00, 0x5F, 0x00, 0xB7, 0x00, 0x75, 0x00, 0x32, 0x00, 0xFF,
			 * 0x00, 0x1A, 0x00, 0x6C, 0x00, 0x49, 0x20, 0x39, 0x00, 0xFA, 0x00, 0x5A, 0x00, 0x01]
			 * ----------------------------------------
			 */
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}
}
