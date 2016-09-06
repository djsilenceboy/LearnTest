
package com.djs.learn.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestMain
{
	public MultiThreadedHttpConnectionManager httpManager = null;
	public HttpClient httpClient = null;
	public int iHttp_MaxConnectionsPerHost = 200;
	public int iHttp_MaxTotalConnections = 200;
	public int iHttp_ConnectionTimeout = 5000;

	public String szProxyHost = "singtelproxy.is";
	public int iProxyPort = 80;
	public String szProxyUser = "singtel\\dujiang";
	public String szProxyNtlmDomain = "singtel";
	public String szProxyNtlmUser = "dujiang";
	public String szProxyPassword = "djan2008";

	public String szHost = "finance.yahoo.com";
	public int iPort = 80;

	public boolean bUseProxy = true;
	public boolean bHasAuthentication = true;
	// public String szAuthPolicy = AuthPolicy.NTLM;
	public String szAuthPolicy = AuthPolicy.BASIC;
	public boolean bUseHost = true;

	public void setupHttpClient(){
		httpManager = new MultiThreadedHttpConnectionManager();

		// httpManager.getParams().setDefaultMaxConnectionsPerHost( iHttp_MaxConnectionsPerHost );
		// httpManager.getParams().setMaxTotalConnections( iHttp_MaxTotalConnections );
		httpManager.getParams().setSoTimeout(iHttp_ConnectionTimeout);

		System.out.println("HttpManager: DefaultMaxConnectionsPerHost = " + httpManager.getParams().getDefaultMaxConnectionsPerHost());
		System.out.println("HttpManager: MaxTotalConnections = " + httpManager.getParams().getMaxTotalConnections());
		System.out.println("HttpManager: SoTimeout = " + httpManager.getParams().getSoTimeout());

		httpClient = new HttpClient(httpManager);

		httpClient.getParams().setSoTimeout(iHttp_ConnectionTimeout);

		System.out.println("HttpClient: SoTimeout = " + httpClient.getParams().getSoTimeout());
	}

	public void setupProxy(boolean bHasAuthentication, String szAuthPolicy){
		httpClient.getHostConfiguration().setProxy(szProxyHost, iProxyPort);

		System.out.println("HttpClient: ProxyHost = " + httpClient.getHostConfiguration().getProxyHost());
		System.out.println("HttpClient: ProxyPort = " + httpClient.getHostConfiguration().getProxyPort());
		System.out.println("HttpClient: HasAuthentication = " + bHasAuthentication);

		if (bHasAuthentication) {
			System.out.println("HttpClient: AuthPolicy = " + szAuthPolicy);

			Credentials defaultCreds = null;

			if (szAuthPolicy.equalsIgnoreCase(AuthPolicy.NTLM)) {
				defaultCreds = new NTCredentials(szProxyNtlmUser, szProxyPassword, "", szProxyNtlmDomain);
			} else {
				List<String> liAuthPrefs = new ArrayList<String>();
				liAuthPrefs.add(AuthPolicy.BASIC);
				liAuthPrefs.add(AuthPolicy.DIGEST);
				httpClient.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, liAuthPrefs);

				defaultCreds = new UsernamePasswordCredentials(szProxyUser, szProxyPassword);
			}

			AuthScope authScope = new AuthScope(szProxyHost, iProxyPort, AuthScope.ANY_REALM);
			HttpState httpState = new HttpState();
			httpState.setProxyCredentials(authScope, defaultCreds);
			httpClient.setState(httpState);

			System.out.println("HttpClient: State = " + httpClient.getState());
		}
	}

	public void setupHost(){
		// setHost() will only be used if the methods are not given an absolute URI.
		httpClient.getHostConfiguration().setHost(szHost, iPort);

		System.out.println("HttpClient: Host = " + httpClient.getHostConfiguration().getHost());
		System.out.println("HttpClient: Port = " + httpClient.getHostConfiguration().getPort());
	}

	public void setup() throws Exception{
		try {
			setupHttpClient();

			System.out.println("HttpClient: Use proxy = " + bUseProxy);

			if (bUseProxy) {
				setupProxy(bHasAuthentication, szAuthPolicy);
			}

			System.out.println("HttpClient: Use host = " + bUseHost);

			if (bUseHost) {
				setupHost();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public GetMethod processUrl(String szUrl) throws Exception{
		GetMethod getMethod = null;

		try {
			System.out.println("GET URL = " + szUrl);

			getMethod = new GetMethod(szUrl);

			// Used for Non-proxy, if any.
			// getMethod.setDoAuthentication( true );
			getMethod.setFollowRedirects(true);
			getMethod.getParams().setSoTimeout(iHttp_ConnectionTimeout);

			System.out.println("GetMethod: FollowRedirects = " + getMethod.getFollowRedirects());
			System.out.println("GetMethod: SoTimeout = " + getMethod.getParams().getSoTimeout());

			httpClient.executeMethod(getMethod);

			System.out.println("GetMethod: StatusCode = " + getMethod.getStatusCode());
			System.out.println("GetMethod: StatusLine = " + getMethod.getStatusLine());
			System.out.println("GetMethod: StatusText = " + getMethod.getStatusText());
		} catch (Exception e) {
			throw e;
		}

		return getMethod;
	}

	public void test() throws Exception{
		GetMethod getMethod = null;

		try {
			setup();

			String szGetUrl = "/q?s=aig";

			if (!bUseHost) {
				szGetUrl = "http://" + szHost + szGetUrl;
			}

			System.out.println("GET URL = " + szGetUrl);

			getMethod = processUrl(szGetUrl);
			int iStatusCode = getMethod.getStatusCode();

			if (iStatusCode == HttpStatus.SC_OK) {
				// String szResponse = getMethod.getResponseBodyAsString();
				// System.out.println( "GET response = " + szResponse );

				BufferedReader br = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));
				String szLine = null;

				while ((szLine = br.readLine()) != null) {
					System.out.println(szLine);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}
	}

	public static void main(String[] args){
		TestMain hct = new TestMain();

		try {
			System.out.println("================================================================================");
			hct.test();
			System.out.println("================================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
