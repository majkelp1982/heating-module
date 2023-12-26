package pl.smarthouse.heatingmodule.configurations;

import static pl.smarthouse.sharedobjects.enums.ZoneName.*;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import pl.smarthouse.heatingmodule.model.core.Buffers;
import pl.smarthouse.heatingmodule.model.core.Devices;
import pl.smarthouse.heatingmodule.model.dao.HeatingModuleDao;
import pl.smarthouse.heatingmodule.model.dao.ZoneDao;
import pl.smarthouse.heatingmodule.properties.Esp32ModuleProperties;
import pl.smarthouse.sharedobjects.enums.Operation;
import pl.smarthouse.sharedobjects.enums.ZoneName;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20Result;
import pl.smarthouse.smartmonitoring.model.BooleanCompareProperties;
import pl.smarthouse.smartmonitoring.properties.defaults.Ds18b20DefaultProperties;
import pl.smarthouse.smartmonitoring.service.CompareProcessor;
import pl.smarthouse.smartmonitoring.service.MonitoringService;

@Configuration
@RequiredArgsConstructor
@Getter
@Slf4j
public class HeatingModuleConfiguration {
  private final CompareProcessor compareProcessor;
  private final MonitoringService monitoringService;
  private final Esp32ModuleConfig esp32ModuleConfig;
  private final Esp32ModuleProperties esp32ModuleProperties;
  private HeatingModuleDao heatingModuleDao;

  @PostConstruct
  void postConstruct() {
    heatingModuleDao =
        HeatingModuleDao.builder()
            .moduleName(Esp32ModuleProperties.MODULE_TYPE)
            .buffers(getBuffers())
            .devices(getDevices())
            .zoneDaoHashMap(createZones())
            .build();
    monitoringService.setModuleDaoObject(heatingModuleDao);
    setCompareProperties();
  }

  private Devices getDevices() {
    return Devices.builder()
        .tDistributor(new Ds18b20Result())
        .tDistributorGroundFloor(new Ds18b20Result())
        .tDistributorFirstFloor(new Ds18b20Result())
        .tSupply(new Ds18b20Result())
        .tReturn(new Ds18b20Result())
        .tFireplace(new Ds18b20Result())
        .tGroundSourceSupply(new Ds18b20Result())
        .tGroundSourceReturn(new Ds18b20Result())
        .build();
  }

  private Buffers getBuffers() {
    return Buffers.builder()
        .tHeatingLow(new Ds18b20Result())
        .tHeatingMid(new Ds18b20Result())
        .tHeatingHigh(new Ds18b20Result())
        .tWaterLow(new Ds18b20Result())
        .tWaterMid(new Ds18b20Result())
        .tWaterHigh(new Ds18b20Result())
        .build();
  }

  private HashMap<ZoneName, ZoneDao> createZones() {
    final HashMap<ZoneName, ZoneDao> zoneDaoHashMap = new HashMap<>();
    zoneDaoHashMap.put(SALON, createZone());
    zoneDaoHashMap.put(PRALNIA, createZone());
    zoneDaoHashMap.put(LAZ_DOL, createZone());
    zoneDaoHashMap.put(RODZICE, createZone());
    zoneDaoHashMap.put(NATALIA, createZone());
    zoneDaoHashMap.put(KAROLINA, createZone());
    zoneDaoHashMap.put(LAZ_GORA, createZone());
    return zoneDaoHashMap;
  }

  private ZoneDao createZone() {
    return ZoneDao.builder().operation(Operation.STANDBY).enabled(false).build();
  }

  private void setCompareProperties() {
    compareProcessor.addMap("error", BooleanCompareProperties.builder().saveEnabled(true).build());
    compareProcessor.addMap(
        "errorPendingAcknowledge", BooleanCompareProperties.builder().saveEnabled(true).build());
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "buffers.tHeatingLow");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "buffers.tHeatingMid");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "buffers.tHeatingHigh");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "buffers.tWaterLow");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "buffers.tWaterMid");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "buffers.tWaterHigh");

    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tSupply");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tReturn");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tDistributor");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tDistributorGroundFloor");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tDistributorFirstFloor");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tGroundSourceSupply");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tGroundSourceReturn");
    Ds18b20DefaultProperties.setDefaultProperties(compareProcessor, "devices.tFireplace");
  }
}
