package com.jason.se.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class CopyCharacters
{
	 public static void main(String[] args) throws IOException {

	        Scanner s = null;
	        double sum = 0;

	        try {
	            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
	            s.useLocale(Locale.US);

	            while (s.hasNext()) {
	                if (s.hasNextDouble()) {
	                    sum += s.nextDouble();
	                } else {
	                    s.next();
	                }   
	            }
	        } finally {
	            s.close();
	        }

	        System.out.println(sum);
	    }
}