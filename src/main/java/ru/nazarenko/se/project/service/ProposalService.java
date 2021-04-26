package ru.nazarenko.se.project.service;


import ru.nazarenko.se.project.model.*;

import java.util.List;

public interface ProposalService {

	/**
	 * Создает нового клиента
	 *
	 * @param proposal - клиент для создания
	 * @return
	 */
	boolean create(Proposal proposal);

	/**
	 * Возвращает список всех имеющихся клиентов
	 *
	 * @return список клиентов
	 */
	List<Proposal> readAll();

	/**
	 * Возвращает клиента по его ID
	 *
	 * @param id - ID клиента
	 * @return - объект клиента с заданным ID
	 */
	Proposal getStatusBy(long id);

	/**
	 * Обновляет клиента с заданным ID,
	 * в соответствии с переданным клиентом
	 *
	 * @param proposal - клиент в соответсвии с которым нужно обновить данные
	 * @param id       - id клиента которого нужно обновить
	 * @return - true если данные были обновлены, иначе false
	 */
	//boolean update(Proposal proposal, long id);

	/**
	 * Удаляет клиента с заданным ID
	 *
	 * @param id - id клиента, которого нужно удалить
	 * @return - true если клиент был удален, иначе false
	 */
	boolean delete(long id);

	ServiceProposalStatus getStatusBy(String treck);



	/**
	 * ищем задания по статусу
	 * @param serviceProposalStatus
	 * @return
	 */
	List<Proposal> readProposalsByStatusIs(ServiceProposalStatus serviceProposalStatus);


	// по айдишке обновляем
	boolean updateStatusBy(long id, ServiceProposalStatus status);

	// по трек номеру
	boolean updateStatusBy(String track, ServiceProposalStatus status);

}
