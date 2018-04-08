package DataStructures;

import java.util.ArrayList;

import javax.script.ScriptException;

public class GraphingContainer {

	private String equation;
	private int xrange;
	private int yrange;
	private int precision;
	
	public GraphingContainer(String equation, int xrange, int yrange, int precision) {
		this.equation = equation;
		// this equation just needs to be in the form of "x^2" or "x + 2"
			// the variable needs to be x, but spacing does not matter.
		
		this.xrange = xrange;
		this.yrange = yrange;
		// these will just be the max values for the graph.
			// so if the x range is 10, then the graph will go from
			// -10 to 10 on the xvalues.
		
		this.precision = precision;
		// we could make this a preset value, but left it here so it could
		// be experimented with. This will be the percentage of which the x values
		// increase. So if the xrange is 50 (so the graph goes from -50 to 50) and the 
		// precision is 1 (or 1%) then there will be an yvalue for every increase in one.
			// this was a bad example because it would be once every increase in one on the xvalues
			// if there were 100, but because of 0 there will be 101 :(
	}
	
	public ArrayList<XYContainer> run() throws ScriptException {
		ArrayList<XYContainer> result = new ArrayList<XYContainer>();
		
		double index = (xrange + 1.0 + xrange) / (precision + 0.0);
		
		for (int x = -xrange; x < xrange; x += index) {
			XYContainer xy = new XYContainer(equation, x);
			result.add(xy);
		}
		XYContainer xy = new XYContainer(equation, xrange);
		result.add(xy);
		
		return result;
	}

}
