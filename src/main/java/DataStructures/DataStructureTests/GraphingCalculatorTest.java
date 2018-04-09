package DataStructures.DataStructureTests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Test;

import DataStructures.GraphingContainer;

public class GraphingCalculatorTest {

	@Test
	public void test() throws ScriptException {
		GraphingContainer graph = new GraphingContainer("x+1.5", 10, 10, 7);
		GraphingContainer graph2 = new GraphingContainer("x*2", 10, 10, 7);
		
		assertTrue(graph.toString().equals(" (-10.0, -8.5) (-7.0, -5.5) (-4.0, -2.5) (-1.0, 0.5) (2.0, 3.5) (5.0, 6.5) (8.0, 9.5) (10.0, 11.5)"));
		
	}

}
