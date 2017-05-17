package com.jason.jquery.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file=new File(this.getServletContext().getRealPath("res/编程基础卷.pdf"));
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer=new byte[bf.available()]; 
		bf.read(buffer);
		bf.close();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="+new String(file.getName().getBytes("GBK"),"ISO8859-1"));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		bos.write(buffer);
		bos.flush();
		bos.close();
	}

}
