package org.xper.joseph.experiment;

import org.xper.experiment.StimSpecGenerator;

public interface DynamicStimSpecGenerator extends StimSpecGenerator {
	public String generateStimSpec();
	public void setParam(String param, Object value);
}
