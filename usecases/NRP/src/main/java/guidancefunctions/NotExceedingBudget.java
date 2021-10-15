package guidancefunctions;

import nextReleaseProblem.NRP;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class NotExceedingBudget implements IGuidanceFunction {
	
	@Override
	public double computeFitness(Solution solution) {
		NRP nrpModel = (NRP)solution.getModel();
		double exceedance = nrpModel.getSolutions().get(0).getTotalCosts() - nrpModel.getBudget();
		if (exceedance < 0) {
			exceedance = 0.0d;
		}			
		//System.out.println("Budget exceeded by: " + exceedance);
		return exceedance;
	}

	@Override
	public String getName() {
		return "Budget Exceedance";
	}
}
