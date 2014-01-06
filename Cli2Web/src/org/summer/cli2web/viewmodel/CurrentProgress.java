package org.summer.cli2web.viewmodel;

public class CurrentProgress {
	private int currentProgress;
	private int totalProgress;

	public CurrentProgress(int currentProgress, int totalProgress) {
		this.currentProgress = currentProgress;
		this.totalProgress = totalProgress;
	}

	public CurrentProgress(int currentProgress) {
		this(currentProgress, 100);
	}

	public int getCurrentProgress() {
		return currentProgress;
	}

	public void setCurrentProgress(int currentProgress) {
		this.currentProgress = currentProgress;
	}

	public int getTotalProgress() {
		return totalProgress;
	}

	public void setTotalProgress(int totalProgress) {
		this.totalProgress = totalProgress;
	}

	public boolean finished() {
		return this.totalProgress == this.currentProgress;
	}
}
