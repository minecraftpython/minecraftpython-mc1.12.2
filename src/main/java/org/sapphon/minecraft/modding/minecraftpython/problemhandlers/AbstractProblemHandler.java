package org.sapphon.minecraft.modding.minecraftpython.problemhandlers;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AbstractProblemHandler implements IProblemHandler {

	protected static boolean ITS_FOR_THE_KIDS = false;

	protected static String getStackTraceFromException(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		
		String fullErrorMessage = stringWriter.toString();
		return fullErrorMessage;
	}
}

class DisplayMessageRunnable implements Runnable{

	private String message;
	private String title;

	public DisplayMessageRunnable(String message, String title){
		this.message = message;
		this.title = title;		
	}
	
	@Override
	public void run() {
        
	}
}
