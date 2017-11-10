package jma;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class CrawlUtils {

  public static final int connectionTimeoutSeconds = 15;
  public static final int socketTimeoutSeconds = 15;

  public static UrlEncodedFormEntity getFormEntity(List<NameValuePair> params, String charset) {
    try {
      return new UrlEncodedFormEntity(params, charset);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("encode url form entity error!", e);
    }
  }

  public static HttpResponseHandler getResponseHandler(HttpRequestBase request) {
    return new HttpResponseHandler(request);
  }

  public static class HttpResponseHandler {
    private final HttpResponse response;

    public HttpResponseHandler(HttpRequestBase request) {
      HttpClient httpClient =wrapClient( new DefaultHttpClient());
      httpClient.getParams().setIntParameter(
          CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeoutSeconds * 1000);
      httpClient.getParams().setIntParameter(
          CoreConnectionPNames.SO_TIMEOUT, socketTimeoutSeconds * 1000);
      try {
        response = httpClient.execute(request);
      } catch (IOException e) {
    	  //Release the connection
    	  request.abort();
        throw new RuntimeException("execute http request error!", e);
      }
    }

    public HttpResponse getResponse() {
      return response;
    }

    public String getHtml() {
      try {
        return EntityUtils.toString(response.getEntity());
      } catch (ParseException | IOException e) {
        throw new RuntimeException("parse http response to string error!", e);
      }
    }

    public String getCookies() {
      StringBuilder cookies = new StringBuilder();
      Header[] headers = response.getHeaders("Set-Cookie");
      if (headers.length == 0) {
        return null;
      }
      for (Header header : headers) {
        cookies.append(header.getValue().split(" ")[0]);
      }
      return cookies.toString();
    }
  }
  @SuppressWarnings("deprecation")
  public static HttpClient wrapClient(HttpClient base)
  {
      try{
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
          @Override
          public void checkClientTrusted(X509Certificate[] chain,String authType) throws CertificateException{}
          @Override
          public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException{}
          @Override
          public X509Certificate[] getAcceptedIssuers(){
            return null;
            }
          };
          ctx.init(null, new TrustManager[] { tm }, null);
          SSLSocketFactory ssf = new SSLSocketFactory(ctx);
          ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
          ClientConnectionManager ccm = base.getConnectionManager();
          SchemeRegistry sr = ccm.getSchemeRegistry();
          sr.register(new Scheme("https", ssf, 443));
          return new DefaultHttpClient(ccm, base.getParams());
          }
      catch (Exception ex) {
        ex.printStackTrace();
        return null;
        }
  }
}
