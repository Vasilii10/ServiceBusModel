package ru.nazarenko.se.project.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "deliveryrequest")
public class DeliveryRequest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_request_id")
	private Long deliveryRequestId;

	@Column(name = "request_service_status")
	@Enumerated(EnumType.STRING)
	private RequestServiceStatus requestServiceStatus;

	@Column(name = "shipment_method")
	@Enumerated(EnumType.STRING)
	private ShipmentMethod shipmentMethod;

	@Column(name = "destination_point")
	private String destinationPoint;

	@Column(name = "departure_point")
	private String departurePoint;

	@Column(name = "delivery_track_number")
	private String deliveryTrackNumber;

	public DeliveryRequest() {
	}

	public long getDeliveryRequestId() {
		return deliveryRequestId;
	}

	public RequestServiceStatus getRequestServiceStatus() {
		return requestServiceStatus;
	}

	public ShipmentMethod getShipmentMethod() {
		return shipmentMethod;
	}

	public String getDestinationPoint() {
		return destinationPoint;
	}

	public String getDeparturePoint() {
		return departurePoint;
	}

	public String getDeliveryTrackNumber() {
		return deliveryTrackNumber;
	}
}
