package com.neosoft.bus.repository;

import com.neosoft.bus.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute,Integer> {
    BusRoute findByRouteFromAndRouteTo(String routeFrom,String routeTo);
}
