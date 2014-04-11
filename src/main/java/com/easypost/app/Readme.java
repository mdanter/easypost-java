package com.easypost.app;

// java -cp "target/easypost-java-2.0.4.jar:target/gson-2.2.4.jar" Readme

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.Parcel;
import com.easypost.model.Shipment;

public class Readme {

	public static void main(String[] args) {
		String apiKey = "4hkbo3ZNgVGUJJuq4rb9Pw";

		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("name", "piq Chocolates");
		fromAddressMap.put("street1", "9420 Beechnut Dr");
		fromAddressMap.put("city", "Austin");
		fromAddressMap.put("state", "TX");
		fromAddressMap.put("zip", "78748");
		fromAddressMap.put("phone", "512-348-7209");

		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("name", "Matyas Danter");
		toAddressMap.put("street1", "1320 Fillmore Ave");
		toAddressMap.put("street2", "UNIT 107");
		toAddressMap.put("city", "Charlotte");
		toAddressMap.put("state", "NC");
		toAddressMap.put("zip", "28203");
		toAddressMap.put("phone", "575-571-9260");
		toAddressMap.put("country", "US");

		Map<String, Object> parcelMap = new HashMap<String, Object>();

		parcelMap.put("predefined_package", "SmallFlatRateBox");
		parcelMap.put("weight", 22.9);

		try {
			Address fromAddress = Address.create(fromAddressMap, apiKey);
			Address toAddress = Address.create(toAddressMap, apiKey);
			Parcel parcel = Parcel.create(parcelMap, apiKey);
			
			Address verified = toAddress.verify(apiKey);

			// create shipment
			Map<String, Object> shipmentMap = new HashMap<String, Object>();
			shipmentMap.put("to_address", verified);
			shipmentMap.put("from_address", fromAddress);
			shipmentMap.put("parcel", parcel);

			Shipment shipment = Shipment.create(shipmentMap, apiKey);

			// buy postage
			List<String> buyCarriers = new ArrayList<String>();
			buyCarriers.add("USPS");
			List<String> buyServices = new ArrayList<String>();
			buyServices.add("Priority");

			Map<String, Object> buyMap = new HashMap<String, Object>();
			buyMap.put("rate", shipment.lowestRate(buyCarriers, buyServices));

			shipment = shipment.buy(buyMap, apiKey);

			System.out.println("URL = "+shipment.getPostageLabel().getLabelUrl());
			System.out.println("TRACKING = "+shipment.getTrackingCode());
			System.out.println("ID = "+shipment.getId());
			
			System.out.println(shipment.prettyPrint());

		} catch (EasyPostException e) {
			e.printStackTrace();
		}
	}
}
