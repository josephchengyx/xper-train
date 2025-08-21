package org.xper.joseph.experiment;

import com.thoughtworks.xstream.XStream;

public class CircleSpec {
	double size;

	boolean solid;

	String color;

	double tx;

	double ty;

	double tz;

	boolean animation;

	transient static XStream s;

	static {
		s = new XStream();
		s.alias("StimSpec", CircleSpec.class);
		s.useAttributeFor("animation", boolean.class);
	}

	public String toXml () {
		return CircleSpec.toXml(this);
	}

	public static String toXml (CircleSpec spec) {
		return s.toXML(spec);
	}

	public static CircleSpec fromXml (String xml) {
		CircleSpec g = (CircleSpec)s.fromXML(xml);
		return g;
	}

	public CircleSpec() {}

	public CircleSpec(CircleSpec d) {
		size = d.getSize();
		solid = d.getSolid();
		color = d.getColor();
		tx = d.getTx();
		ty = d.getTy();
		tz = d.getTz();
	}

	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public boolean getSolid() {
		return solid;
	}
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getTx() {
		return tx;
	}
	public void setTx(double tx) {
		this.tx = tx;
	}
	public double getTy() {
		return ty;
	}
	public void setTy(double ty) {
		this.ty = ty;
	}
	public double getTz() {
		return tz;
	}
	public void setTz(double tz) {
		this.tz = tz;
	}
	public boolean isAnimation() {
		return animation;
	}
	public void setAnimation(boolean animation) {
		this.animation = animation;
	}
}
