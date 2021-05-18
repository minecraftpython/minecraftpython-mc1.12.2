package org.sapphon.minecraft.modding.minecraftpython.io.web;

import org.json.JSONObject;
import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;

public class PythonInterpretingHttpPostHandler extends Thread {

	private static final String PYTHONPROGRAM_JSON_KEY = "pythonprogram";

	static final String HTML_START = "<html>"
			+ "<title>HTTP POST Server in java</title>" + "<body>";

	static final String HTML_END = "</body>" + "</html>";

	static final String NEWLINE = "\r\n";
	
	Socket connectedClient = null;
	BufferedReader inFromClient = null;
	DataOutputStream outToClient = null;

	private SpellInterpreter interpreter;

	public PythonInterpretingHttpPostHandler(Socket client, SpellInterpreter interpreter) {
		connectedClient = client;
		this.interpreter = interpreter;
	}

	public void run() {
		String wholeRequest = "";
		try {

			inFromClient = new BufferedReader(new InputStreamReader(
					connectedClient.getInputStream()));
			outToClient = new DataOutputStream(
					connectedClient.getOutputStream());

			String currentLine = inFromClient.readLine();
			wholeRequest += currentLine + NEWLINE;
			String headerLine = currentLine;
			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			String httpMethod = tokenizer.nextToken();
			System.out.println(httpMethod);
			String httpQueryString = tokenizer.nextToken();
			do {
				currentLine = inFromClient.readLine();
				if (currentLine == null){
					break;
				}
				else{
					wholeRequest += currentLine + NEWLINE;
				}
			} while (inFromClient.ready()); // End of do-while
		} catch (Exception e) {
			JavaProblemHandler.printErrorMessageToDialogBox(e);
		} finally {
			try {
				sendResponse(200, "Spell received");
			}catch(SocketException socketException){
				String message = "Oops! Minecraft is busy, please wait a second! :-)"
						+ "\n(A SocketException occurred.)";
				
				JavaProblemHandler.printErrorMessageToDialogBox(new Exception(message,socketException));
			}
			catch (Exception e) {
				JavaProblemHandler.printErrorMessageToDialogBox(new Exception("Failed to parse incoming HTTP request containing spell.",e));
			}
		}
		String requestBodyThatWeExpectIsJSON = wholeRequest.substring(
				wholeRequest.indexOf("{\"")).trim();
		JSONObject jsonObject = new JSONObject(requestBodyThatWeExpectIsJSON);
		String program = jsonObject.getString(PYTHONPROGRAM_JSON_KEY);
		this.interpreter.interpretPython(program);
	}

	public void sendResponse(int statusCode, String responseString) throws Exception {

		String statusLine = null;
		String serverdetails = "Server: Java HTTPServer";
		String contentLengthLine = null;
		String fileName = null;
		
		String contentTypeLine = "Content-Type: text/html" + NEWLINE;
		FileInputStream fin = null;

		if (statusCode == 200){
			statusLine = "HTTP/1.1 200 OK" + NEWLINE;
		}
		else{
			statusLine = "HTTP/1.1 404 Not Found" + NEWLINE;
		}

		responseString = PythonInterpretingHttpPostHandler.HTML_START
				+ responseString + PythonInterpretingHttpPostHandler.HTML_END;
		contentLengthLine = "Content-Length: " + responseString.length()
				+ NEWLINE;

		outToClient.close();

	}

}
