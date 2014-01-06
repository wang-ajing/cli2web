package org.summer.cli2web.viewmodel;

import java.util.ArrayList;
import java.util.List;

import sun.org.mozilla.javascript.internal.json.JsonParser;

public class ProcessStatusModel {
	private CurrentProgress currentProgress;
	private List<String> keyInformation = new ArrayList<String>();
	private boolean needSubmitData;
	private AbstractDataFormat expectedData;
	private boolean failed;

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public CurrentProgress getCurrentProgress() {
		return currentProgress;
	}

	public void setCurrentProgress(CurrentProgress currentProgress) {
		this.currentProgress = currentProgress;
	}

	public List<String> getKeyInformation() {
		return keyInformation;
	}

	public void setKeyInformation(List<String> keyInformation) {
		this.keyInformation = keyInformation;
	}

	public boolean isNeedSubmitData() {
		return needSubmitData;
	}

	public void setNeedSubmitData(boolean needSubmitData) {
		this.needSubmitData = needSubmitData;
	}

	public AbstractDataFormat getExpectedData() {
		return expectedData;
	}

	public void setExpectedData(AbstractDataFormat expectedData) {
		this.expectedData = expectedData;
	}

	public void merge(ProcessStatusModel model) {
		if (currentProgress == null) {
			currentProgress = model.currentProgress;
		}
	}

	public String toJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		boolean appendComma = false;
		if (currentProgress != null) {
			sb.append("\"currentProgress\" : "
					+ currentProgress.getCurrentProgress() + ",");
			sb.append("\"totalProgress\": "
					+ currentProgress.getTotalProgress());
			appendComma = true;
		}

		if (expectedData != null) {
			if (appendComma) {
				sb.append(",");
			}
			sb.append("\"expectedData\": " + expectedData.toString());
		}
		sb.append("}");
		return sb.toString();
	}
}
