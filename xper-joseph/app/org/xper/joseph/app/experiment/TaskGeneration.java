package org.xper.joseph.app.experiment;

import org.springframework.config.java.context.JavaConfigApplicationContext;
import org.xper.joseph.experiment.TaskGenerator;
import org.xper.util.FileUtil;

public class TaskGeneration {
    public static void main(String[] args) {
        JavaConfigApplicationContext context = new JavaConfigApplicationContext(
                FileUtil.loadConfigClass("experiment.config_class"));
        TaskGenerator writer = context.getBean(TaskGenerator.class);
        writer.generate();
    }
}
