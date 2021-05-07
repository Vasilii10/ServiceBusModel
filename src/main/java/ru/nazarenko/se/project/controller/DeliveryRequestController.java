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
	public ResponseEntity<?> createNewRequest(@RequestBody DeliveryRequest deliveryRequest) {
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
	public ResponseEntity<List<DeliveryRequest>> getAllRequests() {
		final List<DeliveryRequest> deliveryRequests = deliveryRequestService.readAllRequests();

		return deliveryRequests != null && !deliveryRequests.isEmpty()
			? new ResponseEntity<>(deliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение заявки по id
	 */
	@GetMapping(value = "/request/{id}")
	public ResponseEntity<DeliveryRequest> getRequestById(@PathVariable(name = "id") long id) {
		final DeliveryRequest deliveryRequest = deliveryRequestService.getRequestBy(id);

		return deliveryRequest != null
			? new ResponseEntity<>(deliveryRequest, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Получение статуса заявки по id
	 */
	@GetMapping(value = "/request/status/{id}")
	public ResponseEntity<RequestServiceStatus> getRequestStatusBy(@PathVariable(name = "id") long id) {
		final RequestServiceStatus proposalStatus;
		try {
			proposalStatus = deliveryRequestService.getRequestStatusById(id);

			return new ResponseEntity<>(proposalStatus, HttpStatus.OK);
		} catch (RequestNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Получение статуса заявки по трек номеру
	 */
	@GetMapping(value = "/request/status/track/{track_number}")
	public ResponseEntity<RequestServiceStatus> getRequestStatusBy(
		@PathVariable(name = "track_number") String trackNumber) {

		final RequestServiceStatus proposalStatus;
		try {
			proposalStatus = deliveryRequestService.getRequestStatusByTrack(trackNumber);

			return new ResponseEntity<>(proposalStatus, HttpStatus.OK);
		} catch (RequestNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Получение заявок с статусом
	 */
	@PostMapping(value = "/requestsByStatus")
	public ResponseEntity<List<DeliveryRequest>> getRequestWith(@RequestBody RequestServiceStatus status) {
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
	public ResponseEntity<List<DeliveryRequest>> getNewRequests() {
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
	public ResponseEntity<?> writeTrackForRequestById(@PathVariable(name = "id") long id,
													  @RequestBody String trackNumber) {
		try {
			deliveryRequestService.writeTrackById(id, trackNumber);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TrackNumberIsExsistedException e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	/**
	 * Обновление/запись статуса по proposalId
	 */
	@PutMapping(value = "/request/status/update/{id}")
	public ResponseEntity<?> updateRequestStatusBy(@PathVariable(name = "id") long id,
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
	public ResponseEntity<?> updateRequestStatusBy(@PathVariable(name = "track_number") String track,
												   @RequestBody RequestServiceStatus newStatus) {
		final boolean isUpdated = deliveryRequestService.updateRequestsStatusBy(track, newStatus);

		return isUpdated
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
