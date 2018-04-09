package DataStructures;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import DataStructures.InputContainers.InputAdders.FunctionApplicationGetter;

public class XYContainer {

	String equation;
	double xval = 0.0;
	double yval = 0.0;

	public XYContainer(String equation, double xval) throws ScriptException {
		this.equation = equation;
		this.xval = xval;
		run();
	}

	private void run() throws ScriptException {

		String equationWithNewX = FunctionApplicationGetter.replace(equation, Double.toString(xval), "x");

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		yval = (double) engine.eval(equationWithNewX);
	}
<<<<<<< HEAD

	public double getX(){
		return this.xval;
	}

	public double getY(){
		return this.yval;
	}

=======
	
	public double getX() {return xval;};
	public double getY() {return yval;};
	
>>>>>>> 57ba9ad911a6e15392f9cfc86508c0e99f38c587
	@Override
	public String toString() {
		return "(" + Double.toString(xval) + ", " + Double.toString(yval) + ")";
	}

}
