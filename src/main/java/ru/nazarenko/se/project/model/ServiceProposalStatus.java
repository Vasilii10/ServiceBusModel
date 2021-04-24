package ru.nazarenko.se.project.model;

public enum ServiceProposalStatus {
	IN_SEQUECE("IN_SEQUECE"),
	IN_SHIPMENT("IN_SHIPMENT"),
	DELIVERED("DELIVERED"),
	CANCELED("CANCELED"),
	NEW_CREATED("NEW_CREATED");

	private String string;

	ServiceProposalStatus(String new_created) {
		this.string = new_created;
	}

	@Override
	public String toString() {
		return string;
	}
}
