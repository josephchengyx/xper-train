package org.xper.joseph.classic;

import org.xper.Dependency;
import org.xper.classic.TrialEventListener;
import org.xper.classic.vo.TrialContext;
import org.xper.joseph.classic.StreakEventListener;

import java.util.List;

public class StreakController implements TrialEventListener {

    private boolean isSuccess = true;

    private int streakSize = 0;

    @Dependency
    public int streakThreshold;

    public List<? extends StreakEventListener> streakEventListeners;

    @Override
    public void trialInit(long timestamp, TrialContext context) {

    }

    @Override
    public void trialStart(long timestamp, TrialContext context) {

    }

    @Override
    public void fixationPointOn(long timestamp, TrialContext context) {

    }

    @Override
    public void initialEyeInFail(long timestamp, TrialContext context) {

    }

    @Override
    public void initialEyeInSucceed(long timestamp, TrialContext context) {

    }

    @Override
    public void eyeInHoldFail(long timestamp, TrialContext context) {
        onFailure();
    }

    @Override
    public void fixationSucceed(long timestamp, TrialContext context) {

    }

    @Override
    public void eyeInBreak(long timestamp, TrialContext context) {
        onFailure();
    }

    @Override
    public void trialComplete(long timestamp, TrialContext context) {
        onSuccess();
    }

    @Override
    public void trialStop(long timestamp, TrialContext context) {

    }

    private void onSuccess() {
        if (isSuccess) {
            streakSize++;
        } else {
            streakSize = 0;
            isSuccess = true;
            for (StreakEventListener listener: streakEventListeners) {
                listener.streakBreak();
            }
        }
        if (isSuccess && streakSize > streakThreshold) {
            for (StreakEventListener listener: streakEventListeners) {
                listener.successStreak();
            }
        }
    }

    private void onFailure() {
        if (!isSuccess) {
            streakSize++;
        } else {
            streakSize = 0;
            isSuccess = false;
            for (StreakEventListener listener: streakEventListeners) {
                listener.streakBreak();
            }
        }
        if (!isSuccess && streakSize > streakThreshold) {
            for (StreakEventListener listener: streakEventListeners) {
                listener.failureStreak();
            }
        }
    }

    public int getStreakThreshold() {
        return streakThreshold;
    }

    public void setStreakThreshold(int streakThreshold) {
        this.streakThreshold = streakThreshold;
    }

    public List<? extends StreakEventListener> getStreakEventListeners() {
        return streakEventListeners;
    }

    public void setStreakEventListeners(
            List<? extends StreakEventListener> streakEventListeners) {
        this.streakEventListeners = streakEventListeners;
    }
}
