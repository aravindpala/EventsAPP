package com.events.eventvendorapp.repository.vendor;


import com.events.eventvendorapp.model.vendor.EventServices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventServicesRepository extends MongoRepository<EventServices, String> {
    EventServices findByVendorId(String vendorId);
    EventServices findByServiceName(String serviceName);
}
