package pl.smarthouse.heatingmodule.model.dao;

import java.util.HashMap;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import pl.smarthouse.heatingmodule.model.core.Buffers;
import pl.smarthouse.heatingmodule.model.core.Devices;
import pl.smarthouse.sharedobjects.dao.ModuleDao;
import pl.smarthouse.sharedobjects.enums.ZoneName;

@Data
@SuperBuilder
public class HeatingModuleDao extends ModuleDao {
  @Transient private final HashMap<ZoneName, ZoneDao> zoneDaoHashMap;
  private Buffers buffers;
  private Devices devices;
  
  
}
