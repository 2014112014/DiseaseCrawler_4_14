package disease;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

public class Disease_Test {
	public static String getURLcontent(String path) throws HttpException, IOException {
		System.out.println("path =="+path);
		HttpClient httpClient = new HttpClient();
		GetMethod getmethod = new GetMethod(path);
		int statusCode = httpClient.executeMethod(getmethod);
		if (statusCode == HttpStatus.SC_OK) {
			InputStream inputStream = getmethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			getmethod.releaseConnection();
			((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
			return stringBuffer.toString();
		}
		return "";
	}
}
