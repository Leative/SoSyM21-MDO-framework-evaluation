package utils;

import org.eclipse.emf.ecore.EObject;

import SprintPlanning.Plan;

public class ModelPropertiesCalculator {
	public static void main(String[] args) {
		EObject modelRoot = LoadModel.loadModel();
		Plan planModel = (Plan)modelRoot;
		int numberOfItems = planModel.getBacklog().getWorkitems().size();
		int totalEffort = planModel.getBacklog().getWorkitems().stream().map(item -> item.getEffort()).reduce(0, Integer::sum);
		int totalImportance = planModel.getBacklog().getWorkitems().stream().map(item -> item.getImportance()).reduce(0, Integer::sum);
			
		System.out.println("Number of WorkItems in backlog: " + numberOfItems);
		System.out.println("Total WorkItem effort: " + totalEffort);
		System.out.println("Average WorkItem effort: " + ((double)totalEffort)/numberOfItems);
		System.out.println("Total WorkItem importance: " + totalImportance);
		System.out.println("Average WorkItem importance: " + ((double)totalImportance)/numberOfItems);
	}
}
