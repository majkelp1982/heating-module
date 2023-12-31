package pl.smarthouse.heatingmodule.configurations;

import static pl.smarthouse.heatingmodule.properties.ActorsProperties.*;
import static pl.smarthouse.heatingmodule.properties.Ds18b20SensorsProperties.*;
import static pl.smarthouse.heatingmodule.properties.Esp32ModuleProperties.*;

import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import pl.smarthouse.sharedobjects.enums.ZoneName;
import pl.smarthouse.smartmodule.model.actors.actor.ActorMap;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20CompFactor;
import pl.smarthouse.smartmodule.model.actors.type.pin.Pin;
import pl.smarthouse.smartmodule.model.actors.type.pin.PinMode;
import pl.smarthouse.smartmodule.services.ManagerService;
import pl.smarthouse.smartmodule.services.ModuleService;

@Configuration
@RequiredArgsConstructor
@Getter
public class Esp32ModuleConfig {
  private final ModuleService moduleService;
  private final ManagerService managerService;

  // Module specific
  private pl.smarthouse.smartmodule.model.configuration.Configuration configuration;

  @PostConstruct
  public void postConstruct() {
    configuration =
        new pl.smarthouse.smartmodule.model.configuration.Configuration(
            MODULE_TYPE, FIRMWARE, VERSION, MAC_ADDRESS, createActors());
    moduleService.setConfiguration(configuration);
    managerService.setConfiguration(configuration);
  }

