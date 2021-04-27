package ru.nazarenko.se.project.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.entities.ProposalDAO;
import ru.nazarenko.se.project.model.*;

import java.util.List;

@Service
@Primary
public class ProposalServiceImplDB implements ProposalService {

	@Override
	public long createNewProposal(Proposal proposal) {
		return ProposalDAO.createNewProposal(proposal);
	}

	@Override
	public List<Proposal> readAll() {
		return ProposalDAO.getAllProposals();
	}

	@Override
	public Proposal getProposalBy(long id) {
		return ProposalDAO.findProposalById(id);
	}

	@Override
	public boolean deleteProposal(long id) {
		return ProposalDAO.deleteProposalBy(id);
	}

	@Override
	public ServiceProposalStatus getProposalStatusByTrack(String track) {
		return ProposalDAO.getProposalStatusBy(track);
	}

	@Override
	public ServiceProposalStatus getProposalStatusById(long id) {
		return ProposalDAO.getProposalStatusBy(id);
	}

	@Override
	public List<Proposal> getProposalsByStatus(ServiceProposalStatus serviceProposalStatus) {
		return ProposalDAO.getProposalsByStatus(serviceProposalStatus);
	}

	@Override
	public boolean updateProposalStatusBy(long id, ServiceProposalStatus status) {
		return ProposalDAO.updateProposalStatusBy(id, status);
	}

	@Override
	public boolean updateProposalStatusBy(String track, ServiceProposalStatus status) {
		return ProposalDAO.updateProposalStatusBy(track, status);
	}

	@Override
	public boolean writeTrackById(long id, String track_number) {
		return ProposalDAO.writeTreckNumberByProposalId(id, track_number);
	}

}