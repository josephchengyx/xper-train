package org.xper.joseph.experiment;

import org.xper.Dependency;
import org.xper.experiment.StimSpecGenerator;
import org.xper.joseph.classic.StreakEventListener;

public class RandomCircleSpecGenerator implements DynamicStimSpecGenerator, StreakEventListener {

    @Dependency
    public boolean solid;

    @Dependency
    public String defaultColor;

    @Dependency
    public String successColor;

    @Dependency
    public String failureColor;

    @Dependency
    public double minSize;

    @Dependency
    public double maxSize;

    @Dependency
    public double minOffset;

    @Dependency
    public double maxOffset;

    private String color;

    private Double size;

    private Double tx;

    private Double ty;

    private Double tz;

    @Override
    public String generateStimSpec() {
        CircleSpec stimSpec = new CircleSpec();
        stimSpec.setSolid(solid);
        stimSpec.setColor(color);
        if (size == null) { stimSpec.setSize(getRandomNumber(minSize, maxSize)); }
        else { stimSpec.setSize(size); }
        if (tx == null) { stimSpec.setTx(getRandomNumber(minOffset, maxOffset)); }
        else { stimSpec.setTx(tx); }
        if (ty == null) { stimSpec.setTy(getRandomNumber(minOffset, maxOffset)); }
        else { stimSpec.setTy(ty); }
        if (tz == null) { stimSpec.setTz(0); }
        else { stimSpec.setTz(0); }
        return stimSpec.toXml();
    }

    @Override
    public void setParam(String param, Object value) {
        switch (param) {
            case "size":
                if (value instanceof Double) {
                    setSize((double) value);
                }
                break;
            case "tx":
                if (value instanceof Double) {
                    setTx((double) value);
                }
                break;
            case "ty":
                if (value instanceof Double) {
                    setTy((double) value);
                }
                break;
            case "tz":
                if (value instanceof Double) {
                    setTz((double) value);
                }
                break;
            default:
                break;
        }
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

    public void setSize(double size) {
        this.size = size;
    }

    public void resetSize() {
        this.size = null;
    }

    public void setTx(double tx) {
        this.tx = tx;
    }

    public void resetTx() {
        this.tx = null;
    }

    public void setTy(double ty) {
        this.ty = ty;
    }

    public void resetTy() {
        this.ty = null;
    }

    public void setTz(double tz) {
        this.tz = tz;
    }

    public void resetTz() {
        this.tz = null;
    }
}
