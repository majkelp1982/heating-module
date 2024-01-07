package pl.smarthouse.heatingmodule.properties;

import pl.smarthouse.smartmodule.model.actors.type.pin.PinState;

public class ActorsProperties {
  // Pin default state
  public static final PinState PIN_DEFAULT_STATE = PinState.HIGH;
  public static final boolean PIN_DEFAULT_ENABLED = true;

  // Zones
  public static final int SALON_PIN = 15;
  public static final int PRALNIA_PIN = 2;
  public static final int LAZ_DOL_PIN = 4;
  public static final int RODZICE_PIN = 16;
  public static final int NATALIA_PIN = 17;
  public static final int KAROLINA_PIN = 5;
  public static final int LAZ_GORA_PIN = 18;

  // Devices pin
  public static final String HEAT_PUMP = "heatPump";
  public static final int HEAT_PUMP_PIN = 32;
  public static final String WATER_OVERHEAT = "waterOverheat";
  public static final int WATER_OVERHEAT_PIN = 33;

  // Circulation pumps
  public static final String PUMP_IN_HOUSE = "pumpInHouse";
  public static final int PUMP_IN_HOUSE_PIN = 26;
  public static final String PUMP_GROUND_SOURCE = "pumpGroundSource";
  public static final int PUMP_GROUND_SOURCE_PIN = 25;

  // Valves
  public static final String VALVE_3WAY_HEATING = "valve3WayHeating";
  public static final int VALVE_3WAY_HEATING_PIN = 13;
  public static final String VALVE_3WAY_WATER = "valve3WayWater";
  public static final int VALVE_3WAY_WATER_PIN = 23;
  public static final String VALVE_BYPASS_OPEN = "bypassOpen";
  public static final int VALVE_BYPASS_OPEN_PIN = 27;
  public static final String VALVE_BYPASS_CLOSE = "bypassClose";
  public static final int VALVE_BYPASS_CLOSE_PIN = 14;
}
