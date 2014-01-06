package org.summer.cli2web.action;

import java.io.File;
import java.io.IOException;

public class StartProcessAction {
	public Process startProcess(String currentPath, String command)
			throws IOException {

		ProcessBuilder pb = new ProcessBuilder(command.split(" "));
		pb.directory(new File(currentPath));
		return pb.start();
	}
}
