package test.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Kobs
 *
 */
public class FirstTest extends ActionSupport {
     /**
      * long Variable versionUID.
      */
     private static final long serialVersionUID = 1L;

     private String result;

     public String getResult() {
          return result;
     }

     public void setResult(final String result) {
          this.result = result;
     }
    /**
      * @param args parameter
      */
     public String execute() throws Exception {
    	String ret = ERROR;
        String url = "http://www.baidu.com/";
        BufferedReader in = null;
		try {
			URL realURL = new URL(url);
			URLConnection conn = realURL.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				this.result += line + "\n";
			}
		} catch (Exception e) {
			System.out.println("query error!");
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.print(result);
		return ret;
	}
}
