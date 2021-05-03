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
		return DeliveryRequestDAO.createNewProposal(deliveryRequest);
	}

	@Override
	public List<DeliveryRequest> readAllRequests() {
		return DeliveryRequestDAO.getAllProposals();
	}

	@Override
	public DeliveryRequest getRequestBy(long id) {
		return DeliveryRequestDAO.findProposalById(id);
	}

	@Override
	public boolean deleteRequest(long id) {
		return DeliveryRequestDAO.deleteRequestBy(id);
	}

	@Override
	public RequestServiceStatus getRequestStatusByTrack(String trackNumber) {
		return DeliveryRequestDAO.getProposalStatusBy(trackNumber);
	}

	@Override
	public RequestServiceStatus getRequestStatusById(long id) {
		return DeliveryRequestDAO.getProposalStatusBy(id);
	}

	@Override
	public List<DeliveryRequest> getRequestsByStatus(RequestServiceStatus serviceStatus) {
		return DeliveryRequestDAO.getProposalsByStatus(serviceStatus);
	}

	@Override
	public boolean updateRequestsStatusBy(long id, RequestServiceStatus serviceStatus) {
		return DeliveryRequestDAO.updateProposalStatusBy(id, serviceStatus);
	}

	@Override
	public boolean updateRequestsStatusBy(String trackNumber, RequestServiceStatus serviceStatus) {
		return DeliveryRequestDAO.updateProposalStatusBy(trackNumber, serviceStatus);
	}

	@Override
	public boolean writeTrackById(long id, String trackNumber) {
		return DeliveryRequestDAO.writeTrackNumberByRequestId(id, trackNumber);
	}

}