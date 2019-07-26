package org.sapphon.minecraft.modding.minecraftpython.problemhandlers;

import org.sapphon.minecraft.modding.base.MsgBox;

public class JavaProblemHandler extends AbstractProblemHandler {
	public static void printErrorMessageToDialogBox(Exception e){
		if(ITS_FOR_THE_KIDS){
			MsgBox.error(getStackTraceFromException(e), "Java/Minecraft Failure - This Is Not Your Fault - Grab An Instructor");
		}
		else{
			new Thread(new DisplayMessageRunnable(getStackTraceFromException(e), "Java Exception")).start();
		}
	}
}
