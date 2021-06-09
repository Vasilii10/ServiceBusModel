package ru.nazarenko.se.project.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.nazarenko.se.project.model.*;
import ru.nazarenko.se.project.service.DeliveryRequestService;

import java.util.List;

@Api(value = "DeliveryRequestController")
@RestController
public class DeliveryRequestController {

	private final DeliveryRequestService deliveryRequestService;

	@Autowired
	public DeliveryRequestController(DeliveryRequestService deliveryRequestService) {
		this.deliveryRequestService = deliveryRequestService;
	}

	@ApiOperation(value = "Create new requests ", response = DeliveryRequest.class, tags = "Create request")
	@PostMapping(value = "/request/new")
	public ResponseEntity<?> createNewRequest(@RequestBody DeliveryRequest deliveryRequest) {
		try {
			long createdId = deliveryRequestService.createNewRequest(deliveryRequest);

			return new ResponseEntity<>(createdId, HttpStatus.CREATED);
		} catch (RequestNotCreatedException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Get list of all requests", response = Iterable.class, tags = "Get existed all requests")
	@GetMapping(value = "/requests")
	public ResponseEntity<List<DeliveryRequest>> getAllRequests() {
		final List<DeliveryRequest> deliveryRequests = deliveryRequestService.readAllRequests();

		return deliveryRequests != null && !deliveryRequests.isEmpty()
			? new ResponseEntity<>(deliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Get request by id", response = DeliveryRequest.class, tags = "Get request by specific id")
	@GetMapping(value = "/request/{id}")
	public ResponseEntity<DeliveryRequest> getRequestById(@PathVariable(name = "id") long id) {
		final DeliveryRequest deliveryRequest = deliveryRequestService.getRequestBy(id);

		return deliveryRequest != null
			? new ResponseEntity<>(deliveryRequest, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Get request status by id", response = Iterable.class, tags = "Get status by id")
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

	@ApiOperation(value = "Get request status by track number", response = HttpStatus.class, tags = "Get status by " +
		"track number")
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

	@ApiOperation(value = "Get requests with specific status", response = Iterable.class, tags = "Get request by " +
		"status")
	@PostMapping(value = "/requestsByStatus")
	public ResponseEntity<List<DeliveryRequest>> getRequestWith(@RequestBody RequestServiceStatus status) {
		final List<DeliveryRequest> newDeliveryRequests = deliveryRequestService.getRequestsByStatus(
			status
		);

		return newDeliveryRequests != null && !newDeliveryRequests.isEmpty()
			? new ResponseEntity<>(newDeliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Get all requests with status NEW_CREATED", response = Iterable.class, tags = "Get new " +
		"requests")
	@GetMapping(value = "/requests/new")
	public ResponseEntity<List<DeliveryRequest>> getNewRequests() {
		final List<DeliveryRequest> newDeliveryRequests = deliveryRequestService.getRequestsByStatus(
			RequestServiceStatus.NEW_CREATED
		);

		return newDeliveryRequests != null && !newDeliveryRequests.isEmpty()
			? new ResponseEntity<>(newDeliveryRequests, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Put track number to request", response = Iterable.class, tags = "Put track number")
	@PutMapping(value = "/request/track/{id}")
	public ResponseEntity<?> writeTrackForRequestById(@PathVariable(name = "id") long id,
													  @RequestBody String trackNumber) {
		try {
			deliveryRequestService.writeTrackById(id, trackNumber);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TrackNumberIsNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@ApiOperation(value = "Update request status by id", response = HttpStatus.class, tags = "Update status by id")
	@PutMapping(value = "/request/status/update/{id}")
	public ResponseEntity<?> updateRequestStatusBy(@PathVariable(name = "id") long id,
												   @RequestBody RequestServiceStatus newStatus) {

		return deliveryRequestService.updateRequestsStatusBy(id, newStatus)
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@ApiOperation(value = "Update request status by track number", response = HttpStatus.class, tags = "Update status")
	@PutMapping(value = "/request/status/updatebytrack/{track_number}")
	public ResponseEntity<?> updateRequestStatusBy(@PathVariable(name = "track_number") String track,
												   @RequestBody RequestServiceStatus newStatus) {

		return deliveryRequestService.updateRequestsStatusBy(track, newStatus)
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ApiOperation(value = "temporary ability", response = HttpStatus.class, tags = "Delete request")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteRequestStatusBy(@PathVariable(name = "id") Long id) {

		return deliveryRequestService.deleteRequest(id)
			? new ResponseEntity<>(HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
