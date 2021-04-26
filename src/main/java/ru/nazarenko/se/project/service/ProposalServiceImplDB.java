package ru.nazarenko.se.project.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.model.*;

import java.util.List;

import static ru.nazarenko.se.project.entities.ProposalDAO.*;

@Service
@Primary
public class ProposalServiceImplDB implements ProposalService {
	@Override
	public void create(Proposal proposal) {
		createNew(proposal);
	}

	@Override
	public List<Proposal> readAll() {
		return findAll();
	}

	@Override
	public Proposal readStatusBy(long id) {
		return findById(id);
	}

	@Override
	public boolean update(Proposal proposal, long id) {
		return false;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

	@Override
	public ServiceProposalStatus readStatusBy(String track) {

		return getStatusBy(track);
	}

	@Override
	public List<Proposal> readNewProposals() {
		return findNew();
	}

	@Override
	public boolean updateStatusBy(long id, ServiceProposalStatus status) {

		return updateStatus(id, status);
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