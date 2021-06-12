package ru.nazarenko.se.project.model;

import org.hibernate.*;
import ru.nazarenko.se.project.util.HibernateUtil;

import java.util.List;

public class DeliveryRequestDAO {

	public static DeliveryRequest findRequestById(long deliveryRequestId) {
		return HibernateUtil.getSessionFactory()
			.openSession()
			.get(DeliveryRequest.class, deliveryRequestId);
	}

	public static RequestServiceStatus getRequestStatusBy(String trackNumber) throws RequestNotFoundException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String query = "select requestServiceStatus" +
				" from DeliveryRequest where deliveryTrackNumber = :deliveryTrackNumber";

			RequestServiceStatus serviceStatus = (RequestServiceStatus)
				session.createQuery(query)
					.setParameter("deliveryTrackNumber", trackNumber)
					.uniqueResult();

			if (serviceStatus == null) {
				throw new RequestNotFoundException();
			}

			return serviceStatus;
		}
	}

	public static RequestServiceStatus getRequestStatusBy(long id) throws RequestNotFoundException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String query = "select requestServiceStatus" +
				" from DeliveryRequest where deliveryRequestId = :proposalId";

			RequestServiceStatus serviceStatus = (RequestServiceStatus)
				session.createQuery(query)
					.setParameter("proposalId", id)
					.uniqueResult();

			if (serviceStatus == null) {
				throw new RequestNotFoundException();
			}

			return serviceStatus;
		}
	}

	public static long createNewRequest(DeliveryRequest deliveryRequest)
		throws RequestNotCreatedException {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.save(deliveryRequest);
			transaction.commit();

			return deliveryRequest.getDeliveryRequestId();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			throw new RequestNotCreatedException();
		}
	}

	public static List<DeliveryRequest> getAllRequests() {
		String getAllQuery = "From DeliveryRequest";

		return (List<DeliveryRequest>) HibernateUtil.getSessionFactory()
			.openSession()
			.createQuery(getAllQuery)
			.list();
	}

	public static List<DeliveryRequest> getByStatus(RequestServiceStatus serviceStatus) {
		String getStatusQuery = "From DeliveryRequest where requestServiceStatus = :status";

		return (List<DeliveryRequest>) HibernateUtil.getSessionFactory()
			.openSession()
			.createQuery(getStatusQuery)
			.setParameter("status", serviceStatus)
			.list();
	}

	public static boolean updateStatusBy(String trackNumber, RequestServiceStatus requestServiceStatus)
		throws TrackNumberNotFoundException {

		if (trackNumberFoundInDb(trackNumber)) {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();

				String updateQuery = "update DeliveryRequest set requestServiceStatus = :newStatus " +
					"where deliveryTrackNumber = :trackNumber";

				session.createQuery(updateQuery)
					.setParameter("newStatus", requestServiceStatus)
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
		} else {
			throw new TrackNumberNotFoundException();
		}
	}

	private static boolean trackNumberFoundInDb(String trackNumber) {
		long counter;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String countQuery = "select count(delivery.deliveryTrackNumber) " +
				"from DeliveryRequest delivery where " +
				"deliveryTrackNumber =: trackNumber";

			counter = (Long) session
				.createQuery(countQuery)
				.setParameter("trackNumber", trackNumber)
				.uniqueResult();
		}

		return counter == 1;
	}

	public static boolean updateStatusBy(long id, RequestServiceStatus newProposalStatus) {
		// TODO: 25/04/2021 предусмотреть исключение если статуса нет

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("update DeliveryRequest set requestServiceStatus = :newStatus " +
				"where deliveryRequestId = :id")
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

	public static boolean deleteRequestBy(long id) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			String deleteQuery = "delete DeliveryRequest where deliveryRequestId = :id";
			session.createQuery(deleteQuery)
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

	public static boolean writeTrackNumberByRequestId(long id, String trackNumber) throws TrackNumberIsNotExistsException {

		if (!trackNumberFoundInDb(trackNumber)) {
			Transaction transaction;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();

				String query = "update DeliveryRequest set deliveryTrackNumber" +
					" = :track_number where deliveryRequestId = :id";

				session.createQuery(query)
					.setParameter("track_number", trackNumber)
					.setParameter("id", id)
					.executeUpdate();

				transaction.commit();

				return true;
			} catch (Exception e) {

				return false;
			}
		} else {
			throw new TrackNumberIsNotExistsException();
		}
	}
}
