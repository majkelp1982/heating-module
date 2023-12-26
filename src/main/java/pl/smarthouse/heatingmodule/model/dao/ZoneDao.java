package pl.smarthouse.heatingmodule.model.dao;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.smarthouse.sharedobjects.dto.ventilation.enums.State;
import pl.smarthouse.sharedobjects.enums.Operation;

@Getter
@Setter
@Builder
public class ZoneDao {
  boolean enabled;
  @NonNull private LocalDateTime lastUpdate;
  private Operation operation;
  private State heatingCircuit;

  public void setOperation(final Operation operation) {
    this.operation = operation;
    lastUpdate = LocalDateTime.now();
  }
}
