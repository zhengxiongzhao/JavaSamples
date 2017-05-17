package org.soul.okhttp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.soul.okhttp.HttpRequest.ResultCallback;


/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws IOException {
		
		// System.out.println(App.class.getResourceAsStream("/1.jpg").available());;
		httpExample();
	}

	public static void httpExample() {
		// TODO Auto-generated method stub
		String hostname = "http://192.168.100.147:5000";
		HttpRequest.setHostname(hostname);
		
		final String URL_PARAMS_TEST = "/user/params_test";
		/**
		 * GET 请求
		 */
		HttpRequest.get(URL_PARAMS_TEST,
				new ResultCallback() {
					@Override
					public void onResponse(int code, String message, String data) {
						if (code == 0) {
							System.out.println(data);
						} else {
							System.err.println(message);
							// System.err.println(data);
						}
					}
				});

		/**
		 * POST 请求
		 */
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", "1804");
		params.put("password", "qwe123");
		HttpRequest.post("/user/login", params,
				new ResultCallback() {
					@Override
					public void onResponse(int code, String message, String data) {
						if (code == 0) {
							JSONObject result = JSONUtils.toJSONObject(data, null);
							System.err.println(data);
							String token = JSONUtils.getString(result, "session_id", null);;
							/**
							 * TOKEN 设置
							 */
							HttpRequest.setToken(token);

						} else {
							System.err.println(message);
						}
					}
				});
		
		

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/**
		 * POST 请求
		 */
		/**
		HashMap<String, String> params1 = new HashMap<String, String>();
		params1.put("test1", "1804");
		params1.put("test2", "qwe123");
		
		HashMap<String, File> files = new HashMap<String, File>();

		files.put("test1", getFile("1.jpg"));
		files.put("test2", getFile("2.jpg"));
		files.put("test3", getFile("3.jpg"));
		files.put("test4", getFile("4.jpg"));
		files.put("test5", getFile("5.jpg"));
		files.put("test6", getFile("6.jpg"));
		files.put("test7", getFile("7.jpg"));
		
		HttpRequest.post(URL_PARAMS_TEST, params1, files,
				new ResultCallback() {
					@Override
					public void onResponse(int code, String message, String data) {
						if (code == 0) {
							System.out.println(data);
						} else {
							System.err.println(message);
						}
					}
				});
				**/
	}
	
	public static File getFile(String filename){
		return new File(App.class.getResource("/").getPath(), filename);
	}
}
