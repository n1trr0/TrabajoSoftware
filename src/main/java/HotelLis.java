import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Hotel;
import com.amadeus.resources.HotelBooking;
import com.amadeus.resources.HotelOfferSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HotelLis {


    private static Amadeus amadeus = Amadeus
            .builder("Wczhhl2EvY6NBImZIAe4PBaJMKkgukhs", "KXdQi5B1Gi5ohPfc")
            .build();
    public static void listHotels(String ciudad)throws ResponseException, InterruptedException{
        limpiarjson("hotels.json");
        Hotel[] hotels = amadeus.referenceData.locations.hotels.byCity.get(
                Params.with("cityCode", ciudad)
        );
        // Check if hotels array is not null and has at least one hotel
        if (hotels != null && hotels.length > 0) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ///////////////////////////////////
            int numhoteles= Math.min(10, hotels.length);
            Hotel[]primeros=new Hotel[numhoteles];
            System.arraycopy(hotels, 0, primeros, 0, numhoteles);
            ////////////////////////////////////
            String jsonOutput = gson.toJson(primeros);
            // Check response status code
            if (hotels[0].getResponse().getStatusCode() != 200) {
                System.out.println("Wrong status code: " + hotels[0].getResponse().getStatusCode());
                System.exit(-1);
            }
            // Print the first 10 hotels
            try (FileWriter fileWriter = new FileWriter("hotels.json")) {
                fileWriter.write(jsonOutput);
                System.out.println("JSON data has been written to the file.");
            } catch (IOException e) {
                System.err.println("Error writing JSON data to file: " + e.getMessage());
            }
        } else {
            System.out.println("No hotels found.");
        }
    }
    public static void limpiarjson(String json){
        String jsonEmpty = "[]"; // Cadena JSON vacía (un array vacío)
        try (FileWriter fileWriter = new FileWriter(json)) {
            fileWriter.write(jsonEmpty);
            System.out.println("JSON file has been cleared.");
        } catch (IOException e) {
            System.err.println("Error clearing JSON file: " + e.getMessage());
        }
    }

    public static void writeofferId() throws ResponseException {
        limpiarjson("ID.json");
        HotelOfferSearch[] offers = amadeus.shopping.hotelOffersSearch.get(
                Params.with("hotelIds", "ARMADALC")
                        .and("adults", 2)
        );

        if(offers!=null && offers.length>0){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonoutput=gson.toJson(offers);
            if (offers[0].getResponse().getStatusCode() != 200) {
                System.out.println("Wrong status code: " + offers[0].getResponse().getStatusCode());
                System.exit(-1);
            }
            try (FileWriter fileWriter = new FileWriter("ID.json")) {
                fileWriter.write(jsonoutput);
                System.out.println("JSON data has been written to the file.");
            } catch (IOException e) {
                System.err.println("Error writing JSON data to file: " + e.getMessage());
            }
        }
        else {
            System.out.println("No hotels found.");
        }



       /* if (offers[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + offers[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        String oferta= String.valueOf(offers[0]);
        System.out.println(oferta);*/


    }
    public static String getofferid(){
        String jsonFilePath = "ID.json";
        String iden = null;

        try {
            // Lee el contenido del archivo JSON como una cadena
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // Convierte la cadena JSON en un objeto JSON
            JSONArray jsonArray = new JSONArray(jsonString);

            // Itera sobre el arreglo JSON y extrae la información necesaria
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONArray offers = obj.getJSONArray("offers");

                // Itera sobre el arreglo de ofertas
                for (int j = 0; j < offers.length(); j++) {
                    JSONObject offer = offers.getJSONObject(j);
                    iden = offer.getString("id");
                    System.out.println("iden: " + iden);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iden;
    }
    public static void booking() throws ResponseException {
        String body = "{\"data\""
                + ":{\"offerId\":\"2F5B1C3B215FA11FD5A44BE210315B18FF91BDA2FEDDD879907A3798F41D1C28\""
                + ",\"guests\":[{\"id\":1,\"name\":{\"title\":\"MR\",\"firstName\":\"BOB\","
                + "\"lastName\" :\"SMITH\"},\"contact\":{\"phone\":\"+33679278416\",\""
                + "email\":\"bob.smith@email.com\"}}],\""
                + "payments\":[{\"id\":1,\"method\":\"creditCard\",\""
                + "card\":{\"vendorCode\":\"VI\",\"cardNumber\""
                + ":\"4151289722471370\",\"expiryDate\":\"2022-08\"}}]}}";

        HotelBooking[] hotel = amadeus.booking.hotelBookings.post(body);

        if (hotel[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + hotel[0].getResponse().getStatusCode());

            System.exit(-1);
        }

        System.out.println(hotel[0]);
    }



    public static void main(String[] args) throws ResponseException, InterruptedException {
        listHotels("MAD");
       // writeofferId();
        //getofferid();
    }


}
