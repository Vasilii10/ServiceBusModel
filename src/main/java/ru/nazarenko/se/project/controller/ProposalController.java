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

	/**
	 * Создание заявки в сервисе
	 * + запись в БД
	 */
	@PostMapping(value = "/proposal/new")
	public ResponseEntity<?> create(@RequestBody Proposal proposal) {
		proposalService.create(proposal);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Получение всех заявок
	 */
	@GetMapping(value = "/proposals")
	public ResponseEntity<List<Proposal>> read() {
		final List<Proposal> proposals = proposalService.readAll();

		return proposals != null && !proposals.isEmpty()
			? new ResponseEntity<>(proposals, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявки по id
	 */
	@GetMapping(value = "/proposals/{id}")
	public ResponseEntity<Proposal> read(@PathVariable(name = "id") int id) {
		final Proposal proposal = proposalService.readBy(id);

		return proposal != null
			? new ResponseEntity<>(proposal, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статса заявки по id
	 */
	@GetMapping(value = "/proposal/status/{id}")
	public ResponseEntity<ServiceProposalStatus> getStatusBy(@PathVariable(name = "id") int id) {
		final ServiceProposalStatus proposalStatus = proposalService.readBy(id).getProposalStatus(); // вынести на
		// другой слой

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статса заявки по трек номеру
	 */
	@GetMapping(value = "/proposal/status/track/{treck}")
	public ResponseEntity<ServiceProposalStatus> getStatusBy(@PathVariable(name = "treck") String treck) {
		final ServiceProposalStatus proposalStatus = proposalService.readBy(treck); // вынести на
		// другой слой

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


// TODO: 24/04/2021 получение статуса для клиента по трек номеру


	/**
	 * обновлений всех полей заявки
	 */
	@PutMapping(value = "/proposal/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Proposal proposal) {
		final boolean updated = proposalService.update(proposal, id);

		return updated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}


	// ------взаимодействие с СУТ --------

	/**
	 * Передача новых заявок для СУТ
	 */
	@GetMapping(value = "/proposals/new")
	public ResponseEntity<List<Proposal>> readNew() {
		final List<Proposal> newProposals = proposalService.readNewProposals();

		return newProposals != null && !newProposals.isEmpty()
			? new ResponseEntity<>(newProposals, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * обновление статуса по айдишке
	 */
	@PutMapping(value = "/proposal/update/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ServiceProposalStatus newStatus) {
		final boolean isUpdated = proposalService.updateStatusBy(id, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}


	// как мониторить изменения статуса?
	// заправщивать трек номер?
}
