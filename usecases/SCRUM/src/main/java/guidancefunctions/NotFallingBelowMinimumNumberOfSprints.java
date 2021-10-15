package guidancefunctions;

import SprintPlanning.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class NotFallingBelowMinimumNumberOfSprints implements IGuidanceFunction {

	public double computeFitness(Solution solution) {
		Plan p = ((Plan)solution.getModel());
		int currentSprints = p.getCurrentSprints();
		int minSprints = p.getMinSprints();
		if (currentSprints > minSprints) {
			return 0;
		}
		return minSprints - currentSprints;
	}

	public String getName() {
		return "Has minimium sprint count";
	}

}
