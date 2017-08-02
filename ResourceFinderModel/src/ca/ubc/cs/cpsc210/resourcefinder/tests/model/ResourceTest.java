package ca.ubc.cs.cpsc210.resourcefinder.tests.model;

import ca.ubc.cs.cpsc210.resourcefinder.model.Resource;
import ca.ubc.cs.cpsc210.resourcefinder.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

// unit tests for Resource class
public class ResourceTest {
    private Resource testResource;

    @Before
    public void runBefore() {
        testResource = new Resource("Family Services", null);
    }

    @Test
    public void testConstructor() {
        assertEquals("Family Services", testResource.getName());
        assertEquals(null, testResource.getContactInfo());
        assertEquals(Collections.<Service>emptySet(), testResource.getServices());
    }

    @Test
    public void testOffersService() {
        assertFalse(testResource.offersService(Service.SHELTER));
        testResource.addService(Service.SHELTER);
        assertTrue(testResource.offersService(Service.SHELTER));
    }

    @Test
    public void testOffersAllServicesInSet() {
        Set CurrentServices = new HashSet<>();
        CurrentServices.add(Service.FOOD);
        testResource.addService(Service.FOOD);

        assertTrue(testResource.offersAllServicesInSet(CurrentServices));
        CurrentServices.add(Service.LEGAL);
        assertFalse(testResource.offersAllServicesInSet(CurrentServices));
        testResource.addService(Service.LEGAL);
        assertTrue(testResource.offersAllServicesInSet(CurrentServices));
        testResource.addService(Service.SENIOR);
        assertTrue(testResource.offersAllServicesInSet(CurrentServices));

    }

    @Test
    public void testOffersAnyServicesInSet() {
        Set CurrentServices = new HashSet<>();
        CurrentServices.add(Service.LEGAL);
        CurrentServices.add(Service.SENIOR);
        assertFalse(testResource.offersAnyServicesInSet(CurrentServices));
        testResource.addService(Service.LEGAL);
        assertTrue(testResource.offersAnyServicesInSet(CurrentServices));
    }

    @Test
    public void testAddServices() {
        testResource.addService(Service.FOOD);
        assertTrue(testResource.offersService(Service.FOOD));
    }


    @Test
    public void testRemoveService() {
        testResource.addService(Service.FOOD);
        assertTrue(testResource.offersService(Service.FOOD));
        testResource.removeService(Service.FOOD);
        assertFalse(testResource.offersService(Service.FOOD));

    }
}