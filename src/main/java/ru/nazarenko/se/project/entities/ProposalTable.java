package ru.nazarenko.se.project.entities;

import org.hibernate.annotations.TypeDef;
import ru.nazarenko.se.project.PostgreSQLEnumType;
import ru.nazarenko.se.project.model.*;

import javax.persistence.*;

@Entity
@Table(name = "proposal")
//@TypeDef(name = "mood", typeClass = PostgreSQLEnumType.class)
public class ProposalTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "shipment_method")
	private String shipmentMethod;

	@Column(name = "destination")
	private String destination;

	@Column(name = "depature")
	private String depature;

	@Column(name = "service_proposal_status")
	private String service_proposal_status;

	@Column(name = "track_number")
	private String track_number;

	public ProposalTable(int id, String shipmentMethod, String destination, String depature, String service_proposal_status, String track_number) {
		this.id = id;
		this.shipmentMethod = shipmentMethod;
		this.destination = destination;
		this.depature = depature;
		this.service_proposal_status = service_proposal_status;
		this.track_number = track_number;
	}

	public ProposalTable() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepature() {
		return depature;
	}

	public void setDepature(String depature) {
		this.depature = depature;
	}


	public String getTrack_number() {
		return track_number;
	}

	public void setTrack_number(String track_number) {
		this.track_number = track_number;
	}


}
