package guidancefunctions;

import SprintPlanning.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class NotExceedingMaximumNumberOfSprints implements IGuidanceFunction {

	public double computeFitness(Solution solution) {
		Plan p = ((Plan)solution.getModel());
		int currentSprints = p.getCurrentSprints();
		int maxSprints = p.getMaxSprints();
		if (currentSprints < maxSprints) {
			return 0;
		}
		return currentSprints - maxSprints;
	}

	public String getName() {
		return "Has maximum sprint count";
	}

}
