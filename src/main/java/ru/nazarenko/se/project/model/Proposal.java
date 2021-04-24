package ru.nazarenko.se.project.model;

// FIXME: 24/04/2021 Builder is needed
public class Proposal {

	private long proposalId;
	private ShipmentMethod shipmentMethod;
	private String destinationPlace;
	private String depaturePlace;

	private ServiceProposalStatus serviceProposalStatus;
	private String trecNumber; // ткущее местоположение
	//private ProposalDescriptor proposalDescriptor;


	public Proposal(long proposalId,
					ShipmentMethod shipmentMethod,
					String destinationPlace,
					String depaturePlace,
					ServiceProposalStatus serviceProposalStatus,
					String trecNumber
					) {
		this.proposalId = proposalId;
		this.shipmentMethod = shipmentMethod;
		this.destinationPlace = destinationPlace;
		this.depaturePlace = depaturePlace;
		this.serviceProposalStatus = serviceProposalStatus;
		this.trecNumber = trecNumber;


	}

	public long getProposalId() {
		return proposalId;
	}

	public void setProposalId(long proposalId) {
		this.proposalId = proposalId;
	}

	public ShipmentMethod getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(ShipmentMethod shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}

	public String getDestinationPlace() {
		return destinationPlace;
	}

	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}

	public String getDepaturePlace() {
		return depaturePlace;
	}

	public void setDepaturePlace(String depaturePlace) {
		this.depaturePlace = depaturePlace;
	}

	public ServiceProposalStatus getProposalStatus() {
		return serviceProposalStatus;
	}

	public void setProposalStatus(ServiceProposalStatus serviceProposalStatus) {
		this.serviceProposalStatus = serviceProposalStatus;
	}

	public String getTrecNumber() {
		return trecNumber;
	}

	public void setTrecNumber(String trecNumber) {
		this.trecNumber = trecNumber;
	}


}
