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
		boolean result = proposalService.create(proposal);

		return result
			? new ResponseEntity<>(HttpStatus.CREATED)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

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
	public ResponseEntity<Proposal> read(@PathVariable(name = "id") long id) {
		final Proposal proposal = proposalService.getStatusBy(id);

		return proposal != null
			? new ResponseEntity<>(proposal, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	/**
	 * Получение статeса заявки по id
	 */
	@GetMapping(value = "/proposal/status/{id}")
	public ResponseEntity<ServiceProposalStatus> getStatusBy(@PathVariable(name = "id") int id) {
		final ServiceProposalStatus proposalStatus = proposalService.getStatusBy(id).getProposalStatus(); // вынести на
		// другой слой

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статса заявки по трек номеру
	 * <p>
	 * сделали
	 */
	@GetMapping(value = "/proposal/status/track/{track_number}")
	public ResponseEntity<ServiceProposalStatus> getStatusBy(@PathVariable(name = "track_number") String trackNumber) {
		final ServiceProposalStatus proposalStatus = proposalService.getStatusBy(trackNumber); // вынести на
		// другой слой

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	/**
	 * обновлений всех полей заявки
	 */
//	@PutMapping(value = "/proposal/{id}")
//	public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Proposal proposal) {
//		final boolean updated = proposalService.update(proposal, id);
//
//		return updated
//			? new ResponseEntity<>(HttpStatus.OK)
//			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//	}


	// ------взаимодействие с СУТ --------

	/**
	 * Передача новых заявок для СУТ
	 * <p>
	 * сделали
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
	 * <p>
	 * сделали
	 */
	@PutMapping(value = "/proposal/{id}/update")
	public ResponseEntity<?> update(@PathVariable(name = "id") int id,
									@RequestBody ServiceProposalStatus newStatus) {
		final boolean isUpdated = proposalService.updateStatusBy(id, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * обновление статуса по трек номеру
	 * <p>
	 *
	 */
	@PutMapping(value = "/proposal/update/{track_number}")
	public ResponseEntity<?> updateStatusBy(@PathVariable(name = "track_number") String track,
											@RequestBody ServiceProposalStatus newStatus) {
		final boolean isUpdated = proposalService.updateStatusBy(track, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}


	// как мониторить изменения статуса?
	// заправщивать трек номер?
}
