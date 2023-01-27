package org.bluesoft.arquillian.entity;

import jakarta.inject.Inject;
import junit.framework.TestCase;
import org.bluesoft.arquillian.ejb.ManagerEJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(Arquillian.class)
@RunAsClient
public class CustomerIT extends TestCase {

    @Inject
    private ManagerEJB ejb;


    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "testjpa.war")
                .addPackage(Customer.class.getPackage())
                .addPackage(ManagerEJB.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");


    }


    @Test
    public void test() {

        try{

            ejb.createCustomer("john","park avenue 125", "jsmith@gmail.com", "+3955555");
            Customer customer = ejb.findCustomerByName("john");
            //assertNotNull(customer);

            log("=======> Customer List ");


        }catch(Exception e){
            System.out.println(e.getClass());
            e.getMessage();
        }

    }

    private void log(String string) {
        System.out.println(string);

    }

}