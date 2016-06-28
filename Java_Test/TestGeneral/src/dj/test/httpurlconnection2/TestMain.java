
package dj.test.httpurlconnection2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

public class TestMain
{
	public boolean bUseProxy = true;
	public String szProxyHost = "singtelproxy.is";
	public String szProxyPort = "80";
	public String szProxyUser = "singtel\\dujiang";
	public String szProxyPassword = "djan2008";

	public String szUrl = "http://finance.yahoo.com/q?s=";
	public String szayKeyword[] = new String[]{"<big><b><span id=\"yfs_l10_", "\">", "</span>"};

	public URLConnection getUrlConnection(boolean bUseProxy, String szUrl) throws Exception{
		URLConnection urlConn = null;

		try {
			if (bUseProxy) {
				System.out.println("Proxy host = " + szProxyHost);
				System.out.println("Proxy port = " + szProxyPort);
				System.out.println("Proxy user = " + szProxyUser);

				System.setProperty("http.proxyHost", szProxyHost);
				System.setProperty("http.proxyPort", szProxyPort);
			}

			System.out.println("URL = " + szUrl);

			URL url = new URL(szUrl);
			urlConn = url.openConnection();

			if (bUseProxy) {
				Base64 encoder = new Base64();
				String szTemp = szProxyUser + ":" + szProxyPassword;
				String szEncoded = new String(encoder.encode(szTemp.getBytes()));

				szEncoded = "Basic " + szEncoded;

				System.out.println("Proxy-Authorization = " + szEncoded);

				urlConn.setRequestProperty("Proxy-Authorization", szEncoded);
			}

			urlConn.connect();
		} catch (Exception e) {
			throw e;
		}

		return urlConn;
	}

	public void getQuote(String szStockCode) throws Exception{
		try {
			String szUrlTemp = szUrl + szStockCode;

			URLConnection urlConn = getUrlConnection(bUseProxy, szUrlTemp);
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String szLine = null;
			String szKeywordPrefix = szayKeyword[0] + szStockCode + szayKeyword[1];
			String szKeywordSuffix = szayKeyword[2];
			String szPrice = null;
			int i, j;

			// System.out.println( "Keyword prefix = " + szKeywordPrefix );
			// System.out.println( "Keyword suffix = " + szKeywordSuffix );

			while ((szLine = br.readLine()) != null) {
				if ((i = szLine.indexOf(szKeywordPrefix)) > 0) {
					i += szKeywordPrefix.length();
					j = i + szLine.substring(i).indexOf(szKeywordSuffix);
					szPrice = szLine.substring(i, j);

					System.out.println(szStockCode + " = " + szPrice);

					break;
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) throws Exception{
		TestMain hgs = new TestMain();

		try {
			System.out.println("================================================================================");
			hgs.getQuote("aig");
			System.out.println("================================================================================");
			hgs.getQuote("c");
			System.out.println("================================================================================");
			hgs.getQuote("yhoo");
			System.out.println("================================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
