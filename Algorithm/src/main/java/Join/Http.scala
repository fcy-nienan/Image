package Join

import java.net.{HttpURLConnection, URL, URLConnection}

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import scala.util.parsing.json.JSON

object Http {
  def getResponse(url: String, header: String = null): String = {
    var url=new URL(url);
    var connection=url.openConnection().asInstanceOf[HttpURLConnection];
    connection.setRequestMethod("GET");
    connection.setConnectTimeout(5000);
    connection.connect();



  }
}
