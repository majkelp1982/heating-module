package pl.smarthouse.heatingmodule.chain;

import static org.springframework.data.util.Predicates.negate;
import static pl.smarthouse.heatingmodule.properties.Ds18b20SensorsProperties.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.smarthouse.heatingmodule.configurations.Esp32ModuleConfig;
import pl.smarthouse.heatingmodule.service.HeatingModuleService;
import pl.smarthouse.smartchain.model.core.Chain;
import pl.smarthouse.smartchain.model.core.Step;
import pl.smarthouse.smartchain.service.ChainService;
import pl.smarthouse.smartchain.utils.PredicateUtils;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20CommandType;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20Utils;

@Service
public class BuffersDs18b20Chain {
  private final HeatingModuleService heatingModuleService;
  private final Ds18b20 buffers;

  public BuffersDs18b20Chain(
      @Autowired final HeatingModuleService heatingModuleService,
      @Autowired final ChainService chainService,
      @Autowired final Esp32ModuleConfig esp32ModuleConfig) {
    this.heatingModuleService = heatingModuleService;
    buffers = (Ds18b20) esp32ModuleConfig.getConfiguration().getActorMap().getActor(BUFFERS);
    final Chain chain = createChain();
    chainService.addChain(chain);
  }

  private Chain createChain() {
    final Chain chain = new Chain("Read buffers DS18B20 sensors");
    // Wait 30 seconds and read values from each sensor type DS18B20
    chain.addStep(createStep1());
    // Wait until command read successful and set command to NO_ACTION for all
    chain.addStep(createStep2());
    return chain;
  }

  private Step createStep1() {

    return Step.builder()
        .stepDescription("Read values from each buffer sensor type DS18B20")
        .conditionDescription("Waiting 30 seconds")
        .condition(PredicateUtils.delaySeconds(30))
        .action(createActionStep1())
        .build();
  }

  private Runnable createActionStep1() {

    return () -> {
      buffers.getCommandSet().setCommandType(Ds18b20CommandType.READ);
      buffers
          .getCommandSet()
          .setValue(
              Ds18b20Utils.getDs18b20Command(
                  T_HEATING_LOW,
                  T_HEATING_MID,
                  T_HEATING_HIGH,
                  T_WATER_LOW,
                  T_WATER_MID,
                  T_WATER_HIGH));
    };
  }

  private Step createStep2() {

    return Step.builder()
        .stepDescription("Set DS18B20 commands to NO_ACTION")
        .conditionDescription("Wait until command read successful")
        .condition(
            PredicateUtils.isResponseUpdated(buffers)
                .and(negate(PredicateUtils.isErrorOnDs18b20Group(buffers.getResponse()))))
        .action(createActionStep2())
        .build();
  }

  private Runnable createActionStep2() {

    return () -> {
      heatingModuleService.setTHeatingLow(buffers.getResponse().getSensorResult(T_HEATING_LOW));
      heatingModuleService.setTHeatingMid(buffers.getResponse().getSensorResult(T_HEATING_MID));
      heatingModuleService.setTHeatingHigh(buffers.getResponse().getSensorResult(T_HEATING_HIGH));
      heatingModuleService.setTWaterLow(buffers.getResponse().getSensorResult(T_WATER_LOW));
      heatingModuleService.setTWaterMid(buffers.getResponse().getSensorResult(T_WATER_MID));
      heatingModuleService.setTWaterHigh(buffers.getResponse().getSensorResult(T_WATER_HIGH));
      buffers.getCommandSet().setCommandType(Ds18b20CommandType.NO_ACTION);
    };
  }
}
