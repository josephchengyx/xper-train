package org.xper.joseph.experiment;

import org.xper.Dependency;
import org.xper.experiment.StimSpecGenerator;
import org.xper.joseph.classic.StreakEventListener;

public class RandomCircleSpecGenerator implements StimSpecGenerator, StreakEventListener {

    @Dependency
    public boolean solid;

    @Dependency
    public String defaultColor;

    @Dependency
    public String successColor;

    @Dependency
    public String failureColor;

    private String color;

    @Dependency
    public double minSize;

    @Dependency
    public double maxSize;

    @Dependency
    public double minOffset;

    @Dependency
    public double maxOffset;

    @Override
    public String generateStimSpec() {
        CircleSpec stimSpec = new CircleSpec();
        stimSpec.setSize(getRandomNumber(minSize, maxSize));
        stimSpec.setSolid(solid);
        stimSpec.setColor(color);
        stimSpec.setTx(getRandomNumber(minOffset, maxOffset));
        stimSpec.setTy(getRandomNumber(minOffset, maxOffset));
        stimSpec.setTz(0);
        return stimSpec.toXml();
    }

    @Override
    public void successStreak() {
        color = successColor;
    }

    @Override
    public void failureStreak() {
        color = failureColor;
    }

    @Override
    public void streakBreak() {
        color = defaultColor;
    }

    private double getRandomNumber(double min, double max) {
        return (max - min) * Math.random() + min;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public void setDefaultColor(String color) {
        this.defaultColor = color;
        this.color = defaultColor;
    }

    public void setSuccessColor(String color) {
        this.successColor = color;
    }

    public void setFailureColor(String color) {
        this.failureColor = color;
    }

    public void setMinSize(double minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(double maxSize) {
        this.maxSize = maxSize;
    }

    public void setMinOffset(double minOffset) {
        this.minOffset = minOffset;
    }

    public void setMaxOffset(double maxOffset) {
        this.maxOffset = maxOffset;
    }
}
