package ru.nazarenko.se.project.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.entities.DeliveryRequestDAO;
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
	public RequestServiceStatus getRequestStatusByTrack(String track) {
		return DeliveryRequestDAO.getProposalStatusBy(track);
	}

	@Override
	public RequestServiceStatus getRequestStatusById(long id) {
		return DeliveryRequestDAO.getProposalStatusBy(id);
	}

	@Override
	public List<DeliveryRequest> getRequestsByStatus(RequestServiceStatus requestServiceStatus) {
		return DeliveryRequestDAO.getProposalsByStatus(requestServiceStatus);
	}

	@Override
	public boolean updateRequestsStatusBy(long id, RequestServiceStatus status) {
		return DeliveryRequestDAO.updateProposalStatusBy(id, status);
	}

	@Override
	public boolean updateRequestsStatusBy(String track, RequestServiceStatus status) {
		return DeliveryRequestDAO.updateProposalStatusBy(track, status);
	}

	@Override
	public boolean writeTrackById(long id, String track_number) {
		return DeliveryRequestDAO.writeTrackNumberByRequestId(id, track_number);
	}

}