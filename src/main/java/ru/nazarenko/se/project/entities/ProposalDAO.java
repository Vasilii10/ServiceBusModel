package ru.nazarenko.se.project.entities;

import org.hibernate.*;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.util.HibernateUtil;

import java.util.List;

public class ProposalDAO {

	public static final ServiceProposalStatus STATUS_FOR_NEW_PROPOSALS = ServiceProposalStatus.NEW_CREATED;

	public static Proposal findById(long id) {
		return HibernateUtil.getSessionFactory().openSession().get(Proposal.class, id);
	}

	public static ServiceProposalStatus getStatusBy(String track) {
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

	public static boolean create_New(Proposal proposal) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.save(proposal);
			transaction.commit();

			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		}
	}

	public static List<Proposal> findAllProposals() {
		return
			(List<Proposal>) HibernateUtil.getSessionFactory().
				openSession()
				.createQuery("From Proposal")
				.list();


	}

	public static List<Proposal> findProposalsByStatus(ServiceProposalStatus status) {
		return
			(List<Proposal>) HibernateUtil.getSessionFactory().
				openSession()
				.createQuery("From Proposal where serviceProposalStatus = :createdStatus")
				.setParameter("createdStatus", status)
				.list();


	}

	public static boolean updateStatus(String trackNumber, ServiceProposalStatus serviceProposalStatus) {
		// TODO: 25/04/2021 предусмотреть исключение если статуса нет
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("update Proposal set serviceProposalStatus = :newStatus " +
				"where trackNumber = :trackNumber")
				.setParameter("newStatus", serviceProposalStatus)
				.setParameter("trackNumber", trackNumber)
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

	public static boolean updateStatus(long id, ServiceProposalStatus serviceProposalStatus) {
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

	public static boolean deleteProposalBY(long id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("delete Proposal" +
				"where id = :id")
				.setParameter("id", id).executeUpdate();

			transaction.commit();

			return true;
		} catch (Exception e) {

			if (transaction != null) {
				transaction.rollback();
			}

			return false;
		}
	}
}
