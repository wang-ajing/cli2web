package org.summer.cli2web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentProgressAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public RawProcessStatus currentProgress(Process proc, int timeout)
			throws IOException {
		if (proc == null) {
			return null;
		}
		RawProcessStatus status = new RawProcessStatus();
		status.setErrorInformation(new ArrayList<String>());
		status.setOutputInformation(new ArrayList<String>());
		try {
			int exitCode = proc.exitValue();
			status.setFinished(true);
			status.setExitCode(exitCode);
			if (exitCode != 0) {
				status.setFailed(true);
			}
		} catch (IllegalThreadStateException ex) {
			logger.info("process haven't finished");
			status.setFinished(false);
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		if (!reader.ready()) {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
			}
		}
		String line = null;
		while (reader.ready() && (line = reader.readLine()) != null) {
			status.getOutputInformation().add(line);
			if (line.startsWith("input")) {
				status.setNeedInput(true);
			}
		}

		reader = new BufferedReader(
				new InputStreamReader(proc.getErrorStream()));
		if (!reader.ready()) {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
			}
		}
		line = null;
		while (reader.ready() && (line = reader.readLine()) != null) {
			status.getErrorInformation().add(line);
		}

		return status;

	}
}
