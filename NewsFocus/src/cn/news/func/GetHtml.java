package cn.news.func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetHtml {
	
	/**
	 * 连接网页并获取html页面信息
	 * @param url 请求连接
	 * @param encoding 编码方式
	 * @return buffer
	 */
	public static String getHtmlResourceByUrl(String url, String encoding) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		URL urlObj = null;
		URLConnection uc = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		
		try{
			// 建立网络连接
			urlObj = new URL(url);
			// 打开网络连接
			uc = urlObj.openConnection();
			// 建立文件写入流
			isr = new InputStreamReader(uc.getInputStream(),encoding);
			// 建立缓存写入流
			reader = new BufferedReader(isr);
			// 建立临时文件
			String temp = null;
			while((temp = reader.readLine()) != null) {
				buffer.append(temp+"\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("没有联网，请检查设置");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("输入输出流发生错误");
		} finally {
			if(isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
}