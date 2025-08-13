package org.xper.joseph.experiment;

import java.util.Random;
import org.xper.experiment.StimSpecGenerator;

public class RandomCircleSpecGenerator implements StimSpecGenerator {

    private Random rng;

    public RandomCircleSpecGenerator() {
        this.rng = new Random();
    }

    @Override
    public String generateStimSpec() {
        CircleSpec stimSpec = new CircleSpec();
        stimSpec.setSize(getRandomNumber(20, 100));
        stimSpec.setSolid(true);
        stimSpec.setTx(getRandomNumber(20, 100));
        stimSpec.setTy(getRandomNumber(20, 100));
        stimSpec.setTz(getRandomNumber(20, 100));
        return stimSpec.toXml();
    }

    private double getRandomNumber(double min, double max) {
        return (max - min) * rng.nextDouble() + min;
    }
}
