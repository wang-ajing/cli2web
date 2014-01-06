package org.summer.cli2web.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.summer.cli2web.viewmodel.CurrentProgress;
import org.summer.cli2web.viewmodel.ProcessStatusModel;
import org.summer.cli2web.viewmodel.TextDataFormat;

public class RawProcessStatus {
	private boolean needInput;
	private List<String> outputInformation;
	private boolean failed;
	private List<String> errorInformation;
	private boolean finished;
	private int exitCode;

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean hasFinished) {
		this.finished = hasFinished;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean hasFailed) {
		this.failed = hasFailed;
	}

	public List<String> getErrorInformation() {
		return errorInformation;
	}

	public void setErrorInformation(List<String> errorInformation) {
		this.errorInformation = errorInformation;
	}

	public boolean isNeedInput() {
		return needInput;
	}

	public void setNeedInput(boolean needInput) {
		this.needInput = needInput;
	}

	public List<String> getOutputInformation() {
		return outputInformation;
	}

	public void setOutputInformation(List<String> outputInformation) {
		this.outputInformation = outputInformation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProcessStatus \n[\nneedInput:");
		builder.append(needInput);
		builder.append(", \noutputInformation:");
		builder.append(outputInformation);
		builder.append(", \nfailed:");
		builder.append(failed);
		builder.append(", \nerrorInformation:");
		builder.append(errorInformation);
		builder.append(", \nfinished:");
		builder.append(finished);
		builder.append(", \nexitCode:");
		builder.append(exitCode);
		builder.append("\n]");
		return builder.toString();
	}

	public static final Pattern PROGRESS_PATTERN = Pattern
			.compile("progress: (\\d+)/(\\d+)");
	public static final Pattern KEY_INFORMATION_PATTERN = Pattern
			.compile("info: (.*)");
	private static final Pattern INPUT_PATTERN = Pattern.compile("input (.*?): ");

	public ProcessStatusModel toViewModel() {
		System.out.println(this.toString());
		ProcessStatusModel model = new ProcessStatusModel();
		if(this.finished){
			model.setCurrentProgress(new CurrentProgress(100));
		}
		if(this.failed){
			model.setFailed(true);
		}
		if (this.outputInformation.size() > 0) {
			for (String output : outputInformation) {
				Matcher matcher = PROGRESS_PATTERN.matcher(output);
				if (matcher.matches()) {
					model.setCurrentProgress(new CurrentProgress(Integer
							.parseInt(matcher.group(1)), Integer
							.parseInt(matcher.group(2))));
				}
				matcher = KEY_INFORMATION_PATTERN.matcher(output);
				if (matcher.matches()) {
					model.getKeyInformation().add(matcher.group(1));
				}
				matcher = INPUT_PATTERN.matcher(output);
				if(matcher.matches()){
					model.setNeedSubmitData(true);
					model.setExpectedData(new TextDataFormat(matcher.group(1)));
				}
			}
		}

		return model;

	}
}
