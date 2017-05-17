package org.soul.okhttp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * use <code>
		String hostname = "http://192.168.100.147:5000";
		
		HttpRequest.setHostname(hostname);									// SET SERVER ADDRESS
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", "1804");
		params.put("password", "qwe123");
		
		HttpRequest.post("/user/params_test", params,
		new ResultCallback() {
			 
			public void onResponse(int code, String message, String data) {
				//{"code": -2, "message": "连接服务器超时!", "data": "<string exception>"}  //getException()
				//{"code": -1, "message": "请登录后操作!", "data": {}}
				//{"code": 0, "message": "交易成功", "data": {"a":1}}

				if (code == 0) {
					System.out.println(data);
					String token = new JsonParser().parse(data)
							.getAsJsonObject().get("session_id")
							.getAsString();
							
					// SET AUTHORIZATION TOKEN
					HttpRequest.setToken(token);

				} else {
					System.err.println(message);
					
				}
			}
		});
 * </code>
 */
@SuppressWarnings("all")
public class HttpRequest {
	private static OkHttpClient httpClient;
	private static TestHandler mDelivery;
	private static String HTTP_SESSION_TOKEN_NAME = "x-session-token";
	private static String HTTP_SESSION_TOKEN = null;
	private static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
	private static final String TAG = "HttpRequest";
	private static String HOST_NAME = null;

	static {
		okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(10, TimeUnit.SECONDS);
		builder.writeTimeout(10, TimeUnit.SECONDS);
		builder.readTimeout(30, TimeUnit.SECONDS);
		httpClient = builder.build();

		// mDelivery = new Handler(Looper.getMainLooper());
		mDelivery = new TestHandler();
	}

	public static void setHostname(String hostname) {
		HOST_NAME = hostname;
	}

	public static void setToken(String token) {
		HTTP_SESSION_TOKEN = token;
	}

	private static okhttp3.Request.Builder getBuilder() {
		okhttp3.Request.Builder builder = new Request.Builder();
		if (HTTP_SESSION_TOKEN != null) {
			builder.addHeader(HTTP_SESSION_TOKEN_NAME, HTTP_SESSION_TOKEN);
		}
		return builder;
	}

	private static String getUrl(String url) {
		if (HOST_NAME == null) {
			System.err.println("\nplease setting the hostname! \n\tUSE:\n\t\tHttpRequest.setHostname(hostname)\n\n");
		}
		return HOST_NAME + url;
	}

	/**
	 * 异步的get请求
	 * 
	 * @param url
	 * @param callback
	 */
	public static void get(String url, final ResultCallback callback) {
		final Request request = getBuilder().url(getUrl(url)).build();
		deliveryResult(callback, request);
	}

	/**
	 * 异步的post请求
	 * 
	 * @param url
	 * @param callback
	 */
	public static void post(String url, HashMap<String, String> params, final ResultCallback callback) {
		post(url, params, null, callback);
	}

	/**
	 * 异步的文件上传
	 * 
	 * @param url
	 * @param callback
	 */
	public static void post(String url, HashMap<String, String> params, HashMap<String, File> files,
			final ResultCallback callback) {
		RequestBody requestBody = null;
		if (files != null && files.size() > 0) {
			okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
			builder.setType(MultipartBody.FORM);
			if (params != null) {
				for (Entry<String, String> param : params.entrySet()) {
					builder.addFormDataPart(param.getKey(), param.getValue());
				}
			}
			for (Entry<String, File> param : files.entrySet()) {
				builder.addFormDataPart(param.getKey(), param.getKey(),
						RequestBody.create(MEDIA_TYPE_JPEG, param.getValue()));
			}
			requestBody = builder.build();
		} else if (params != null && params.size() > 0) {
			okhttp3.FormBody.Builder builder = new FormBody.Builder();
			for (Entry<String, String> param : params.entrySet()) {
				builder.add(param.getKey(), param.getValue());
			}
			requestBody = builder.build();
		} else {
			new Exception(String.format("%s POST 方法参数不能为空 !", HttpRequest.class.getSimpleName())).printStackTrace();
			return;
		}
		final Request request = getBuilder().url(getUrl(url)).post(requestBody).build();
		deliveryResult(callback, request);
	}

	static class TestHandler {
		public TestHandler() {
			// TODO Auto-generated constructor stub
		}

		void post(Runnable runnable) {
			new Thread(runnable).start();
		}
	}

	private static void deliveryResult(final ResultCallback callback, Request request) {
		httpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pw.close();
				callback.setException(e);
				if (e instanceof java.net.SocketTimeoutException) {
					sendSuccessResultCallback(-2, "连接服务器超时!", sw.toString(), callback);
				} else if (e instanceof java.net.ConnectException) {
					sendSuccessResultCallback(-2, "请求服务器失败!", sw.toString(), callback);
				} else if (e instanceof java.net.SocketException) {
					// Connection reset by peer: socket write error
					e.printStackTrace();
					sendSuccessResultCallback(-2, "连接被重置！!", sw.toString(), callback);
				} else {
					e.printStackTrace();
				}
				// sendFailedStringCallback(call.request(), e, callback);
			}

			@Override
			public void onResponse(Call call, Response response) {

				try {
					System.out.println(response.toString());
					String string = response.body().string();
					if (response.code() == 200) {
						System.out.println(string);
						JSONObject result = JSONUtils.toJSONObject(string, null);
						int code = JSONUtils.getInt(result, "code", -2);
						String message = JSONUtils.getString(result, "message", "数据解析错误!");
						String data = result.get("data").toString(); // JSONUtils.getString(result, "data", "数据解析错误!");
						sendSuccessResultCallback(code, message, data, callback);
					} else {
						System.err.println(response);
						sendFailedStringCallback(call.request(), new Exception(response.message()), callback);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	private static void sendFailedStringCallback(final Request request, final Exception e,
			final ResultCallback callback) {
		mDelivery.post(new Runnable() {
			@Override
			public void run() {
				if (callback != null) {
					callback.onError(e);
				}
			}
		});
	}

	private static void sendSuccessResultCallback(final int code, final String message, final String data,
			final ResultCallback callback) {
		mDelivery.post(new Runnable() {
			@Override
			public void run() {
				if (callback != null) {
					callback.onResponse(code, message, data);
				}
			}
		});
	}

	public static abstract class ResultCallback {
		private Exception exception;

		public Exception getException() {
			return exception;
		}

		public void setException(Exception exception) {
			this.exception = exception;
		}

		public void onError(Exception e) {
		};

		public abstract void onResponse(int code, String message, String data);
	}

}
