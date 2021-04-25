package ru.nazarenko.se.project.entities;

import ru.nazarenko.se.project.model.Proposal;
import ru.nazarenko.se.project.util.HibernateUtil;

public class ProposalDAO {

	public static Proposal findById(long id) {
		return HibernateUtil.getSessionFactory().openSession().get(Proposal.class, id);
	}


	// TODO: 25/04/2021  
}
