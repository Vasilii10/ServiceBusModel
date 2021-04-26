package ru.nazarenko.se.project.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.entities.ProposalDAO;
import ru.nazarenko.se.project.model.*;

import java.util.List;

import static ru.nazarenko.se.project.entities.ProposalDAO.*;

@Service
@Primary
public class ProposalServiceImplDB implements ProposalService {
	@Override
	public boolean create(Proposal proposal) {
		return ProposalDAO.create_New(proposal);
	}

	@Override
	public List<Proposal> readAll() {
		return ProposalDAO.findAllProposals();
	}


	// TODO: 26/04/2021 read all цшер status

	@Override
	public Proposal getStatusBy(long id) {
		return findById(id);
	}

	@Override
	public boolean delete(long id) {
		return ProposalDAO.deleteProposalBY(id);
	}

	@Override
	public ServiceProposalStatus getStatusBy(String track) {
		return ProposalDAO.getStatusBy(track);
	}

	@Override
	public List<Proposal> readNewProposals() {
		return findNew();
	}

	@Override
	public boolean updateStatusBy(long id, ServiceProposalStatus status) {
		return updateStatus(id, status);
	}

	@Override
	public boolean updateStatusBy(String track, ServiceProposalStatus status) {
		return ProposalDAO.updateStatus(track, status);
	}

}
/**
 * {
 * "proposalId": 22222,
 * "shipmentMethod": "SHIP",
 * "destinationPlace": "j",
 * "depaturePlace": "из",
 * "trackNumber": "wewew",
 * "proposalStatus": "IN_SEQUECE"
 * }
 */