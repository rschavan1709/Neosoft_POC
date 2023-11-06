package com.neosoft.bus.repository;

import com.neosoft.bus.dto.GetBusesQueryResponse;
import com.neosoft.bus.dto.GetBusesResponse;
import com.neosoft.bus.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute,Integer> {
    @Query(nativeQuery = true,value = "select bus_id,distance,fare from bus_route br where br.route_from = :source and br.route_to = :destination")
    List<GetBusesQueryResponse> getBusesIdsFromSourceAndDestination(@Param("source") String source,@Param("destination") String destination);
}
