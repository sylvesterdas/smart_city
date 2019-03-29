package com.lenin.smart_city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenin.smart_city.models.ReportedPlace;

public interface ReportsRepository extends JpaRepository<ReportedPlace, Long> {

}
