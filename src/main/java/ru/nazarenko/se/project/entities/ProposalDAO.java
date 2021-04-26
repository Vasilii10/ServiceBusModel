package ru.nazarenko.se.project.entities;

import org.hibernate.*;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.util.HibernateUtil;

import java.util.List;

public class ProposalDAO {

	public static Proposal findProposalById(long id) {
		return HibernateUtil.getSessionFactory().openSession().get(Proposal.class, id);
	}

	public static ServiceProposalStatus getProposalStatusBy(String track) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			ServiceProposalStatus status = (ServiceProposalStatus)
				session.createQuery("select serviceProposalStatus" +
					" from Proposal where trackNumber = :track")
					.setParameter("track", track)
					.uniqueResult();

			return status;
		} catch (Exception e) {
			System.err.println("ОЩибочка");

			return ServiceProposalStatus.CANCELED; // FIXME: 25/04/2021 исклжючение сделать!
		}

	}

	public static ServiceProposalStatus getProposalStatusBy(long proposalId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			ServiceProposalStatus status = (ServiceProposalStatus)
				session.createQuery("select serviceProposalStatus" +
					" from Proposal where proposalId = :proposalId")
					.setParameter("proposalId", proposalId)
					.uniqueResult();

			return status;
		} catch (Exception e) {

			return ServiceProposalStatus.CANCELED; // FIXME: 25/04/2021 исклжючение сделать!
		}
	}

	public static boolean createNewProposal(Proposal proposal) {


		// TODO: 27/04/2021 проверки на заполенность полей? 
		
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

	public static List<Proposal> getAllProposals() {
		return
			(List<Proposal>) HibernateUtil.getSessionFactory().
				openSession()
				.createQuery("From Proposal")
				.list();
	}

	public static List<Proposal> getProposalsByStatus(ServiceProposalStatus status) {
		return
			(List<Proposal>) HibernateUtil.getSessionFactory()
				.openSession()
				.createQuery("From Proposal where serviceProposalStatus = :status")
				.setParameter("status", status)
				.list();
	}

	public static boolean updateProposalStatusBy(String trackNumber, ServiceProposalStatus serviceProposalStatus) {
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

	public static boolean updateProposalStatusBy(long id, ServiceProposalStatus newProposalStatus) {
		// TODO: 25/04/2021 предусмотреть исключение если статуса нет

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("update Proposal set serviceProposalStatus = :newStatus " +
				"where id = :id")
				.setParameter("newStatus", newProposalStatus)
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

	public static boolean deleteProposalBy(long id) {
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
