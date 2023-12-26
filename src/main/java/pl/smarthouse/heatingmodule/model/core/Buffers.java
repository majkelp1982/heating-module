package pl.smarthouse.heatingmodule.model.core;

import lombok.Builder;
import lombok.Data;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20Result;

@Data
@Builder
public class Buffers {
  private Ds18b20Result tHeatingLow;
  private Ds18b20Result tHeatingMid;
  private Ds18b20Result tHeatingHigh;
  private Ds18b20Result tWaterLow;
  private Ds18b20Result tWaterMid;
  private Ds18b20Result tWaterHigh;
}
