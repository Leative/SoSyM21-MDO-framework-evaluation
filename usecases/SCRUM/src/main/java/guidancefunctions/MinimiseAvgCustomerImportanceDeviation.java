package guidancefunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import SprintPlanning.Plan;
import SprintPlanning.Sprint;
import SprintPlanning.Stakeholder;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseAvgCustomerImportanceDeviation implements IGuidanceFunction {

	@Override
	public double computeFitness(Solution solution) {
		Plan plan = (Plan) solution.getModel();
		StandardDeviation stdDevCalculator = new StandardDeviation();
		ArrayList<Double> stakeholderImportanceDeviation = new ArrayList<>();

		for (Stakeholder stakeholder : plan.getStakeholders()) {
			double[] importanceValues = plan.getSprints().stream()
					.map((sprint) -> sprint.getCommittedItem().stream()
							.filter((item) -> item.getStakeholder().equals(stakeholder))
							.mapToInt((item) -> item.getImportance())
							.sum())
					.mapToDouble((intSum) -> intSum.doubleValue())
					.toArray(); // List of total importance of each sprint for this stakeholder (list contains zero for empty sprints)
			Double importanceDeviation = stdDevCalculator.evaluate(importanceValues); //Empty sprints increase deviation
			stakeholderImportanceDeviation.add(importanceDeviation);
		}
		Double totalDeviation = stakeholderImportanceDeviation.stream().reduce(0d, (res, val) -> res + val ); //Empty sprint increases the deviation for each stakeholder. This depends on the number of sprints (few sprints -> high influence of emtpy one) and the magnitude of the importance of a stakeholder's items (high importance value -> high influence of empty sprint).
		return totalDeviation/stakeholderImportanceDeviation.size(); // In total, empty sprints increase the avg. deviation
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Minimise avg. std. deviation of customer importance";
	}

}
