package com.example.demo.api.shipments;

import com.example.demo.api.Business.Business;
import com.example.demo.api.Courier.courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ShipmentRepo extends JpaRepository<shipments,Long> {
    List<shipments>findAllByBusinessId(Business business);
    List<shipments>findAllByCourierId(courier courier);
    @Modifying(flushAutomatically = true)
    @Query("UPDATE shipments a "+"SET a.courierId =:courierId,a.shipmentToPickUpDate =:date WHERE a.Id =:shipmentId")
    void shipmentsUpdateCourier(@Param("courierId") courier courierId, @Param("shipmentId") Long shipmentId,@Param("date") Date date);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE shipments a "+"SET a.shipmentFinishedDate =:date,a.shipmentStatus=:shipmentStatus WHERE a.Id =:shipmentId")
    void shipmentsFinished(@Param("shipmentId") Long shipmentId,@Param("date") Date date,@Param("shipmentStatus")ShipmentStatus shipmentStatus);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE shipments a "+"SET a.shipmentFinishedDate =:date ,a.shipmentStatus=:shipmentStatus WHERE a.Id =:shipmentId")
    void SetShipmentStatus(@Param("shipmentId")Long shipmentId,@Param("date") Date date,@Param("shipmentStatus")ShipmentStatus shipmentStatus);

}
