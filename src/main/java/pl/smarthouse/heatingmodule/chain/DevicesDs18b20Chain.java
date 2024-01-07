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
public class DevicesDs18b20Chain {
  private final HeatingModuleService heatingModuleService;
  private final Ds18b20 devices;

  public DevicesDs18b20Chain(
      @Autowired final HeatingModuleService heatingModuleService,
      @Autowired final ChainService chainService,
      @Autowired final Esp32ModuleConfig esp32ModuleConfig) {
    this.heatingModuleService = heatingModuleService;
    devices = (Ds18b20) esp32ModuleConfig.getConfiguration().getActorMap().getActor(DEVICES);
    final Chain chain = createChain();
    chainService.addChain(chain);
  }

  private Chain createChain() {
    final Chain chain = new Chain("Read devices DS18B20 sensors");
    // Wait 30 seconds and read values from each sensor type DS18B20
    chain.addStep(createStep1());
    // Wait until command read successful and set command to NO_ACTION for all
    chain.addStep(createStep2());
    return chain;
  }

  private Step createStep1() {

    return Step.builder()
        .stepDescription("Read values from each devices sensor type DS18B20")
        .conditionDescription("Waiting 30 seconds")
        .condition(PredicateUtils.delaySeconds(30))
        .action(createActionStep1())
        .build();
  }

  private Runnable createActionStep1() {

    return () -> {
      devices.getCommandSet().setCommandType(Ds18b20CommandType.READ);
      devices
          .getCommandSet()
          .setValue(
              Ds18b20Utils.getDs18b20Command(
                  T_SUPPLY,
                  T_RETURN,
                  T_DISTRIBUTOR,
                  T_DISTRIBUTOR_GROUND_FLOOR_RETURN,
                  T_DISTRIBUTOR_FIRST_FLOOR_RETURN,
                  T_GROUND_SOURCE_SUPPLY,
                  T_GROUND_SOURCE_RETURN,
                  T_FIREPLACE));
    };
  }

  private Step createStep2() {

    return Step.builder()
        .stepDescription("Set DS18B20 commands to NO_ACTION")
        .conditionDescription("Wait until command read successful")
        .condition(
            PredicateUtils.isResponseUpdated(devices)
                .and(negate(PredicateUtils.isErrorOnDs18b20Group(devices.getResponse()))))
        .action(createActionStep2())
        .build();
  }

  private Runnable createActionStep2() {

    return () -> {
      heatingModuleService.setTSupply(devices.getResponse().getSensorResult(T_SUPPLY));
      heatingModuleService.setTReturn(devices.getResponse().getSensorResult(T_RETURN));
      heatingModuleService.setTDistributor(devices.getResponse().getSensorResult(T_DISTRIBUTOR));
      heatingModuleService.setTDistributorGroundFloorReturn(
          devices.getResponse().getSensorResult(T_DISTRIBUTOR_GROUND_FLOOR_RETURN));
      heatingModuleService.setTDistributorFirstFloorReturn(
          devices.getResponse().getSensorResult(T_DISTRIBUTOR_FIRST_FLOOR_RETURN));
      heatingModuleService.setTGroundSourceSupply(
          devices.getResponse().getSensorResult(T_GROUND_SOURCE_SUPPLY));
      heatingModuleService.setTGroundSourceReturn(
          devices.getResponse().getSensorResult(T_GROUND_SOURCE_RETURN));
      heatingModuleService.setTFireplace(devices.getResponse().getSensorResult(T_FIREPLACE));
      devices.getCommandSet().setCommandType(Ds18b20CommandType.NO_ACTION);
    };
  }
}
