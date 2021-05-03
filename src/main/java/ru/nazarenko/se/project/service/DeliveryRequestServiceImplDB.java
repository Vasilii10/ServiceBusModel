package ru.nazarenko.se.project.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.model.*;

import java.util.List;

@Service
@Primary
public class DeliveryRequestServiceImplDB implements DeliveryRequestService {

	@Override
	public long createNewRequest(DeliveryRequest deliveryRequest) {
		return DeliveryRequestDAO.createNewRequest(deliveryRequest);
	}

	@Override
	public List<DeliveryRequest> readAllRequests() {
		return DeliveryRequestDAO.getAllRequests();
	}

	@Override
	public DeliveryRequest getRequestBy(long id) {
		return DeliveryRequestDAO.findRequestById(id);
	}

	@Override
	public boolean deleteRequest(long id) {
		return DeliveryRequestDAO.deleteRequestBy(id);
	}

	@Override
	public RequestServiceStatus getRequestStatusByTrack(String trackNumber) {
		return DeliveryRequestDAO.getRequestStatusBy(trackNumber);
	}

	@Override
	public RequestServiceStatus getRequestStatusById(long id) {
		return DeliveryRequestDAO.getRequestStatusBy(id);
	}

	@Override
	public List<DeliveryRequest> getRequestsByStatus(RequestServiceStatus serviceStatus) {
		return DeliveryRequestDAO.getByStatus(serviceStatus);
	}

	@Override
	public boolean updateRequestsStatusBy(long id, RequestServiceStatus serviceStatus) {
		return DeliveryRequestDAO.updateStatusBy(id, serviceStatus);
	}

	@Override
	public boolean updateRequestsStatusBy(String trackNumber, RequestServiceStatus serviceStatus) {
		return DeliveryRequestDAO.updateStatusBy(trackNumber, serviceStatus);
	}

	@Override
	public boolean writeTrackById(long id, String trackNumber) {
		return DeliveryRequestDAO.writeTrackNumberByRequestId(id, trackNumber);
	}

}