package ru.nazarenko.se.project.model;

public class ProposalDescriptor {
	private Dimensions dimensions;
	private double weight;

	public ProposalDescriptor(Dimensions dimensions, double weight) {
		this.dimensions = dimensions;
		this.weight = weight;
	}
}
