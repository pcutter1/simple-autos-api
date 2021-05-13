package com.galvanize.autosapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutosRepository extends JpaRepository<Auto, Long> {
    List<Auto> findByColorContainsAndMakeContains(String color, String make);

    Auto findByVin(String vin);
}
