package com.example.demo.api.shipments;

import com.example.demo.api.Courier.courier;
import com.example.demo.api.shipments.shipments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ShipmentRepo extends JpaRepository<shipments,Long> {
    @Modifying(flushAutomatically = true)
    @Query("UPDATE shipments a "+"SET a.courierId =:courierId,a.shipmentToPickUpDate =:date WHERE a.Id =:shipmentId")
//    @Query("UPDATE shipments a"+"SET a.courier_id_id= :courierId ,shipment_to_pick_up_date=current_date WHERE id= :shipmentId;")
    void shipmentsUpdateCourier(@Param("courierId") courier courierId, @Param("shipmentId") Long shipmentId,@Param("date") Date date);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE shipments a "+"SET a.isDelivered=true, a.shipmentFinishiedDate =:date WHERE a.Id =:shipmentId")
    void shipmentsFinished(@Param("shipmentId") Long shipmentId,@Param("date") Date date);


}
