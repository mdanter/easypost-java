package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Batch extends EasyPostResource {
	public String id;
	String mode;
	public BatchStatus status;
	List<Shipment> shipments;
	String labelUrl;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}
	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public String getLabelUrl() {
		return labelUrl;
	}
	public void setLabelUrl(String labelUrl) {
		this.labelUrl = labelUrl;
	}


	// create
	public static Batch create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("batch", params);

		return request(RequestMethod.POST, classURL(Batch.class), wrappedParams, Batch.class, apiKey);
	}

	// retrieve
	public static Batch retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Batch.class, id), null, Batch.class, apiKey);
	}

	// all
	public static BatchCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Batch.class), params, BatchCollection.class, apiKey);
	}

	// create_and_buy
	public static Batch create_and_buy(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("batch", params);

		return request(RequestMethod.POST, classURL(Batch.class), wrappedParams, Batch.class, apiKey);
	}

	// refresh
	public Batch refresh(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.GET,
			String.format("%s", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// label
	public Batch label(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/label", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// removeShipment
	public Batch removeShipment(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/remove_shipment", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// addShipment
	public Batch addShipment(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/add_shipment", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

}
