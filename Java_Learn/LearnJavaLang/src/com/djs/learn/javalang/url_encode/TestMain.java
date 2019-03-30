
package com.djs.learn.javalang.url_encode;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class TestMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args){
		try {
			// String szOrgUrl = "http://sample.com/shop/results.asp?UserId=test&Password=1234&Key=java";
			String szOrgUrl = "http://test.test_&_a.com?test=?&b=c";

			System.out.println("Original URL = " + szOrgUrl);

			// String szEncodedUrl = URLEncoder.encode( szOrgUrl, "UTF-8" );
			String szEncodedUrl =
			                      szOrgUrl.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
			                              .replaceAll("'", "&apos;");

			System.out.println("Encoded URL = " + szEncodedUrl);
		} catch (Exception e) {

		}

		try {
			URL url = new URL("http://sample.com/shop/results.asp?UserId=test&Password=1234&Key=java");
			System.out.println("Original URL = " + url);

			String encodedurl = URLEncoder.encode(url.toString(), "UTF-8");
			System.out.println("Encoded URL = " + encodedurl);
		} catch (MalformedURLException e) {
			System.err.println(e);
		} catch (UnsupportedEncodingException e) {
			System.err.println(e);
		}
	}
}
