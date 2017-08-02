package ca.ubc.cs.cpsc210.resourcefinder.model;


import java.util.*;

// Registry of available resources
public class ResourceRegistry {
    private List<Resource> resources;

    // EFFECTS: constructs empty resource registry
    public ResourceRegistry() {
        this.resources = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add resource to registry, maintains resources in the order added to registry
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    // EFFECTS: returns resources in registry as unmodifiable list (in order that they were added
    // to registry)
    public List<Resource> getResources() {
        return Collections.unmodifiableList(resources);
    }

    // EFFECTS: returns set of resources in registry that offer the given service
    public Set<Resource> getResourcesOfferingService(Service service) {
        Set ResourcesWithService = new HashSet();

        for (Resource next: resources) {
            if (next.offersService(service)) {ResourcesWithService.add(next);}
        }
        return ResourcesWithService;
    }

    // EFFECTS: returns set of resources in registry that offer all the services in requestedServices set
    public Set<Resource> getResourcesOfferingAllServicesInSet(Set<Service> requestedServices) {
        Set ResourcesWithServices = new HashSet();

        for (Resource next: resources) {
            if (next.offersAllServicesInSet(requestedServices)) {ResourcesWithServices.add(next);}
        }
        return ResourcesWithServices;
    }

    // EFFECTS: returns set of resources in registry that off any of the services in requestedServices set
    public Set<Resource> getResourcesOfferingAnyServicesInSet(Set<Service> requestedServices) {
        Set ResourcesWithServices = new HashSet();

        for (Resource next: resources) {
            if (next.offersAnyServicesInSet(requestedServices)) {ResourcesWithServices.add(next);}
        }
        return ResourcesWithServices;
    }
}
