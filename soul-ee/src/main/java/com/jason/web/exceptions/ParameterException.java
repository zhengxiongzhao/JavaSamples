/**
 * @(#)ParameterException.java 2011-12-20 Copyright 2011 it.kedacom.com, Inc.
 *                             All rights reserved.
 */

package com.jason.web.exceptions;

/**
 * @ClassName: ParameterException
 * @Description: TODO(参数错误)
 * @author Jason Chiu:CIHUnKnown@Gmail.com
 * @date 2013-8-17 上午12:43:00
 * 
 */
public class ParameterException extends RuntimeException
{

	/** serialVersionUID */
	private static final long serialVersionUID = 6417641452178955756L;

	public ParameterException() {
		super();
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
