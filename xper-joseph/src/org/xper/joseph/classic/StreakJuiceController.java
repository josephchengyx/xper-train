package org.xper.joseph.classic;

import org.xper.Dependency;
import org.xper.classic.TrialEventListener;
import org.xper.classic.vo.TrialContext;
import org.xper.juice.Juice;

import java.sql.Timestamp;


public class StreakJuiceController implements TrialEventListener {
	
	@Dependency
	Juice juice;

	@Dependency
	public int defaultReward;

	@Dependency
	public int maxReward;

	private int rewardSize;

	public void eyeInBreak(long timestamp, TrialContext context) {
		resetReward();
	}

	public void eyeInHoldFail(long timestamp, TrialContext context) {
		resetReward();
	}

	public void fixationPointOn(long timestamp, TrialContext context) {
	}

	public void fixationSucceed(long timestamp, TrialContext context) {
	}

	public void initialEyeInFail(long timestamp, TrialContext context) {
	}

	public void initialEyeInSucceed(long timestamp, TrialContext context) {
	}

	public void trialComplete(long timestamp, TrialContext context) {
		deliverReward();
		updateReward();
		System.out.println("Juice delivered @ " + new Timestamp(timestamp/1000).toString());
	}
	
	public void trialInit(long timestamp, TrialContext context) {
	}

	public void trialStart(long timestamp, TrialContext context) {
	}

	public void trialStop(long timestamp, TrialContext context) {
	}

	private void deliverReward() {
		for (int i = 0; i < rewardSize; i++) {
			juice.deliver();
		}
		System.out.println("Delivering " + rewardSize + " pulses of juice");
	}

	private void updateReward() {
		rewardSize = Math.min(rewardSize + 1, maxReward);
	}

	private void resetReward() {
		rewardSize = defaultReward;
	}

	public Juice getJuice() {
		return juice;
	}

	public void setJuice(Juice juice) {
		this.juice = juice;
	}

	public int getDefaultReward() {
		return defaultReward;
	}

	public void setDefaultReward(int defaultReward) {
		this.defaultReward = defaultReward;
		this.rewardSize = defaultReward;
	}

	public int getMaxReward() {
		return maxReward;
	}

	public void setMaxReward(int maxReward) {
		this.maxReward = maxReward;
	}

}
