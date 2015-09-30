package com.newroad.user.sns.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http call
 * 
 * @author tangzj1
 * 
 */
public class HttpUtil {

  private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

  private HttpUtil() {}

  /**
   * http 调用
   * 
   * @param method http method
   * @param url http request url
   * @param headers http header data
   * @param reqEntity http request body data
   * @return
   * @throws UnsupportedEncodingException
   */
  public static StringBuffer httpCall(Method method, String url, Map<String, String> headers, StringBuffer reqEntity,
      Map<String, String> parameters) throws Exception {
    if (StringUtils.isBlank(url)) {
      throw new RuntimeException("url is null");
    }
    StringBuffer sb = new StringBuffer();
    HttpUriRequest m = null;
    if (!MapUtils.isEmpty(parameters)) {
      List<NameValuePair> listParam = new ArrayList<NameValuePair>();
      for (Entry<String, String> entry : parameters.entrySet()) {
        listParam.add(new BasicNameValuePair(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
      }
      url += "?" + URLEncodedUtils.format(listParam, Consts.UTF_8);
    }
    if (method == null) {
      m = new HttpGet(url);
    } else {
      m = method.getMethod(url);
    }

    if (!MapUtils.isEmpty(headers)) {
      for (Entry<String, String> entry : headers.entrySet()) {
        m.addHeader(entry.getKey(), entry.getValue());
      }
    }

    if (reqEntity != null && reqEntity.length() > 0 && (m instanceof HttpPut || m instanceof HttpPost)) {
      ((HttpEntityEnclosingRequestBase) m).setEntity(new StringEntity(reqEntity.toString(), Consts.UTF_8));
    }

    HttpClient client = HttpClientBuilder.create().build();

    HttpResponse response = client.execute(m);

    HttpEntity resEntity = null;
    if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && (resEntity = response.getEntity()) != null) {
      InputStream is = null;
      BufferedReader br = null;
      try {
        is = resEntity.getContent();
        if (resEntity.getContentEncoding() != null && resEntity.getContentEncoding().getValue().contains("gzip")) {
          is = new GZIPInputStream(is);
        }
        br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
        String line = null;
        while ((line = br.readLine()) != null) {
          sb.append(line);
        }
      } catch (Exception e) {
        log.error("HttpUtil.httpCall bring an exception:", e);
      } finally {
        try {
          is.close();
          br.close();
        } catch (Exception e2) {
          log.error("HttpUtil.httpCall bring an exception:", e2);
        }
      }

    }

    return sb;
  }

  public static StringBuffer simulateHttpPostCall(String url, Map<String, String> headers, HttpEntity reqEntity,
      Map<String, String> parameters) {
    StringBuffer sb = new StringBuffer();
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    try {
      HttpPost httpPost = new HttpPost(url);
      httpPost.setEntity(reqEntity);
      response = httpClient.execute(httpPost);

      if (response == null) {
        return null;
      }
      // 获取响应对象
      HttpEntity resEntity = response.getEntity();
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && (resEntity = response.getEntity()) != null) {
        InputStream is = null;
        BufferedReader br = null;
        try {
          is = resEntity.getContent();
          if (resEntity.getContentEncoding() != null && resEntity.getContentEncoding().getValue().contains("gzip")) {
            is = new GZIPInputStream(is);
          }
          br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
          String line = null;
          while ((line = br.readLine()) != null) {
            sb.append(line);
          }
        } catch (Exception e) {
          log.error("HttpUtil.httpCall bring an exception:", e);
        } finally {
          try {
            is.close();
            br.close();
          } catch (Exception e2) {
            log.error("HttpUtil.httpCall bring an exception:", e2);
          }
        }

      }
      EntityUtils.consume(resEntity);
    } catch (ClientProtocolException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (response != null) {
          response.close();
        }
        if (httpClient != null) {
          httpClient.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb;
  }

  public enum Method {
    get(1), post(2), put(3), delete(4);
    private int val;

    private Method(int val) {
      this.val = val;
    }

    public HttpUriRequest getMethod(String url) {
      switch (this.val) {
        case 1:
          return new HttpGet(url);
        case 2:
          return new HttpPost(url);
        case 3:
          return new HttpPut(url);
        case 4:
          return new HttpDelete(url);
        default:
          return null;
      }
    }
  }
}
