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

	public static RequestServiceStatus getRequestStatusBy(String trackNumber) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			RequestServiceStatus serviceStatus = (RequestServiceStatus)
				session
					.createQuery("select requestServiceStatus" +
						" from DeliveryRequest where deliveryTrackNumber = :deliveryTrackNumber")
					.setParameter("deliveryTrackNumber", trackNumber)
					.uniqueResult();

			return serviceStatus;
		} catch (Exception e) {

			return RequestServiceStatus.CANCELED; // FIXME: 25/04/2021 исклжючение сделать!zx
		}

	}

	public static RequestServiceStatus getRequestStatusBy(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			RequestServiceStatus serviceStatus = (RequestServiceStatus)
				session
					.createQuery("select requestServiceStatus" +
						" from DeliveryRequest where deliveryRequestId = :proposalId")
					.setParameter("proposalId", id)
					.uniqueResult();

			return serviceStatus;
		} catch (Exception e) {

			return RequestServiceStatus.CANCELED; // FIXME: 25/04/2021 исклжючение сделать!
		}
	}

	public static long createNewRequest(DeliveryRequest deliveryRequest) {


		// TODO: 27/04/2021 проверки на заполенность полей? 

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

			// а лучше иксепшн
			return 0;
		}
	}

	public static List<DeliveryRequest> getAllRequests() {
		return
			(List<DeliveryRequest>) HibernateUtil.getSessionFactory()
				.openSession()
				.createQuery("From DeliveryRequest")
				.list();
	}

	public static List<DeliveryRequest> getByStatus(RequestServiceStatus serviceStatus) {
		return
			(List<DeliveryRequest>) HibernateUtil.getSessionFactory()
				.openSession()
				.createQuery("From DeliveryRequest where requestServiceStatus = :status")
				.setParameter("status", serviceStatus)
				.list();
	}

	public static boolean updateStatusBy(String trackNumber, RequestServiceStatus requestServiceStatus) {
		// TODO: 25/04/2021 предусмотреть исключение если статуса нет

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("update DeliveryRequest set requestServiceStatus = :newStatus " +
				"where deliveryTrackNumber = :trackNumber")
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

			session.createQuery("delete DeliveryRequest where deliveryRequestId = :id")
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

	public static boolean writeTrackNumberByRequestId(long id, String trackNumber) {

		// FIXME: 28/04/2021 проверить пусто ли, если нет - ошибка

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.createQuery("update DeliveryRequest set deliveryTrackNumber" +
				" = :track_number where deliveryRequestId = :id")
				.setParameter("track_number", trackNumber)
				.setParameter("id", id)
				.executeUpdate();

			transaction.commit();

			return true;
		} catch (Exception e) {

//			if (transaction != null) {
//				transaction.rollback();
//			}

			return false;
		}
	}
}
