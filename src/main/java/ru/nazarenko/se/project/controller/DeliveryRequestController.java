package ru.nazarenko.se.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.service.DeliveryRequestService;

import java.util.List;

@RestController
public class DeliveryRequestController {

	private final DeliveryRequestService deliveryRequestService;

	@Autowired
	public DeliveryRequestController(DeliveryRequestService deliveryRequestService) {
		this.deliveryRequestService = deliveryRequestService;
	}

	// TODO: 29/04/2021 rename paths

	/**
	 * Создание заявки в сервисе
	 * @return id
	 */
	@PostMapping(value = "/proposal/new")
	public ResponseEntity<?> createNewProposal(@RequestBody DeliveryRequest deliveryRequest) {
		try {
			long id = deliveryRequestService.createNewRequest(deliveryRequest);
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Получение всех заявок
	 */
	@GetMapping(value = "/proposals")
	public ResponseEntity<List<DeliveryRequest>> getAllProposals() {
		final List<DeliveryRequest> deliveryRequests = deliveryRequestService.readAllRequests();

		return deliveryRequests != null && !deliveryRequests.isEmpty()
			? new ResponseEntity<>(deliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявки по id
	 */
	@GetMapping(value = "/proposal/{id}")
	public ResponseEntity<DeliveryRequest> getProposalById(@PathVariable(name = "id") long id) {
		final DeliveryRequest deliveryRequest = deliveryRequestService.getRequestBy(id);

		return deliveryRequest != null
			? new ResponseEntity<>(deliveryRequest, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статуса заявки по id
	 */
	@GetMapping(value = "/proposal/status/{id}")
	public ResponseEntity<RequestServiceStatus> getProposalStatusBy(@PathVariable(name = "id") int id) {
		final RequestServiceStatus proposalStatus = deliveryRequestService.getRequestStatusById(id);

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статуса заявки по трек номеру
	 */
	@GetMapping(value = "/proposal/status/track/{track_number}")
	public ResponseEntity<RequestServiceStatus> getProposalStatusBy(
		@PathVariable(name = "track_number") String trackNumber) {

		final RequestServiceStatus proposalStatus = deliveryRequestService.getRequestStatusByTrack(trackNumber);

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявок с определенным статусом
	 */
	@PostMapping(value = "/proposalsByStatus")
	public ResponseEntity<List<DeliveryRequest>> getProposalWith(@RequestBody RequestServiceStatus status) {
		final List<DeliveryRequest> newDeliveryRequests = deliveryRequestService.getRequestsByStatus(
			status);

		return newDeliveryRequests != null && !newDeliveryRequests.isEmpty()
			? new ResponseEntity<>(newDeliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Передача новых заявок
	 * для СУТ
	 */
	@GetMapping(value = "/proposals/new")
	public ResponseEntity<List<DeliveryRequest>> getNewProposals() {
		final List<DeliveryRequest> newDeliveryRequests = deliveryRequestService.getRequestsByStatus(
			RequestServiceStatus.NEW_CREATED);

		return newDeliveryRequests != null && !newDeliveryRequests.isEmpty()
			? new ResponseEntity<>(newDeliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Запись трек номера для заявки по id
	 * для СУТ
	 */
	@PutMapping(value = "/proposal/track/{id}")
	public ResponseEntity<?> writeTrackForProposalById(@PathVariable(name = "id") long id,
													   @RequestBody String track_number) {

		boolean isUpdated = deliveryRequestService.writeTrackById(id, track_number);
		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * обновление/запись статуса по proposalId
	 * для СУТ
	 */
	@PutMapping(value = "/proposal/status/update/{id}")
	public ResponseEntity<?> updateProposalStatusBy(@PathVariable(name = "id") long id,
													@RequestBody RequestServiceStatus newStatus) {
		final boolean isUpdated = deliveryRequestService.updateRequestsStatusBy(id, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * обновление/запись статуса по трек номеру
	 * для СУТ
	 */
	@PutMapping(value = "/proposal/status/updatebytrack/{track_number}")
	public ResponseEntity<?> updateProposalStatusBy(@PathVariable(name = "track_number") String track,
													@RequestBody RequestServiceStatus newStatus) {
		final boolean isUpdated = deliveryRequestService.updateRequestsStatusBy(track, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

}
