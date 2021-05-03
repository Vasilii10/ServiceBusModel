package ru.nazarenko.se.project.service;


import ru.nazarenko.se.project.model.*;

import java.util.List;

public interface DeliveryRequestService {

	long createNewRequest(DeliveryRequest deliveryRequest);

	List<DeliveryRequest> readAllRequests();

	DeliveryRequest getRequestBy(long id);

	RequestServiceStatus getRequestStatusByTrack(String trackNumber);

	RequestServiceStatus getRequestStatusById(long id);

	List<DeliveryRequest> getRequestsByStatus(RequestServiceStatus requestServiceStatus);

	boolean updateRequestsStatusBy(long id, RequestServiceStatus serviceStatus);

	boolean updateRequestsStatusBy(String trackNumber, RequestServiceStatus serviceStatus);

	boolean writeTrackById(long id, String trackNumber);

	boolean deleteRequest(long id);
}
