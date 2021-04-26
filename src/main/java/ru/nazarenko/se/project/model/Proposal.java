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

	public Proposal() {
	}

	public long getProposalId() {
		return proposalId;
	}

	public ShipmentMethod getShipmentMethod() {
		return shipmentMethod;
	}

	public String getDestinationPlace() {
		return destinationPlace;
	}

	public String getDepaturePlace() {
		return depaturePlace;
	}

	public ServiceProposalStatus getServiceProposalStatus() {
		return serviceProposalStatus;
	}

	public String getTrackNumber() {
		return trackNumber;
	}
}
