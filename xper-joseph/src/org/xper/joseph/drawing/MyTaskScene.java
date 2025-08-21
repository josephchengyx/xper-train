package org.xper.joseph.drawing;

import org.xper.drawing.AbstractTaskScene;
import org.xper.drawing.Context;
import org.xper.joseph.drawing.GLUtil;
import org.xper.experiment.ExperimentTask;
import org.xper.drawing.object.Circle;
import org.xper.joseph.experiment.CircleSpec;


public class MyTaskScene extends AbstractTaskScene {

    public ExperimentTask task;

    public void drawStimulus(Context context) {
        CircleSpec spec = CircleSpec.fromXml(task.getStimSpec());
//        printStimSpec(spec);
        GLUtil.drawCircle(new Circle(),
                spec.getSize(),
                spec.getSolid(),
                spec.getColor(),
                spec.getTx(),
                spec.getTy(),
                spec.getTz());
    }

    public void setTask(ExperimentTask task) {
        this.task = task;
    }

    private static void printStimSpec(CircleSpec spec) {
        System.out.println("size: "+ spec.getSize()
                + ", offsets: " + spec.getTx() + ", "
                + spec.getTy() + ", "
                + spec.getTz());
    }

}
