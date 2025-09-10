package com.mendes.api_system_registration.Events.Repository;

import com.mendes.api_system_registration.Events.Model.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventsModel, Long> {
}
