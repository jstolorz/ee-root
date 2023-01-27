package org.bluesoft.filter

import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.client.WebTarget
import jakarta.ws.rs.core.GenericType
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.bluesoft.model.SimpleProperty
import spock.lang.Specification



class CachingFilterSpockTest extends Specification{

    String BASE_URL ="http://localhost:8080/ee-rest-filters/rest";
    Client client;

    void setup() {
        client = ClientBuilder.newClient();
    }

    def "test Post SimpleProperty"() {

        given:
        client.register(new ClientFilter("user","password"))
        SimpleProperty simpleProperty = new SimpleProperty("mykey","value")

        when:
        WebTarget myResource = client.target(BASE_URL).path("/service")
        Response rs = myResource.request(MediaType.APPLICATION_JSON)
              .post(Entity.json(simpleProperty),Response.class)

        then:
        assert rs.getStatus(), 200

    }

    def "test GET by id SimpleProperty"(){
        given:
        client.register(new ClientFilter("user","password"))


        when:
        SimpleProperty property = client.target(BASE_URL).path("/service/{key}")
                       .resolveTemplate("key","0")
                       .request(MediaType.APPLICATION_JSON)
                       .get(SimpleProperty.class)
        then:
        property != null

    }

    def "test GET all SimpleProperty"(){
        given:
        client.register(new ClientFilter("user","password"))
        //SimpleProperty p1 = new SimpleProperty("mykey","value")

        when:
        List<SimpleProperty> result = client.target(BASE_URL).path("/service")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<SimpleProperty>>(){})

        then:
        "mykey" == result.get(0).getKey()
        "value" == result.get(0).getValue()

    }


}
