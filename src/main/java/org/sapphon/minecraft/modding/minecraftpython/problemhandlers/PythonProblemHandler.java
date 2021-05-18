package org.sapphon.minecraft.modding.minecraftpython.problemhandlers;

import net.minecraft.client.Minecraft;
import org.sapphon.minecraft.modding.gui.MsgBox;
import org.sapphon.minecraft.modding.gui.GuiPythonErrorMessage;

public class PythonProblemHandler extends AbstractProblemHandler {
	
	public static void printErrorMessageToDialogBox(Exception e) {
		String fullErrorMessage = getStackTraceFromException(e);
		String pythonErrorMessageFull = fullErrorMessage.split("at org.")[0];
		String pythonErrorMessageErrorOnly = pythonErrorMessageFull.split("\r\n")[2];
		if(ITS_FOR_THE_KIDS){
			MsgBox.error(pythonErrorMessageErrorOnly, "Python Interpreter Failure - See Below For Error Message");
		}
		else{
			Minecraft minecraft = Minecraft.getMinecraft();
			if(minecraft != null){
				System.out.println(fullErrorMessage);
				minecraft.displayGuiScreen(new GuiPythonErrorMessage(pythonErrorMessageFull));
			}
		}
	}
}
