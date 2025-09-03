package org.xper.joseph.experiment;

import org.xper.Dependency;
import org.xper.exception.VariableNotFoundException;
import org.xper.experiment.StimSpecGenerator;
import org.xper.time.TimeUtil;
import org.xper.util.DbUtil;

public class TaskGenerator {

    @Dependency
    DbUtil dbUtil;

    @Dependency
    TimeUtil globalTimeUtil;

    @Dependency
    DynamicStimSpecGenerator generator;

    @Dependency
    int taskCount;

    @Dependency
    double maxSize;

    @Dependency
    double minSize;

    public void generate() {
        System.out.print("Generating ");
        long genId = 1;
        try {
            genId = dbUtil.readReadyGenerationInfo().getGenId() + 1;
        } catch (VariableNotFoundException e) {
            dbUtil.writeReadyGenerationInfo(genId, 0);
        }
        double size = maxSize;
        double dSize = (maxSize - minSize) / 10;
        double sign = 1;
        for (int i = 0; i < taskCount; i++) {
            if (i % 10 == 0) {
                System.out.print(".");
                sign *= -1;
            } else {
                size += sign * dSize;
            }
            generator.setParam("size", size);
            String spec = generator.generateStimSpec();
            long taskId = globalTimeUtil.currentTimeMicros();
            dbUtil.writeStimSpec(taskId, spec);
            dbUtil.writeTaskToDo(taskId, taskId, -1, genId);
        }
        dbUtil.updateReadyGenerationInfo(genId, taskCount);
        System.out.println("done.");
    }

    public DbUtil getDbUtil() {
        return dbUtil;
    }

    public void setDbUtil(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public TimeUtil getGlobalTimeUtil() {
        return globalTimeUtil;
    }

    public void setGlobalTimeUtil(TimeUtil globalTimeUtil) {
        this.globalTimeUtil = globalTimeUtil;
    }

    public StimSpecGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(DynamicStimSpecGenerator generator) {
        this.generator = generator;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }


    public double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(double maxSize) {
        this.maxSize = maxSize;
    }

    public double getMinSize() {
        return minSize;
    }

    public void setMinSize(double minSize) {
        this.minSize = minSize;
    }
}
