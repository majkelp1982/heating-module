package pl.smarthouse.heatingmodule.model.core;

import lombok.Builder;
import lombok.Data;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20Result;

@Data
@Builder
public class Devices {
  private Ds18b20Result tSupply;
  private Ds18b20Result tReturn;
  private Ds18b20Result tDistributor;
  private Ds18b20Result tDistributorGroundFloor;
  private Ds18b20Result tDistributorFirstFloor;
  private Ds18b20Result tGroundSourceSupply;
  private Ds18b20Result tGroundSourceReturn;
  private Ds18b20Result tFireplace;
}
