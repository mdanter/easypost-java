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
        EasyPost.apiKey = "4hkbo3ZNgVGUJJuq4rb9Pw";

        Map<String, Object> fromAddressMap = new HashMap<String, Object>();
        fromAddressMap.put("name", "piq Chocolates");
        fromAddressMap.put("street1", "9420 Beechnut Dr");
        fromAddressMap.put("street2", "");
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
//        parcelMap.put("height", 12.1);
//        parcelMap.put("width", 8);
//        parcelMap.put("length", 19.8);

        try {
            Address fromAddress = Address.create(fromAddressMap);
            Address toAddress = Address.create(toAddressMap);
            Parcel parcel = Parcel.create(parcelMap);


            try {
                Address verified = toAddress.verify();
            } catch (EasyPostException e) {
                System.out.println(e.getMessage());
            }

            // create shipment
            Map<String, Object> shipmentMap = new HashMap<String, Object>();
            shipmentMap.put("to_address", toAddress);
            shipmentMap.put("from_address", fromAddress);
            shipmentMap.put("parcel", parcel);

            Shipment shipment = Shipment.create(shipmentMap);

            // buy postage
            List<String> buyCarriers = new ArrayList<String>();
            buyCarriers.add("USPS");
            List<String> buyServices = new ArrayList<String>();
            buyServices.add("Priority");
            // List<String> buyServiceCodes = new ArrayList<String>();
            // buyServiceCodes.add("fedex.fedex_ground");

            Map<String, Object> buyMap = new HashMap<String, Object>();
            buyMap.put("rate", shipment.lowestRate(buyCarriers, buyServices));
//            buyMap.put("insurance", 249.99);

            // shipment = shipment.buy(shipment.lowestRate(buyCarriers, buyServices));
            shipment = shipment.buy(buyMap);

            System.out.println(shipment.prettyPrint());

        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}
