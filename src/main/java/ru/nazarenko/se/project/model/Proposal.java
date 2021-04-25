package ru.nazarenko.se.project.model;


import javax.persistence.*;

@Entity
@Table(name = "proposal")
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long proposalId;

	@Column(name = "shipment_method")
	@Enumerated(EnumType.STRING)
	private ShipmentMethod shipmentMethod;

	@Column(name = "destination")
	private String destinationPlace;

	@Column(name = "depature")
	private String depaturePlace;

	@Column(name = "service_proposal_status")
	@Enumerated(EnumType.STRING)
	private ServiceProposalStatus serviceProposalStatus;

	@Column(name = "track_number")
	private String trackNumber;

	public Proposal(int proposalId,
					ShipmentMethod shipmentMethod,
					String destination,
					String depature,
					ServiceProposalStatus service_proposal_status,
					String track_number) {
		this.proposalId = proposalId;
		this.shipmentMethod = shipmentMethod;
		this.destinationPlace = destination;
		this.depaturePlace = depature;
		this.serviceProposalStatus = service_proposal_status;
		this.trackNumber = track_number;
	}

	public Proposal() {
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

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trecNumber) {
		this.trackNumber = trecNumber;
	}
}
