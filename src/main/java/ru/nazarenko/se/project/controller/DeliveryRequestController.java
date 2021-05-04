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

	/**
	 * Создание заявки
	 *
	 * @return id
	 */
	@PostMapping(value = "/request/new")
	public ResponseEntity<?> createNewProposal(@RequestBody DeliveryRequest deliveryRequest) {
		try {
			long createdId = deliveryRequestService.createNewRequest(deliveryRequest);

			return new ResponseEntity<>(createdId, HttpStatus.CREATED);
		} catch (RequestNotCreatedException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Получение всех заявок
	 */
	@GetMapping(value = "/requests")
	public ResponseEntity<List<DeliveryRequest>> getAllProposals() {
		final List<DeliveryRequest> deliveryRequests = deliveryRequestService.readAllRequests();

		return deliveryRequests != null && !deliveryRequests.isEmpty()
			? new ResponseEntity<>(deliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявки по id
	 */
	@GetMapping(value = "/request/{id}")
	public ResponseEntity<DeliveryRequest> getProposalById(@PathVariable(name = "id") long id) {
		final DeliveryRequest deliveryRequest = deliveryRequestService.getRequestBy(id);

		return deliveryRequest != null
			? new ResponseEntity<>(deliveryRequest, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статуса заявки по id
	 */
	@GetMapping(value = "/request/status/{id}")
	public ResponseEntity<RequestServiceStatus> getProposalStatusBy(@PathVariable(name = "id") int id) {
		final RequestServiceStatus proposalStatus = deliveryRequestService.getRequestStatusById(id);

		return proposalStatus != null
			? new ResponseEntity<>(proposalStatus, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статуса заявки по трек номеру
	 */
	@GetMapping(value = "/request/status/track/{track_number}")
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
	@PostMapping(value = "/requestsByStatus")
	public ResponseEntity<List<DeliveryRequest>> getProposalWith(@RequestBody RequestServiceStatus status) {
		final List<DeliveryRequest> newDeliveryRequests = deliveryRequestService.getRequestsByStatus(
			status);

		return newDeliveryRequests != null && !newDeliveryRequests.isEmpty()
			? new ResponseEntity<>(newDeliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Передача новых заявок
	 */
	@GetMapping(value = "/requests/new")
	public ResponseEntity<List<DeliveryRequest>> getNewProposals() {
		final List<DeliveryRequest> newDeliveryRequests = deliveryRequestService.getRequestsByStatus(
			RequestServiceStatus.NEW_CREATED);

		return newDeliveryRequests != null && !newDeliveryRequests.isEmpty()
			? new ResponseEntity<>(newDeliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Запись трек номера для заявки по id
	 */
	@PutMapping(value = "/request/track/{id}")
	public ResponseEntity<?> writeTrackForProposalById(@PathVariable(name = "id") long id,
													   @RequestBody String track_number) {
		try {
			deliveryRequestService.writeTrackById(id, track_number);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TrackNumberIsExsistedException trackNumberIsExsistedException) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	/**
	 * Обновление/запись статуса по proposalId
	 */
	@PutMapping(value = "/request/status/update/{id}")
	public ResponseEntity<?> updateProposalStatusBy(@PathVariable(name = "id") long id,
													@RequestBody RequestServiceStatus newStatus) {
		final boolean isUpdated = deliveryRequestService.updateRequestsStatusBy(id, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * Обновление/запись статуса по трек номеру
	 */
	@PutMapping(value = "/request/status/updatebytrack/{track_number}")
	public ResponseEntity<?> updateProposalStatusBy(@PathVariable(name = "track_number") String track,
													@RequestBody RequestServiceStatus newStatus) {
		final boolean isUpdated = deliveryRequestService.updateRequestsStatusBy(track, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
