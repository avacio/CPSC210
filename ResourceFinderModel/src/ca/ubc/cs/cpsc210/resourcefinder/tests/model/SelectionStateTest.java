package ca.ubc.cs.cpsc210.resourcefinder.tests.model;

import ca.ubc.cs.cpsc210.resourcefinder.model.Resource;
import ca.ubc.cs.cpsc210.resourcefinder.model.ResourceRegistry;
import ca.ubc.cs.cpsc210.resourcefinder.model.SelectionState;
import ca.ubc.cs.cpsc210.resourcefinder.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

// unit tests for SelectionState class
public class SelectionStateTest {
    private SelectionState testSelectionState;
    private ResourceRegistry registry;
    private Resource r1;
    private Resource r2;
    private Resource r3;
    private Resource r4;

    @Before
    public void runBefore() {
        registry = new ResourceRegistry();
        loadResources();

        testSelectionState = new SelectionState(registry);
    }

    @Test
    public void testConstructor() {
        testSelectionState.selectService(Service.FOOD);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r4)), testSelectionState.getResourcesWithSelectedServices());
        testSelectionState.selectService(Service.COUNSELLING);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r3, r4)), testSelectionState.getResourcesWithSelectedServices());
        testSelectionState.deselectService(Service.FOOD);
        assertEquals(new HashSet<>(Arrays.asList(r3)), testSelectionState.getResourcesWithSelectedServices());
    }

    @Test
    public void testSetSelectAny() {
        testSelectionState.setSelectAny();
        testSelectionState.selectService(Service.YOUTH);
        assertEquals(new HashSet<>(Arrays.asList(r2)), testSelectionState.getResourcesWithSelectedServices());
        testSelectionState.selectService(Service.FOOD);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r4)), testSelectionState.getResourcesWithSelectedServices());

        testSelectionState.setSelectedServices(new HashSet<>(Arrays.asList(Service.COUNSELLING, Service.SENIOR, Service.LEGAL)));
        assertEquals(new HashSet<>(Arrays.asList(r3, r4)), testSelectionState.getResourcesWithSelectedServices());
    }

    @Test
    public void testSetSelectAll() {
        testSelectionState.setSelectAll();
        testSelectionState.selectService(Service.FOOD);
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r4)), testSelectionState.getResourcesWithSelectedServices());
        testSelectionState.selectService(Service.YOUTH);
        assertEquals(new HashSet<>(Arrays.asList(r2)), testSelectionState.getResourcesWithSelectedServices());

        testSelectionState.setSelectedServices(new HashSet<>(Arrays.asList(Service.COUNSELLING, Service.SENIOR, Service.LEGAL)));
        assertEquals(new HashSet<>(Arrays.asList()), testSelectionState.getResourcesWithSelectedServices());
    }

    @Test
    public void testGetResourcesWithSelectedServices() {
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r3, r4)), testSelectionState.getResourcesWithSelectedServices());
        testSelectionState.setSelectAll();
        assertEquals(new HashSet<>(Arrays.asList(r1, r2, r3, r4)), testSelectionState.getResourcesWithSelectedServices());
    }


    // MODIFIES: this
    // EFFECTS:  adds services to resources and resources to resource registry
    private void loadResources() {
        r1 = new Resource("Res 1", null);
        r2 = new Resource("Res 2", null);
        r3 = new Resource("Res 3", null);
        r4 = new Resource("Res 4", null);

        r1.addService(Service.FOOD);
        r1.addService(Service.SHELTER);
        r2.addService(Service.YOUTH);
        r2.addService(Service.FOOD);
        r3.addService(Service.SENIOR);
        r3.addService(Service.COUNSELLING);
        r4.addService(Service.SHELTER);
        r4.addService(Service.FOOD);
        r4.addService(Service.LEGAL);

        registry.addResource(r1);
        registry.addResource(r2);
        registry.addResource(r3);
        registry.addResource(r4);
    }
}