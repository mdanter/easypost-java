package com.easypost.app;

// java -cp "target/easypost-java-2.0.4.jar:target/gson-2.2.2.jar" Webhook


import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;

public class Webhook {

    public static void main(String[] args) {
        String apiKey = "4hkbo3ZNgVGUJJuq4rb9Pw";

        try {
            Shipment shipment = Shipment.retrieve("shp_RkJrpM4a", apiKey);
            System.out.println(shipment.prettyPrint());

        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}
