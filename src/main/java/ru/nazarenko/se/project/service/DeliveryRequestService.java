package ru.nazarenko.se.project.service;


import ru.nazarenko.se.project.model.*;

import java.util.List;

public interface DeliveryRequestService {

	long createNewRequest(DeliveryRequest deliveryRequest);

	List<DeliveryRequest> readAllRequests();

	DeliveryRequest getRequestBy(long id);

	boolean deleteRequest(long id);

	RequestServiceStatus getRequestStatusByTrack(String track);

	RequestServiceStatus getRequestStatusById(long id);

	List<DeliveryRequest> getRequestsByStatus(RequestServiceStatus requestServiceStatus);

	boolean updateRequestsStatusBy(long proposalId, RequestServiceStatus status);

	boolean updateRequestsStatusBy(String track, RequestServiceStatus status);

	boolean writeTrackById(long id, String track_number);
}
