package pl.smarthouse.heatingmodule.properties;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Esp32ModuleProperties {

  // Module specific
  public static final String FIRMWARE = "20231202.08";
  public static final String VERSION = "20231202.09";
  public static final String MAC_ADDRESS = "XX:XX:XX:XX:XX:XX";
  public static final String MODULE_TYPE = "HEATING";
}
