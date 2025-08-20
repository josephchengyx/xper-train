package org.xper.joseph.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.config.java.annotation.*;
import org.springframework.config.java.annotation.valuesource.SystemPropertiesValueSource;
import org.springframework.config.java.plugin.context.AnnotationDrivenConfig;
import org.springframework.config.java.util.DefaultScopes;
import org.xper.classic.SlideEventListener;
import org.xper.classic.TrialEventListener;
import org.xper.config.AcqConfig;
import org.xper.config.BaseConfig;
import org.xper.config.ClassicConfig;
import org.xper.drawing.TaskScene;
import org.xper.drawing.object.BlankScreen;
import org.xper.experiment.StimSpecGenerator;
import org.xper.experiment.TaskDataSource;
import org.xper.experiment.mock.RandomTaskDataSource;
import org.xper.joseph.classic.StimJuiceController;
import org.xper.joseph.classic.StreakController;
import org.xper.joseph.classic.StreakEventListener;
import org.xper.joseph.classic.StreakJuiceController;
import org.xper.joseph.drawing.MyTaskScene;
import org.xper.joseph.experiment.RandomCircleSpecGenerator;
import org.xper.juice.mock.NullDynamicJuice;

import java.util.LinkedList;
import java.util.List;


@Configuration(defaultLazy=Lazy.TRUE)
@SystemPropertiesValueSource
@AnnotationDrivenConfig
@Import(ClassicConfig.class)
public class MyConfig {
	@Autowired
	BaseConfig baseConfig;
	@Autowired
	ClassicConfig classicConfig;
	@Autowired
	AcqConfig acqConfig;

	@ExternalValue("experiment.streak_threshold")
	public int experimentStreakThreshold;

	@Bean
	public TaskScene taskScene() {
		MyTaskScene scene = new MyTaskScene();
		scene.setRenderer(classicConfig.experimentGLRenderer());
		scene.setFixation(classicConfig.experimentFixationPoint());
		scene.setMarker(classicConfig.screenMarker());
		scene.setBlankScreen(new BlankScreen());
		return scene;
	}

	@Bean
	public TaskDataSource taskDataSource() {
		RandomTaskDataSource dataSource = new RandomTaskDataSource();
		dataSource.setGenerator(stimSpecGenerator());
		return dataSource;
	}

	@Bean
	public StimSpecGenerator stimSpecGenerator() {
		RandomCircleSpecGenerator generator = new RandomCircleSpecGenerator();
		generator.setSolid(xperStimCircleSolid());
		generator.setMinSize(xperStimCircleMinSize());
		generator.setMaxSize(xperStimCircleMaxSize());
		generator.setMinOffset(xperStimCircleMinOffset());
		generator.setMaxOffset(xperStimCircleMaxOffset());
		return generator;
	}

	@Bean
	public TrialEventListener juiceController() {
//		StreakJuiceController controller = new StreakJuiceController();
		StimJuiceController controller = new StimJuiceController();
		controller.setJuice(new NullDynamicJuice());
		controller.setRewardRatio(xperRewardCircleSizeRatio());
		controller.setMaxReward(xperStimCircleMaxSize());
		return controller;
	}

	@Bean
	public StreakController streakController() {
		StreakController controller = new StreakController();
		controller.setStreakThreshold(experimentStreakThreshold);
		List<StreakEventListener> listeners = new LinkedList<>();
		listeners.add((StreakEventListener) stimSpecGenerator());
		controller.setStreakEventListeners(listeners);
		return controller;
	}

	@Bean (scope = DefaultScopes.PROTOTYPE)
	public List<TrialEventListener> trialEventListeners () {
		List<TrialEventListener> trialEventListener = new LinkedList<>();
		trialEventListener.add(classicConfig.eyeMonitorController());
		trialEventListener.add(classicConfig.trialEventLogger());
		trialEventListener.add(classicConfig.experimentProfiler());
		trialEventListener.add(classicConfig.messageDispatcher());
		trialEventListener.add(juiceController());
		trialEventListener.add(classicConfig.trialSyncController());
		trialEventListener.add(classicConfig.dataAcqController());
		trialEventListener.add(classicConfig.jvmManager());
		trialEventListener.add(streakController());
		return trialEventListener;
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public List<SlideEventListener> slideEventListeners () {
		List<SlideEventListener> listeners = new LinkedList<>();
		listeners.add(classicConfig.slideEventLogger());
		listeners.add(classicConfig.experimentProfiler());
		listeners.add(classicConfig.messageDispatcher());
		listeners.add((SlideEventListener) juiceController());
		return listeners;
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Boolean xperStimCircleSolid() {
		return Boolean.parseBoolean(baseConfig.systemVariableContainer().get("xper_stim_circle_solid", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Double xperStimCircleMinSize() {
		return Double.parseDouble(baseConfig.systemVariableContainer().get("xper_stim_circle_min_size", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Double xperStimCircleMaxSize() {
		return Double.parseDouble(baseConfig.systemVariableContainer().get("xper_stim_circle_max_size", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Double xperStimCircleMinOffset() {
		return Double.parseDouble(baseConfig.systemVariableContainer().get("xper_stim_circle_min_offset", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Double xperStimCircleMaxOffset() {
		return Double.parseDouble(baseConfig.systemVariableContainer().get("xper_stim_circle_max_offset", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Integer xperRewardDefaultSize() {
		return Integer.parseInt(baseConfig.systemVariableContainer().get("xper_reward_default_size", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Integer xperRewardMaxSize() {
		return Integer.parseInt(baseConfig.systemVariableContainer().get("xper_reward_max_size", 0));
	}

	@Bean(scope = DefaultScopes.PROTOTYPE)
	public Double xperRewardCircleSizeRatio() {
		return Double.parseDouble(baseConfig.systemVariableContainer().get("xper_reward_circle_size_ratio", 0));
	}

}
