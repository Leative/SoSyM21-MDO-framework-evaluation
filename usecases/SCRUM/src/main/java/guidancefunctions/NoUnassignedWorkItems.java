package guidancefunctions;

import SprintPlanning.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class NoUnassignedWorkItems implements IGuidanceFunction {

	public double computeFitness(Solution solution) {
		
		var workItems = ((Plan) solution.getModel()).getBacklog().getWorkitems();	
		return workItems.stream().filter(item -> item.getIsPlannedFor().isEmpty()).count();
	}

	public String getName() {
		return "No workitems are unassigned";
	}
}

