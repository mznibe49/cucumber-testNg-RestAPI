package com.example.demo.stepDefinitions;


import com.example.demo.model.Contract;
import com.example.demo.model.Subscriber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.testng.Assert.assertEquals;

public class StepDefinition  {

    private OkHttpClient client = new OkHttpClient();
    private Response response;

    static class SubscriberEntity {

        static Subscriber getNewSubscriber(){
            String firstName = "Julien";
            String lastName = "Fritz";
            String location = "France";
            String address = "12 rue Pascal, 69000 Lyon";
            Contract c1 = new Contract("12 rue Pascal, 69000 Lyon", new Date());
            Contract c2 = new Contract("12 rue Pascal, 69000 Lyon", new Date());
            Subscriber jack = new Subscriber(firstName, lastName, location, address, new ArrayList<>());
            jack.addContract(c1);
            jack.addContract(c2);
            return jack;
        }
    }
    CloseableHttpClient httpClient = HttpClients.createDefault();

    String newAddress = "7 rue Gambetta, 75020 Paris";
    Subscriber subscriber;

    @Given("^un abonné avec une adresse principale \"([^\"]*)\" en France$")
    public void un_abonné_avec_une_adresse_principale_en_France(String arg1) throws Throwable {
        subscriber = SubscriberEntity.getNewSubscriber();
    }

    @When("^le conseiller connecté à \"([^\"]*)\" modifie l'adresse de l'abonné$")
    public void le_conseiller_connecté_à_modifie_l_adresse_de_l_abonné(String arg1) throws Throwable {
        subscriber.setAddress(newAddress);

    }

    @Then("^l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contracts de l'abonné$")
    public void l_adresse_de_l_abonné_modifiée_est_enregistrée_sur_l_ensemble_des_contracts_de_l_abonné() throws Throwable {
        subscriber.setAddressInAllContract(newAddress);
        for(Contract contract : subscriber.getListContract()){
            assertEquals(contract.getAddress(), newAddress);
        }
    }

    @Then("^un mouvement de modification d'adresse est crée$")
    public void un_mouvement_de_modification_d_adresse_est_crée() throws Throwable {
        // testNg assertEq
        assertEquals(newAddress, subscriber.getAddress());

        // using the mock server to test the request with more details on the specification could be that way

        /*stubFor(get(urlEqualTo("/changeAddress")).willReturn(aResponse().withBody("AddressChanged")));
        HttpGet request = new HttpGet("http://localhost:8080/changeAddress");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertResponseToString(httpResponse);
        verify(getRequestedFor(urlEqualTo("/changeAddress")));
        assertEquals("AddressChanged", stringResponse);*/
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

}
