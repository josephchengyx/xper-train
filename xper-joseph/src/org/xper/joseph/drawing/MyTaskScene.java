package org.xper.joseph.drawing;

import org.xper.Dependency;
import org.xper.drawing.AbstractTaskScene;
import org.xper.drawing.Context;
import org.xper.drawing.GLUtil;
import org.xper.experiment.ExperimentTask;
import org.xper.drawing.object.Circle;
import org.xper.joseph.experiment.CircleSpec;

import java.util.Random;

public class MyTaskScene extends AbstractTaskScene {
//    @Dependency
    public double size;

//    @Dependency
    public boolean solid;

//    @Dependency
    public double tx;

//    @Dependency
    public double ty;

//    @Dependency
    public double tz;

    public ExperimentTask task;

    public void drawStimulus(Context context) {
        CircleSpec spec = CircleSpec.fromXml(task.getStimSpec());
        GLUtil.drawCircle(new Circle(),
                spec.getSize(),
                spec.getSolid(),
                spec.getTx(),
                spec.getTy(),
                spec.getTz());
    }

    public void setTask(ExperimentTask task) {
        this.task = task;
    }

}
