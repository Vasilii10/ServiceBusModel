package ru.nazarenko.se.project.entities;

import org.hibernate.*;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.util.HibernateUtil;

import java.util.List;

public class ProposalDAO {

	public static Proposal findById(long id) {
		return HibernateUtil.getSessionFactory().openSession().get(Proposal.class, id);
	}

	public static ServiceProposalStatus getStatusBy(String track){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			ServiceProposalStatus status = (ServiceProposalStatus)
				session.createQuery("select serviceProposalStatus" +
					" from Proposal where trackNumber = :paramName")
					.setParameter("paramName", track)
					.uniqueResult();

			return status;
		} catch (Exception e) {
			System.err.println("ОЩибочка");

			return ServiceProposalStatus.CANCELED; // FIXME: 25/04/2021 исклжючение сделать!
		}

	}

//	public static ServiceProposalStatus findByTrack(String id) throws NoSuchFieldException {
//		return HibernateUtil.getSessionFactory().openSession().get(String.valueOf(Proposal.class.getField("service_proposal_status")), id);
//	}

	public static void createNew(Proposal proposal){
		try {
			writeInDB(proposal);
			System.err.println("Успешно");
		} catch (Exception e) {
			System.err.println("Ошибка!");
		}
	}

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

	public static boolean updateStatus(long id, ServiceProposalStatus serviceProposalStatus){
		// TODO: 26/04/2021 перенос на dao слой
		// TODO: 25/04/2021 предусмотреть исключение если статуса нет
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("update Proposal set serviceProposalStatus = :newStatus " +
				"where id = :id")
				.setParameter("newStatus", serviceProposalStatus)
				.setParameter("id", id)
				.executeUpdate();

			transaction.commit();

			return true;
		} catch (Exception e) {

			if (transaction != null) {
				transaction.rollback();
			}

			return false;
		}
	}

	// TODO: 26/04/2021 перенос на dao слой
	private static void writeInDB(Object object) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.save(object);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception();
		}

	}
	// TODO: 25/04/2021  
}
