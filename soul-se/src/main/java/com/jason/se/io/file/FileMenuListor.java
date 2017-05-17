package com.jason.se.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FileMenuListor {
	public static void main(String[] args) {
		 
	}
	 

	class DirFilter implements FilenameFilter {
		private Pattern pattern;
		public DirFilter(String regex) {
			pattern=Pattern.compile(regex);
		}
		@Override
		public boolean accept(File dir, String name) {
			return pattern.matcher(name).matches();
		}
	}
}
