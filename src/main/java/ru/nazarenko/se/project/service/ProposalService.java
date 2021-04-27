package ru.nazarenko.se.project.service;


import ru.nazarenko.se.project.model.*;

import java.util.List;

public interface ProposalService {

	long createNewProposal(Proposal proposal);

	List<Proposal> readAll();

	Proposal getProposalBy(long id);

	boolean deleteProposal(long id);

	ServiceProposalStatus getProposalStatusByTrack(String track);

	ServiceProposalStatus getProposalStatusById(long id);

	List<Proposal> getProposalsByStatus(ServiceProposalStatus serviceProposalStatus);

	boolean updateProposalStatusBy(long proposalId, ServiceProposalStatus status);

	boolean updateProposalStatusBy(String track, ServiceProposalStatus status);

	boolean writeTrackById(long id, String track_number);
}
