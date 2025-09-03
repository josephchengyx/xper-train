package org.xper.joseph.classic;

import org.xper.Dependency;
import org.xper.classic.SlideEventListener;
import org.xper.classic.TrialEventListener;
import org.xper.classic.vo.TrialContext;
import org.xper.experiment.ExperimentTask;
import org.xper.joseph.experiment.CircleSpec;
import org.xper.juice.Juice;

import java.sql.Timestamp;
import java.util.LinkedList;


public class StimJuiceController implements TrialEventListener, SlideEventListener {
	
	@Dependency
	Juice juice;

	@Dependency
	private int maxReward;

	@Dependency
	public double rewardRatio;

	private int rewardSize;

	private TrialContext currentContext;

	private LinkedList<Double> stimSizeList = new LinkedList<>();


	public void eyeInBreak(long timestamp, TrialContext context) {
	}

	public void eyeInHoldFail(long timestamp, TrialContext context) {

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
		updateReward();
		deliverReward();
		System.out.println("Juice delivered @ " + new Timestamp(timestamp/1000).toString());
	}
	
	public void trialInit(long timestamp, TrialContext context) {
	}

	public void trialStart(long timestamp, TrialContext context) {
		currentContext = context;
	}

	public void trialStop(long timestamp, TrialContext context) {
		stimSizeList = new LinkedList<>();
	}

	public void slideOn(int index, long timestamp) {
		ExperimentTask currentTask = currentContext.getCurrentTask();
		if (currentTask != null) {
			stimSizeList.add(CircleSpec.fromXml(currentTask.getStimSpec()).getSize());
		}
	}

	public void slideOff(int index, long timestamp, int frameCount) {
	}

	private void deliverReward() {
		for (int i = 0; i < rewardSize; i++) {
			juice.deliver();
		}
		String rewardMessage = "Delivering " + rewardSize + " pulses of juice";
		if ((double) rewardSize / maxReward >= Math.random()) {
			juice.deliver();
			rewardMessage = rewardMessage + " + 1 bonus pulse of juice";
		}
		System.out.println(rewardMessage);
	}

	private void updateReward() {
		double maxSize = 0;
		for (Double size : stimSizeList) {
			if (size > maxSize) {
				maxSize = size;
			}
		}
		rewardSize = stimSizeToReward(maxSize);
	}

	private int stimSizeToReward(double stimSize) {
		return (int) (stimSize / rewardRatio);
	}

	public Juice getJuice() {
		return juice;
	}

	public void setJuice(Juice juice) {
		this.juice = juice;
	}

	public int getMaxReward() {
		return maxReward;
	}

	public void setMaxReward(double maxStimSize) {
		this.maxReward = stimSizeToReward(maxStimSize);
	}

	public double getRewardRatio() {
		return rewardRatio;
	}

	public void setRewardRatio(double rewardRatio) {
		this.rewardRatio = rewardRatio;
	}

}
