
package dj.test.httpurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

public class TestMain
{
	public String szProxyHost = "singtelproxy.is";
	public int iProxyPort = 80;
	public String szProxyUser = "singtel\\dujiang";
	public String szProxyPassword = "djan2008";

	public Proxy getProxy(boolean bUseSysProp, String szHost, int iPort) throws Exception{
		Proxy proxy = null;

		System.out.println("Host = " + szHost);
		System.out.println("Port = " + iPort);

		if (bUseSysProp) {
			System.setProperty("http.proxyHost", szHost);
			System.setProperty("http.proxyPort", Integer.toString(iPort));
		} else {
			try {
				SocketAddress sa = new InetSocketAddress(szHost, iPort);
				proxy = new Proxy(Proxy.Type.HTTP, sa);
			} catch (Exception e) {
				throw e;
			}
		}

		return proxy;
	}

	public String getAuthorization(String szUser, String szPassword){
		String szAuthorization = null;

		System.out.println("User = " + szUser);
		System.out.println("Password = " + szPassword);

		Base64 encoder = new Base64();
		String szTemp = szUser + ":" + szPassword;
		szAuthorization = new String(encoder.encode(szTemp.getBytes()));
		szAuthorization = "Basic " + szAuthorization;

		System.out.println("Authorization = " + szAuthorization);

		return szAuthorization;
	}

	public URLConnection getUrlConnection(boolean bUseProxy, String szHost, int iPort, String szUser, String szPassword, String szUrl, int iTimeout)
	                                                                                                                                                throws Exception{
		URL url = null;
		Proxy proxy = null;
		String szAuthorization = null;
		URLConnection urlConn = null;

		try {
			System.out.println("URL = " + szUrl);

			url = new URL(szUrl);

			if (bUseProxy) {
				proxy = getProxy(false, szHost, iPort);
				szAuthorization = getAuthorization(szUser, szPassword);
				urlConn = url.openConnection(proxy);
				urlConn.setRequestProperty("Proxy-Authorization", szAuthorization);
			} else {
				urlConn = url.openConnection(Proxy.NO_PROXY);
			}

			urlConn.setConnectTimeout(iTimeout);
			urlConn.connect();
		} catch (Exception e) {
			throw e;
		}

		return urlConn;
	}

	public void showHttpHeaders(boolean bUseProxy, String szUrl, int iTimeout) throws Exception{
		try {
			URLConnection urlConn = getUrlConnection(bUseProxy, szProxyHost, iProxyPort, szProxyUser, szProxyPassword, szUrl, iTimeout);

			for (int i = 0;; i++) {
				String szKey = urlConn.getHeaderFieldKey(i);
				String szValue = urlConn.getHeaderField(i);

				if ((szKey == null) && (szValue == null)) {
					// No more headers.
					break;
				} else if (szKey == null) {
					// First line of headers.
					System.out.println("Server HTTP version, Response code: " + szValue);
				} else {
					System.out.println(szKey + " = " + szValue);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void showHttpPage(boolean bUseProxy, String szUrl, int iTimeout) throws Exception{
		try {
			URLConnection urlConn = getUrlConnection(bUseProxy, szProxyHost, iProxyPort, szProxyUser, szProxyPassword, szUrl, iTimeout);
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String szLine = null;

			while ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) throws Exception{
		TestMain hgt = new TestMain();

		// boolean bUseProxy = false;
		// String szUrl = "http://10.252.84.21:10080/console/";
		boolean bUseProxy = true;
		String szUrl = "http://quote.morningstar.com/highlow.html?msection=HighLow";
		int iTimeout = 2000;

		try {
			System.out.println("================================================================================");
			hgt.showHttpHeaders(bUseProxy, szUrl, iTimeout);
			System.out.println("================================================================================");
			hgt.showHttpPage(bUseProxy, szUrl, iTimeout);
			System.out.println("================================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
