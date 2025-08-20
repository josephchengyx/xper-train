package org.xper.joseph.experiment;

import org.xper.Dependency;
import org.xper.experiment.StimSpecGenerator;
import org.xper.joseph.classic.StreakEventListener;

public class RandomCircleSpecGenerator implements StimSpecGenerator, StreakEventListener {

    @Dependency
    public boolean solid;

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
        stimSpec.setTx(getRandomNumber(minOffset, maxOffset));
        stimSpec.setTy(getRandomNumber(minOffset, maxOffset));
        stimSpec.setTz(0);
        return stimSpec.toXml();
    }

    @Override
    public void successStreak() {
        solid = true;
    }

    @Override
    public void failureStreak() {
        solid = false;
    }

    private double getRandomNumber(double min, double max) {
        return (max - min) * Math.random() + min;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
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
