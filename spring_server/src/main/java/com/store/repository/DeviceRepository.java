package com.store.repository;

import com.store.entity.Brand;
import com.store.entity.Device;
import com.store.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByName(String name);
    List<Device> findDevicesByBrand(Brand brand);
    List<Device> findDevicesByType(Type type);
    List<Device> findDevicesByTypeAndBrand(Type type, Brand brand);

}
