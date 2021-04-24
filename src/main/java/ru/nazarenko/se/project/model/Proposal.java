package ru.nazarenko.se.project.model;

import org.hibernate.annotations.TypeDef;
import ru.nazarenko.se.project.PostgreSQLEnumType;

import javax.persistence.*;

@Entity
@Table(name = "proposal")
@TypeDef(name = "shipment_method", typeClass = PostgreSQLEnumType.class)
@TypeDef(name = "service_proposal_status", typeClass = PostgreSQLEnumType.class)
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long proposalId;

	@Column(name = "shipment_method")
	private ShipmentMethod shipmentMethod;

	@Column(name = "destination")
	private String destinationPlace;

	@Column(name = "depature")
	private String depaturePlace;

	@Column(name = "service_proposal_status")
	private ServiceProposalStatus serviceProposalStatus;

	@Column(name = "track_number")
	private String trecNumber;

	public Proposal(long proposalId, ShipmentMethod shipmentMethod,
					String destination, String depature, ServiceProposalStatus service_proposal_status,
					String track_number) {
		this.proposalId = proposalId;
		this.shipmentMethod = shipmentMethod;
		this.destinationPlace = destination;
		this.depaturePlace = depature;
		this.serviceProposalStatus = service_proposal_status;
		this.trecNumber = track_number;
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

	public String getTrecNumber() {
		return trecNumber;
	}

	public void setTrecNumber(String trecNumber) {
		this.trecNumber = trecNumber;
	}
}
