package pl.smarthouse.heatingmodule.error;

import static pl.smarthouse.fireplacemodule.properties.Ds18b20SensorsProperties.*;
import static pl.smarthouse.fireplacemodule.properties.Ds18b20SensorsProperties.THERMOMETERS_CHIMNEY;
import static pl.smarthouse.fireplacemodule.properties.Ds18b20SensorsProperties.THERMOMETERS_WATER_OUT;
import static pl.smarthouse.fireplacemodule.properties.Ds18b20SensorsProperties.THERMOMETER_WATER_IN;

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
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, THERMOMETER_WATER_IN, heatingModuleService::getWaterInSensor);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, THERMOMETERS_WATER_OUT, heatingModuleService::getWaterOutSensor);
    Ds18b20ErrorPredictionsUtils.setDs180b20SensorsErrorPredictions(
        errorHandlingService, THERMOMETERS_CHIMNEY, heatingModuleService::getChimneySensor);
  }
}
