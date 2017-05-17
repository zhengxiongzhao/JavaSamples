package com.jason.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest
{
	public static void main(String[] args)
	{
		PropertyConfigurator
				.configure("src/test/resources/log4j/log4j.properties");
		Logger log = Logger.getLogger(Log4jTest.class);
		File file=new File("");
		try
		{
			new FileInputStream(file);
		} catch (FileNotFoundException e)
		{
			log.debug(e.getMessage());
		}
	}
}
