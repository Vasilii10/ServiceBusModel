package ru.nazarenko.se.project.service;


import ru.nazarenko.se.project.model.*;

import java.util.List;

public interface DeliveryRequestService {

	long createNewRequest(DeliveryRequest deliveryRequest) throws RequestNotCreatedException;

	List<DeliveryRequest> readAllRequests();

	DeliveryRequest getRequestBy(long id);

	RequestServiceStatus getRequestStatusByTrack(String trackNumber) throws RequestNotFoundException;

	RequestServiceStatus getRequestStatusById(long id) throws RequestNotFoundException;

	List<DeliveryRequest> getRequestsByStatus(RequestServiceStatus requestServiceStatus);

	boolean updateRequestsStatusBy(long id, RequestServiceStatus serviceStatus);

	boolean updateRequestsStatusBy(String trackNumber, RequestServiceStatus serviceStatus);

	void writeTrackById(long id, String trackNumber) throws TrackNumberIsExsistedException;

	boolean deleteRequest(long id);
}
