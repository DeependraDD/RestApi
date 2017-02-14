package priceBookRestAPI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

// Invoke POST Method Using HttpClient

public class HttpClientPostExample {

  public static void main(String[] args) {

    try {

      DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
      HttpPost httpPost = new HttpPost(
          "http://localhost:8080/CXFRestfulTutorial/rest/changeName");

      StringEntity inputEntity = new StringEntity(
          "{\"Student\":{\"name\":\"Tom\"}}");
      inputEntity.setContentType("application/json");
      httpPost.setEntity(inputEntity);

      HttpResponse httpResponse = defaultHttpClient.execute(httpPost);

      if (httpResponse.getStatusLine().getStatusCode() != 200) {
        System.out.println("Failed response"
            + httpResponse.getStatusLine().getStatusCode());
      }
      String output;
      System.out.println("Output from Server .... \n");
      BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(
              (httpResponse.getEntity().getContent())));

      while ((output = bufferedReader.readLine()) != null) {
        System.out.println(output);
      }
      defaultHttpClient.getConnectionManager().shutdown();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}