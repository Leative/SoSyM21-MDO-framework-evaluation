package guidancefunctions;

import java.util.List;

import nextReleaseProblem.NRP;
import nextReleaseProblem.SoftwareArtifact;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

/**
 * Expresses the constraint that all dependencies of a selected artifact should also be selected. 
 * Otherwise the respective artifact cannot be fully functional.
 * 
 * The computation returns the number of selected artifacts violating the constraint.
 */
public class NoSAsWithUnselectedDependencies implements IGuidanceFunction {

	@Override
	public double computeFitness(Solution solution) {
		NRP nrpModel = (NRP)solution.getModel();
		List<SoftwareArtifact> artifacts = nrpModel.getSolutions().get(0).getSelectedArtifacts();
		return artifacts.stream().filter(artifact -> artifact.getRequires().stream().anyMatch(reqArtifact -> reqArtifact.getSolutions().isEmpty())).count();
	}

	@Override
	public String getName() {
		return "Number of SAs with unselected dependencies";
	}
	
}
