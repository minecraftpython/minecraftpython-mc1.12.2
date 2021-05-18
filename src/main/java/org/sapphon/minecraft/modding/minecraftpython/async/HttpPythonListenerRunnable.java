package org.sapphon.minecraft.modding.minecraftpython.async;

import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.io.web.PythonInterpretingHttpPostHandler;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpPythonListenerRunnable implements Runnable {

	private SpellInterpreter spellInterpreter;

	public HttpPythonListenerRunnable(SpellInterpreter spellInterpreter) {
		this.spellInterpreter = spellInterpreter;
	}

	@Override
	public void run() {

		ServerSocket server;
		try {
			server = new ServerSocket(57444, 10,
					InetAddress.getByName("127.0.0.1"));
			System.out.println("HTTP Server Waiting for client on port "
					+ server.getLocalPort());

			while (true) {
				Socket connected = server.accept();
				(new PythonInterpretingHttpPostHandler(connected, spellInterpreter))
						.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
