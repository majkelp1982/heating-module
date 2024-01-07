package pl.smarthouse.heatingmodule.properties;

public class Ds18b20SensorsProperties {
  public static final String BUFFERS = "buffers";
  public static final int BUFFERS_DS18B20_PIN = 22;
  public static final String DEVICES = "devices";
  public static final int DEVICES_DS18B20_PIN = 21;

  // Buffers
  public static final String T_HEATING_LOW = "40-2-0-7-47-39-1-204";
  public static final float T_HEATING_LOW_GRADIENT = 0.9f;
  public static final float T_HEATING_LOW_INTERCEPT = 7.7f;
  public static final String T_HEATING_MID = "40-7-0-7-205-124-1-251";
  public static final float T_HEATING_MID_GRADIENT = 0.935f;
  public static final float T_HEATING_MID_INTERCEPT = 4.7f;
  public static final String T_HEATING_HIGH = "40-12-1-7-112-170-1-144";
  public static final float T_HEATING_HIGH_GRADIENT = 0.9f;
  public static final float T_HEATING_HIGH_INTERCEPT = 6.2f;
  public static final String T_WATER_LOW = "40-12-1-7-67-166-1-231";
  public static final float T_WATER_LOW_GRADIENT = 1.005f;
  public static final float T_WATER_LOW_INTERCEPT = 3.5f;
  public static final String T_WATER_MID = "40-12-1-7-194-103-1-94";
  public static final float T_WATER_MID_GRADIENT = 0.97f;
  public static final float T_WATER_MID_INTERCEPT = 3.5f;
  public static final String T_WATER_HIGH = "40-12-1-7-225-170-1-19";
  public static final float T_WATER_HIGH_GRADIENT = 0.99f;
  public static final float T_WATER_HIGH_INTERCEPT = 3.5f;

  // Others
  public static final String T_SUPPLY = "40-12-1-7-159-197-1-74";
  public static final float T_SUPPLY_GRADIENT = 0.92f;
  public static final float T_SUPPLY_INTERCEPT = 5f;
  public static final String T_RETURN = "40-255-161-100-96-23-5-48";
  public static final float T_RETURN_GRADIENT = 0.955f;
  public static final float T_RETURN_INTERCEPT = 2.7f;

  // Distributor
  public static final String T_DISTRIBUTOR = "40-255-154-228-161-22-3-252";
  public static final float T_DISTRIBUTOR_GRADIENT = 0.96f;
  public static final float T_DISTRIBUTOR_INTERCEPT = 2.2f;
  public static final String T_DISTRIBUTOR_GROUND_FLOOR_RETURN = "1-0-0-0-0-0-0-0";
  public static final float T_DISTRIBUTOR_GROUND_FLOOR_RETURN_GRADIENT = 0.0f;
  public static final float T_DISTRIBUTOR_GROUND_FLOOR_RETURN_INTERCEPT = 0.0f;
  public static final String T_DISTRIBUTOR_FIRST_FLOOR_RETURN = "2-0-0-0-0-0-0-0";
  public static final float T_DISTRIBUTOR_FIRST_FLOOR_RETURN_GRADIENT = 0.f;
  public static final float T_DISTRIBUTOR_FIRST_FLOOR_RETURN_INTERCEPT = 0.0f;

  // Ground source
  public static final String T_GROUND_SOURCE_SUPPLY = "3-0-0-0-0-0-0-0";
  public static final float T_GROUND_SOURCE_SUPPLY_GRADIENT = 0.f;
  public static final float T_GROUND_SOURCE_SUPPLY_INTERCEPT = 0.0f;
  public static final String T_GROUND_SOURCE_RETURN = "4-0-0-0-0-0-0-0";
  public static final float T_GROUND_SOURCE_RETURN_GRADIENT = 0.f;
  public static final float T_GROUND_SOURCE_RETURN_INTERCEPT = 0.0f;

  // Fireplace
  public static final String T_FIREPLACE = "40-255-21-68-96-23-5-16";
  public static final float T_FIREPLACE_GRADIENT = 0.97f;
  public static final float T_FIREPLACE_INTERCEPT = 1.65f;
}
