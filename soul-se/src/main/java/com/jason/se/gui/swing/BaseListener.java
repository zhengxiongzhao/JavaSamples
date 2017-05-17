package com.jason.se.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public abstract class BaseListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{	final ActionEvent event=e;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				Performed(event);
			}
		});
	}
	
	public abstract void Performed(ActionEvent e);
}
