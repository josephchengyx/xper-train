package org.xper.joseph.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.config.java.annotation.Bean;
import org.springframework.config.java.annotation.Configuration;
import org.springframework.config.java.annotation.Import;
import org.springframework.config.java.annotation.Lazy;
import org.springframework.config.java.annotation.valuesource.SystemPropertiesValueSource;
import org.springframework.config.java.plugin.context.AnnotationDrivenConfig;
import org.xper.config.AcqConfig;
import org.xper.config.BaseConfig;
import org.xper.config.ClassicConfig;
import org.xper.drawing.TaskScene;
import org.xper.drawing.object.BlankScreen;
import org.xper.drawing.renderer.PerspectiveStereoRenderer;
import org.xper.experiment.StimSpecGenerator;
import org.xper.experiment.TaskDataSource;
import org.xper.experiment.mock.RandomTaskDataSource;
import org.xper.joseph.drawing.MyTaskScene;
import org.xper.joseph.experiment.RandomCircleSpecGenerator;


@Configuration(defaultLazy=Lazy.TRUE)
@SystemPropertiesValueSource
@AnnotationDrivenConfig
@Import(ClassicConfig.class)
public class MyConfig {
	@Autowired
	BaseConfig baseConfig;
	@Autowired ClassicConfig classicConfig;
	@Autowired
	AcqConfig acqConfig;


	@Bean
	public PerspectiveStereoRenderer experimentGLRenderer () {
		PerspectiveStereoRenderer renderer = new PerspectiveStereoRenderer();
		//PerspectiveRenderer renderer = new PerspectiveRenderer();				// not using stereo
		renderer.setDistance(classicConfig.xperMonkeyScreenDistance());
		renderer.setDepth(classicConfig.xperMonkeyScreenDepth());
		renderer.setHeight(classicConfig.xperMonkeyScreenHeight());
		renderer.setWidth(classicConfig.xperMonkeyScreenWidth());
		renderer.setPupilDistance(classicConfig.xperMonkeyPupilDistance());
		renderer.setInverted(classicConfig.xperMonkeyScreenInverted());  		// only used for stereo rendering
		return renderer;
	}

	@Bean
	public TaskScene taskScene() {
		MyTaskScene scene = new MyTaskScene();
		scene.setRenderer(experimentGLRenderer());
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
		return new RandomCircleSpecGenerator();
	}
}
