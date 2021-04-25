package ru.nazarenko.se.project.entities;

import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.util.HibernateUtil;

import java.util.List;

public class ProposalDAO {

	public static Proposal findById(long id) {
		return HibernateUtil.getSessionFactory().openSession().get(Proposal.class, id);
	}

//	public static ServiceProposalStatus findByTrack(String id) throws NoSuchFieldException {
//		return HibernateUtil.getSessionFactory().openSession().get(String.valueOf(Proposal.class.getField("service_proposal_status")), id);
//	}


	public static List<Proposal> findAll() {
		return
			(List<Proposal>) HibernateUtil.getSessionFactory().
				openSession()
				.createQuery("From Proposal")
				.list();


	}

	public static List<Proposal> findNew() {
		return
			(List<Proposal>) HibernateUtil.getSessionFactory().
				openSession()
				.createQuery("From Proposal where serviceProposalStatus = :createdStatus")
				.setParameter("createdStatus", ServiceProposalStatus.NEW_CREATED)
				.list();


	}

	// TODO: 25/04/2021  
}
