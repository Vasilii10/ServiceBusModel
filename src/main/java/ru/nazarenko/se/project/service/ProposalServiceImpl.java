package ru.nazarenko.se.project.service;


import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.model.*;

import java.util.*;

@Service

public class ProposalServiceImpl implements ProposalService {

	private static final Map<Long, Proposal> PROPOSAL_REPOSITORY_MAP = new HashMap<>();

	//private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

	static {
		PROPOSAL_REPOSITORY_MAP.put(1L, new Proposal(
				1,
				ShipmentMethod.SHIP,
				"все дороги ведут в москву",
				"из краснодара",
				ServiceProposalStatus.IN_SEQUECE,
				"сгккуте"
			)
		);

		PROPOSAL_REPOSITORY_MAP.put(23L, new Proposal(
				1,
				ShipmentMethod.SHIP,
				"new guys",
				"из краснодара",
				ServiceProposalStatus.NEW_CREATED,
				"1L23"
			)
		);
	}


	@Override
	public void create(Proposal proposal) {

		//final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
		//proposal.setId(clientId);

		PROPOSAL_REPOSITORY_MAP.put(proposal.getProposalId(),
			proposal);
	}

	@Override
	public List<Proposal> readAll() {
		return new ArrayList<>(PROPOSAL_REPOSITORY_MAP.values());
	}

	@Override
	public Proposal readBy(long id) {
		return PROPOSAL_REPOSITORY_MAP.get(id);
	}

	@Override
	public boolean update(Proposal proposal, long id) {
		if (PROPOSAL_REPOSITORY_MAP.containsKey(id)) {
			proposal.setProposalId(id);
			PROPOSAL_REPOSITORY_MAP.put(id, proposal);
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

	@Override
	public ServiceProposalStatus readBy(String treck) {
		Map<Long, Proposal> map = new HashMap<>();

		for (Map.Entry<Long, Proposal> entry : PROPOSAL_REPOSITORY_MAP.entrySet()) {
			if (entry.getValue().getTrackNumber().equals(treck)) {
				return entry.getValue().getProposalStatus();
			}
		}

		return null;
	}

	@Override
	public List<Proposal> readNewProposals() { // для БД потом чисто where
		Map<Long, Proposal> map = new HashMap<>();

		for (Map.Entry<Long, Proposal> entry : PROPOSAL_REPOSITORY_MAP.entrySet()) {
			if (entry.getValue().getProposalStatus().equals(ServiceProposalStatus.NEW_CREATED)) {
				map.put(entry.getKey(), entry.getValue());
			}
		}

		return new ArrayList<>(map.values()); // FIXME: 24/04/2021
	}

	@Override
	public boolean updateStatusBy(long id, ServiceProposalStatus newStatus) {
		if (PROPOSAL_REPOSITORY_MAP.containsKey(id)) {

			Proposal proposal = PROPOSAL_REPOSITORY_MAP.get(id);

			proposal.setProposalStatus(newStatus);

			PROPOSAL_REPOSITORY_MAP.put(id, proposal);
			return true;
		}

		return false;
	}


}
