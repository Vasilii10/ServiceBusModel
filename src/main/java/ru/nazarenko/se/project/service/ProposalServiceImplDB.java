package ru.nazarenko.se.project.service;

import org.hibernate.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nazarenko.se.project.entities.ProposalTable;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.util.HibernateUtil;

import java.util.List;

@Service
@Primary
public class ProposalServiceImplDB implements ProposalService {
	@Override
	public void create(Proposal proposal) {

		// FIXME: 25/04/2021 тестовый случай!
		ProposalTable student = new ProposalTable(
			1,
			ShipmentMethod.SHIP.toString(),
			"все дороги ведут в москву",
			"из краснодара",
			ServiceProposalStatus.IN_SEQUECE.toString(),
			"h"
		);

		try {

			writeProposalInDB(student);
			System.err.println("Успешно");
		} catch (Exception e) {
			System.err.println("Ошибка!");
		}
	}

	@Override
	public List<Proposal> readAll() {
		return null;
	}

	@Override
	public Proposal readBy(long id) {
		return null;
	}

	@Override
	public boolean update(Proposal proposal, long id) {
		return false;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

	@Override
	public ServiceProposalStatus readBy(String treck) {
		return null;
	}

	@Override
	public List<Proposal> readNewProposals() {
		return null;
	}

	@Override
	public boolean updateStatusBy(long id, ServiceProposalStatus status) {
		return false;
	}

	public static void writeProposalInDB(ProposalTable testTable) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.save(testTable);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception();
		}

	}
}
