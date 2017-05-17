package com.jason.se.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.java.swing.plaf.windows.resources.windows;

public class BaseFrame extends JFrame
{
	/**@Fields serialVersionUID : TODO( )*/
	private static final long serialVersionUID = 2920077203897437247L;

	@Before
	public void before(){
		System.out.println("before");
	}
	
	@Test
	public void Test1() throws Exception{
		JFrame frame=new JFrame("JasonFrame");
		frame.setName("Jason");
		JPanel panel=new JPanel();
		JMenu munuFile=new JMenu("File");
		JMenu munuEdit=new JMenu("Edit");
		JMenu munuExit=new JMenu("exit");

		JMenuItem mi1=new JMenuItem("aa");
		JMenuItem mi2=new JMenuItem("bb");
		munuFile.add(mi1);
		munuEdit.add(mi2);
		JMenuBar munuBar=new JMenuBar();
		munuBar.add(munuEdit);
		munuBar.add(munuFile);
		munuBar.add(munuExit);
		panel.add(munuBar);
		
		frame.add(munuBar,BorderLayout.NORTH);
		/*SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				jl.setText("run nwo");
			}
		});*/
		display(frame);
//		Thread.sleep(10000);
		
		
	}
	
	public void display(JFrame frame){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		frame.setVisible(true);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

