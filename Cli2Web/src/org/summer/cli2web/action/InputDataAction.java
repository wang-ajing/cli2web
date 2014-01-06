package org.summer.cli2web.action;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InputDataAction {
	public void input(Process proc, String data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				proc.getOutputStream(), "UTF-8"));
		writer.write(data + "\n");
		writer.flush();
	}
}
