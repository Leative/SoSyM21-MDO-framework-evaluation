package utils;

import SprintPlanning.Backlog;
import SprintPlanning.Plan;
import SprintPlanning.SprintPlanningFactory;
import SprintPlanning.WorkItem;

public class TestMetamodel {
	
	public static void main(String[] args) {
		
		SprintPlanningFactory fac = SprintPlanningFactory.eINSTANCE;
		Plan p = fac.createPlan();
		Backlog b = fac.createBacklog();
		WorkItem w = fac.createWorkItem();
		p.setBacklog(b);
		b.getWorkitems().add(w);
		int result = p.getMaxSprints();
		System.out.println(result);
	}
	
}
