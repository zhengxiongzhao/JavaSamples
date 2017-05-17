package com.jason.web.exceptions;

/**
 * @ClassName: BusinessException
 * @Description: TODO(系统业务异常)
 * @author Jason Chiu:CIHUnKnown@Gmail.com
 * @date 2013-8-16 下午10:44:38
 * 
 */
public class BusinessException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 4122189715638317584L;

	private String code;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
