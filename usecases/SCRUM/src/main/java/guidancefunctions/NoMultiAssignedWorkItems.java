package guidancefunctions;

import SprintPlanning.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class NoMultiAssignedWorkItems implements IGuidanceFunction {

	public double computeFitness(Solution solution) {
		
		var workItems = ((Plan) solution.getModel()).getBacklog().getWorkitems();	
		return workItems.stream().filter(item -> item.getIsPlannedFor().size() > 1).count();
	}

	public String getName() {
		return "No workitems assigned to multiple sprints";
	}

}