  private ActorMap createActors() {
    final ActorMap actorMap = new ActorMap();
    // DS18b20 sensors
    actorMap.putActor(createBuffersDs18b20());
    actorMap.putActor(createDevicesDs18b20());

    // Circuits valves
    actorMap.putActor(createCircuitValve(ZoneName.SALON, SALON_PIN));
    actorMap.putActor(createCircuitValve(ZoneName.PRALNIA, PRALNIA_PIN));
    actorMap.putActor(createCircuitValve(ZoneName.LAZ_DOL, LAZ_DOL_PIN));
    actorMap.putActor(createCircuitValve(ZoneName.RODZICE, RODZICE_PIN));
    actorMap.putActor(createCircuitValve(ZoneName.NATALIA, NATALIA_PIN));
    actorMap.putActor(createCircuitValve(ZoneName.KAROLINA, KAROLINA_PIN));
    actorMap.putActor(createCircuitValve(ZoneName.LAZ_GORA, LAZ_GORA_PIN));

    // Other actors
    actorMap.putActor(
        new Pin(HEAT_PUMP, HEAT_PUMP_PIN, PinMode.OUTPUT, PIN_DEFAULT_STATE, PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            WATER_OVERHEAT,
            WATER_OVERHEAT_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            PUMP_IN_HOUSE,
            PUMP_IN_HOUSE_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            PUMP_GROUND_SOURCE,
            PUMP_GROUND_SOURCE_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            VALVE_3WAY_HEATING,
            VALVE_3WAY_HEATING_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            VALVE_3WAY_WATER,
            VALVE_3WAY_WATER_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            VALVE_BYPASS_OPEN,
            VALVE_BYPASS_OPEN_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    actorMap.putActor(
        new Pin(
            VALVE_BYPASS_CLOSE,
            VALVE_BYPASS_CLOSE_PIN,
            PinMode.OUTPUT,
            PIN_DEFAULT_STATE,
            PIN_DEFAULT_ENABLED));
    return actorMap;
  }

  private Pin createCircuitValve(final ZoneName zoneName, final int pin) {
    return new Pin(zoneName.name(), pin, PinMode.OUTPUT, PIN_DEFAULT_STATE, PIN_DEFAULT_ENABLED);
  }

  private Ds18b20 createBuffersDs18b20() {
    final Ds18b20 ds18b20 = new Ds18b20(BUFFERS, BUFFERS_DS18B20_PIN);
    final Map<String, Ds18b20CompFactor> comparator = ds18b20.getDs18b20CompFactorMap();
    comparator.put(
        T_HEATING_LOW,
        Ds18b20CompFactor.builder()
            .gradient(T_HEATING_LOW_GRADIENT)
            .intercept(T_HEATING_LOW_INTERCEPT)
            .build());
    comparator.put(
        T_HEATING_MID,
        Ds18b20CompFactor.builder()
            .gradient(T_HEATING_MID_GRADIENT)
            .intercept(T_HEATING_MID_INTERCEPT)
            .build());
    comparator.put(
        T_HEATING_HIGH,
        Ds18b20CompFactor.builder()
            .gradient(T_HEATING_HIGH_GRADIENT)
            .intercept(T_HEATING_HIGH_INTERCEPT)
            .build());
    comparator.put(
        T_WATER_LOW,
        Ds18b20CompFactor.builder()
            .gradient(T_WATER_LOW_GRADIENT)
            .intercept(T_WATER_LOW_INTERCEPT)
            .build());
    comparator.put(
        T_WATER_MID,
        Ds18b20CompFactor.builder()
            .gradient(T_WATER_MID_GRADIENT)
            .intercept(T_WATER_MID_INTERCEPT)
            .build());
    comparator.put(
        T_WATER_HIGH,
        Ds18b20CompFactor.builder()
            .gradient(T_WATER_HIGH_GRADIENT)
            .intercept(T_WATER_HIGH_INTERCEPT)
            .build());
    return ds18b20;
  }

  private Ds18b20 createDevicesDs18b20() {
    final Ds18b20 ds18b20 = new Ds18b20(DEVICES, DEVICES_DS18B20_PIN);
    final Map<String, Ds18b20CompFactor> comparator = ds18b20.getDs18b20CompFactorMap();
    comparator.put(
        T_SUPPLY,
        Ds18b20CompFactor.builder()
            .gradient(T_SUPPLY_GRADIENT)
            .intercept(T_SUPPLY_INTERCEPT)
            .build());
    comparator.put(
        T_RETURN,
        Ds18b20CompFactor.builder()
            .gradient(T_RETURN_GRADIENT)
            .intercept(T_RETURN_INTERCEPT)
            .build());
    comparator.put(
        T_DISTRIBUTOR,
        Ds18b20CompFactor.builder()
            .gradient(T_DISTRIBUTOR_GRADIENT)
            .intercept(T_DISTRIBUTOR_INTERCEPT)
            .build());
    comparator.put(
        T_DISTRIBUTOR_GROUND_FLOOR_RETURN,
        Ds18b20CompFactor.builder()
            .gradient(T_DISTRIBUTOR_GROUND_FLOOR_RETURN_GRADIENT)
            .intercept(T_DISTRIBUTOR_GROUND_FLOOR_RETURN_INTERCEPT)
            .build());
    comparator.put(
        T_DISTRIBUTOR_FIRST_FLOOR_RETURN,
        Ds18b20CompFactor.builder()
            .gradient(T_DISTRIBUTOR_FIRST_FLOOR_RETURN_GRADIENT)
            .intercept(T_DISTRIBUTOR_FIRST_FLOOR_RETURN_INTERCEPT)
            .build());
    comparator.put(
        T_GROUND_SOURCE_SUPPLY,
        Ds18b20CompFactor.builder()
            .gradient(T_GROUND_SOURCE_SUPPLY_GRADIENT)
            .intercept(T_GROUND_SOURCE_SUPPLY_INTERCEPT)
            .build());
    comparator.put(
        T_GROUND_SOURCE_RETURN,
        Ds18b20CompFactor.builder()
            .gradient(T_GROUND_SOURCE_RETURN_GRADIENT)
            .intercept(T_GROUND_SOURCE_RETURN_INTERCEPT)
            .build());
    comparator.put(
        T_FIREPLACE,
        Ds18b20CompFactor.builder()
            .gradient(T_FIREPLACE_GRADIENT)
            .intercept(T_FIREPLACE_INTERCEPT)
            .build());
    return ds18b20;
  }
}
