package ca.ubc.cs.cpsc210.resourcefinder.tests.model;

import ca.ubc.cs.cpsc210.resourcefinder.model.Resource;
import ca.ubc.cs.cpsc210.resourcefinder.model.ResourceRegistry;
import ca.ubc.cs.cpsc210.resourcefinder.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// unit tests for ResourceRegistry class
public class ResourceRegistryTest {
    private ResourceRegistry testRegistry;
    private Resource r1;
    private Resource r2;
    private Resource r3;
    private Resource r4;

    @Before
    public void runBefore() {
        testRegistry = new ResourceRegistry();
        r1 = new Resource("Res 1", null);
        r2 = new Resource("Res 2", null);
        r3 = new Resource("Res 3", null);
        r4 = new Resource("Res 4", null);
    }

    @Test
    public void testAddResource() {
        testRegistry.addResource(r1);
        List<Resource> resources = testRegistry.getResources();
        assertEquals(1, resources.size());
        assertTrue(resources.contains(r1));

        testRegistry.addResource(r2);
        assertEquals(2, resources.size());
        assertEquals(Arrays.asList(r1, r2), testRegistry.getResources());
    }

    @Test
    public void testGetResourcesOfferingService() {
        loadResources();

        assertEquals(1, testRegistry.getResourcesOfferingService(Service.LEGAL).size());
        assertTrue(testRegistry.getResourcesOfferingService(Service.LEGAL).contains(r4));
        assertEquals(3, testRegistry.getResourcesOfferingService(Service.FOOD).size());
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r4)), testRegistry.getResourcesOfferingService(Service.FOOD));
    }

    @Test
    public void testGetResourcesOfferingAllServicesInSet() {
        loadResources();
        Set RequestedServices = new HashSet<>();
        RequestedServices.add(Service.FOOD);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r4)), testRegistry.getResourcesOfferingAllServicesInSet(RequestedServices));
        RequestedServices.add(Service.SHELTER);
        assertEquals(new HashSet<>(Arrays.asList(r1, r4)), testRegistry.getResourcesOfferingAllServicesInSet(RequestedServices));
    }

    @Test
    public void testGetResourcesOfferingAnyServicesInSet() {
        loadResources();
        Set RequestedServices = new HashSet<>();
        RequestedServices.add(Service.FOOD);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r4)), testRegistry.getResourcesOfferingAnyServicesInSet(RequestedServices));
        RequestedServices.add(Service.SENIOR);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r3, r4)), testRegistry.getResourcesOfferingAnyServicesInSet(RequestedServices));
    }



    // MODIFIES: this
    // EFFECTS:  adds services to resources and resources to registry
    private void loadResources() {
        r1.addService(Service.FOOD);
        r1.addService(Service.SHELTER);
        r2.addService(Service.YOUTH);
        r2.addService(Service.FOOD);
        r3.addService(Service.SENIOR);
        r4.addService(Service.SHELTER);
        r4.addService(Service.FOOD);
        r4.addService(Service.LEGAL);

        testRegistry.addResource(r1);
        testRegistry.addResource(r2);
        testRegistry.addResource(r3);
        testRegistry.addResource(r4);
    }
}