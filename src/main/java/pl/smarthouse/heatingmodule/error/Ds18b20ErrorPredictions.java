package pl.smarthouse.heatingmodule.error;

import static pl.smarthouse.heatingmodule.properties.Ds18b20SensorsProperties.*;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import pl.smarthouse.heatingmodule.service.HeatingModuleService;
import pl.smarthouse.smartmodule.utils.errorpredictions.Ds18b20ErrorPredictionsUtils;
import pl.smarthouse.smartmonitoring.service.ErrorHandlingService;

@Configuration
@RequiredArgsConstructor
public class Ds18b20ErrorPredictions {

  private final HeatingModuleService heatingModuleService;
  private final ErrorHandlingService errorHandlingService;

  @PostConstruct
  public void postConstructor() {
    // Buffers
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_HEATING_LOW, heatingModuleService::getTHeatingLow);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_HEATING_MID, heatingModuleService::getTHeatingMid);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_HEATING_HIGH, heatingModuleService::getTHeatingHigh);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_WATER_LOW, heatingModuleService::getTWaterLow);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_WATER_MID, heatingModuleService::getTWaterMid);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_WATER_HIGH, heatingModuleService::getTWaterHigh);

    // Devices
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_SUPPLY, heatingModuleService::getTSupply);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_RETURN, heatingModuleService::getTReturn);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_DISTRIBUTOR, heatingModuleService::getTDistributor);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService,
        T_DISTRIBUTOR_GROUND_FLOOR_RETURN,
        heatingModuleService::getTDistributorFirstFloor);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService,
        T_DISTRIBUTOR_FIRST_FLOOR_RETURN,
        heatingModuleService::getTDistributorFirstFloor);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_GROUND_SOURCE_SUPPLY, heatingModuleService::getTGroundSourceSupply);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_GROUND_SOURCE_RETURN, heatingModuleService::getTGroundSourceReturn);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, T_FIREPLACE, heatingModuleService::getTFireplace);
  }
}
