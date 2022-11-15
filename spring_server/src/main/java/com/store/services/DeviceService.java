package com.store.services;

import com.google.gson.Gson;
import com.store.entity.Brand;
import com.store.entity.Device;
import com.store.entity.DeviceInfo;
import com.store.entity.Type;
import com.store.excaptions.DeviceAlreadyExistException;
import com.store.excaptions.DeviceNotFoundException;
import com.store.model.DeviceDTO;
import com.store.repository.DeviceRepository;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private StorageService storageService;

    public Map<String, Object> getDevices(Map<String, Object> params) throws DeviceNotFoundException {
        Brand brand = (Brand)params.get("brand");
        Type type = (Type)params.get("type");
        int limit = params.get("limit") == null ? 9 : (Integer)params.get("limit");
        int page = params.get("page") == null ? 1 : (Integer)params.get("page");
        int offset = page * limit - limit;

        List<DeviceDTO> devices = new ArrayList<>();
        var size = 0;
        Map<String, Object> result = new LinkedHashMap<>();

        if (type == null && brand == null) {
            devices = deviceRepository.findAll().stream().map(DeviceDTO::toDTO).toList();
            size = devices.size();
            devices = devices.stream().skip(offset).limit(limit).toList();
        }

        if (type == null && brand != null) {
            devices = deviceRepository.findDevicesByBrand(brand).stream().map(DeviceDTO::toDTO).toList();
            size = devices.size();
            devices = devices.stream()
                    .skip(offset)
                    .limit(limit)
                    .toList();
        }

        if (type != null && brand == null) {
            devices = deviceRepository.findDevicesByType(type).stream().map(DeviceDTO::toDTO).toList();

            size = devices.size();
            devices = devices.stream()
                    .skip(offset)
                    .limit(limit)
                    .toList();
        }

        if (type != null && brand != null) {
            devices = deviceRepository.findDevicesByTypeAndBrand(type, brand).stream().map(DeviceDTO::toDTO).toList();
            size = devices.size();
            devices = devices.stream()
                    .skip(offset)
                    .limit(limit)
                    .toList();
        }

        result.put("count", size);
        result.put("rows", devices);

        return result;
    }

    public DeviceDTO getDevice(Long id) throws DeviceNotFoundException {
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            return DeviceDTO.toDTO(device.get());
        } else {
            throw new DeviceNotFoundException("Device has not been found");
        }

    }

    public Device create(
            String name,
            Brand brand,
            Type type,
            Integer price,
            MultipartFile file,
            String  infoJson
    ) throws DeviceAlreadyExistException, IOException, ParseException {
        if (deviceRepository.findByName(name).isPresent()) {
            throw new DeviceAlreadyExistException("Device with this name already exist");
        } else {
            String img = storageService.save(file);
            List<DeviceInfo> info = parseInfo(infoJson);

            Device device = new Device();
            device.setName(name);
            device.setPrice(price);
            device.setType(type);
            device.setBrand(brand);
            device.setRating(5);
            device.setImg(img);
            device.setInfo(info);

            return deviceRepository.save(device);
        }
    }

    public List<DeviceInfo> parseInfo(String info){
        Gson gson = new Gson();
        DeviceInfo[] deviceInfo = gson.fromJson(info, DeviceInfo[].class);

        return Arrays.stream(deviceInfo).toList();
    }

}
