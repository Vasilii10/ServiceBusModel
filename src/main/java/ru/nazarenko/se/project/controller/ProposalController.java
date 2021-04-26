package ru.nazarenko.se.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.service.ProposalService;

import java.util.List;

@RestController
public class ProposalController {

	private final ProposalService proposalService;

	@Autowired
	public ProposalController(ProposalService proposalService) {
		this.proposalService = proposalService;
	}

	// TODO: 27/04/2021 по айди записывать трек-номер (нужно для СУТ)

	/**
	 * Создание заявки в сервисе
	 * + запись в БД
	 */
	@PostMapping(value = "/proposal/new")
	public ResponseEntity<?> createNewProposal(@RequestBody Proposal proposal) {
		boolean result = proposalService.createNewProposal(proposal);

		return result
			? new ResponseEntity<>(HttpStatus.CREATED)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * Получение всех заявок
	 * <p>
	 */
	@GetMapping(value = "/proposals")
	public ResponseEntity<List<Proposal>> getAllProposals() {
		final List<Proposal> proposals = proposalService.readAll();

		return proposals != null && !proposals.isEmpty()
			? new ResponseEntity<>(proposals, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявки по id
	 * <p>
	 * работает
	 */
	@GetMapping(value = "/proposals/{id}")
	public ResponseEntity<Proposal> getProposalById(@PathVariable(name = "id") long id) {
		final Proposal proposal = proposalService.getProposalBy(id);

		return proposal != null
			? new ResponseEntity<>(proposal, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	/**
	 * Получение статуса заявки по id
	 * <p>
	 * работает
	 */
	@GetMapping(value = "/proposal/status/{id}")
	public ResponseEntity<ServiceProposalStatus> getProposalStatusBy(@PathVariable(name = "id") int id) {
		final ServiceProposalStatus proposalStatus = proposalService.getProposalStatusById(id);

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статуса заявки по трек номеру
	 * <p>
	 * работает
	 */
	@GetMapping(value = "/proposal/status/track/{track_number}")
	public ResponseEntity<ServiceProposalStatus> getProposalStatusBy(
		@PathVariable(name = "track_number") String trackNumber) {

		final ServiceProposalStatus proposalStatus = proposalService.getProposalStatusByTrack(trackNumber);

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявок с определенным статусом
	 * <p>
	 * рабоатет
	 */
	@PostMapping(value = "/proposalsByStatus")
	public ResponseEntity<List<Proposal>> getProposalWith(@RequestBody ServiceProposalStatus status) {
		final List<Proposal> newProposals = proposalService.getProposalsByStatus(
			status);

		return newProposals != null && !newProposals.isEmpty()
			? new ResponseEntity<>(newProposals, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	// ------взаимодействие с СУТ --------

	/**
	 * Передача новых заявок для СУТ
	 * <p>
	 * рабоатет
	 */
	@GetMapping(value = "/proposals/new")
	public ResponseEntity<List<Proposal>> getNewProposals() {
		final List<Proposal> newProposals = proposalService.getProposalsByStatus(
			ServiceProposalStatus.NEW_CREATED);

		return newProposals != null && !newProposals.isEmpty()
			? new ResponseEntity<>(newProposals, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * обновление статуса по айдишке
	 * <p>
	 * работает
	 */
	@PutMapping(value = "/proposal/status/update/{id}")
	public ResponseEntity<?> updateProposalStatusBy(@PathVariable(name = "id") long id,
													@RequestBody ServiceProposalStatus newStatus) {
		final boolean isUpdated = proposalService.updateProposalStatusBy(id, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * обновление статуса по трек номеру
	 * <p>
	 */
	@PutMapping(value = "/proposal/status/updatebytrack/{track_number}")
	public ResponseEntity<?> updateProposalStatusBy(@PathVariable(name = "track_number") String track,
													@RequestBody ServiceProposalStatus newStatus) {
		final boolean isUpdated = proposalService.updateProposalStatusBy(track, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

}
