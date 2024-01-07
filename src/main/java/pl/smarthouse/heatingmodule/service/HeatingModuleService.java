package pl.smarthouse.heatingmodule.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.smarthouse.heatingmodule.configurations.HeatingModuleConfiguration;
import pl.smarthouse.smartmodule.model.actors.type.ds18b20.Ds18b20Result;

@Service
@RequiredArgsConstructor
public class HeatingModuleService {
  private final HeatingModuleConfiguration heatingModuleConfiguration;
  private final ModelMapper modelMapper = new ModelMapper();

  public void setTDistributorGroundFloorReturn(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration
            .getHeatingModuleDao()
            .getDevices()
            .getTDistributorGroundFloor()
            .isError());
    heatingModuleConfiguration
        .getHeatingModuleDao()
        .getDevices()
        .setTDistributorGroundFloor(ds18b20Result);
  }

  public void setTDistributorFirstFloorReturn(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration
            .getHeatingModuleDao()
            .getDevices()
            .getTDistributorFirstFloor()
            .isError());
    heatingModuleConfiguration
        .getHeatingModuleDao()
        .getDevices()
        .setTDistributorFirstFloor(ds18b20Result);
  }

  public Ds18b20Result getTHeatingLow() {
    return heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTHeatingLow();
  }

  public void setTHeatingLow(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTHeatingLow().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getBuffers().setTHeatingLow(ds18b20Result);
  }

  public Ds18b20Result getTHeatingMid() {
    return heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTHeatingMid();
  }

  public void setTHeatingMid(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTHeatingMid().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getBuffers().setTHeatingMid(ds18b20Result);
  }

  public Ds18b20Result getTHeatingHigh() {
    return heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTHeatingHigh();
  }

  public void setTHeatingHigh(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTHeatingHigh().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getBuffers().setTHeatingHigh(ds18b20Result);
  }

  public Ds18b20Result getTWaterLow() {
    return heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTWaterLow();
  }

  public void setTWaterLow(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTWaterLow().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getBuffers().setTWaterLow(ds18b20Result);
  }

  public Ds18b20Result getTWaterMid() {
    return heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTWaterMid();
  }

  public void setTWaterMid(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTWaterMid().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getBuffers().setTWaterMid(ds18b20Result);
  }

  public Ds18b20Result getTWaterHigh() {
    return heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTWaterHigh();
  }

  public void setTWaterHigh(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getBuffers().getTWaterHigh().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getBuffers().setTWaterHigh(ds18b20Result);
  }

  public Ds18b20Result getTSupply() {
    return heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTSupply();
  }

  public void setTSupply(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTSupply().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getDevices().setTSupply(ds18b20Result);
  }

  public Ds18b20Result getTReturn() {
    return heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTReturn();
  }

  public void setTReturn(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTReturn().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getDevices().setTReturn(ds18b20Result);
  }

  public Ds18b20Result getTDistributor() {
    return heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTDistributor();
  }

  public void setTDistributor(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTDistributor().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getDevices().setTDistributor(ds18b20Result);
  }

  public Ds18b20Result getTDistributorGroundFloor() {
    return heatingModuleConfiguration
        .getHeatingModuleDao()
        .getDevices()
        .getTDistributorGroundFloor();
  }

  public Ds18b20Result getTDistributorFirstFloor() {
    return heatingModuleConfiguration
        .getHeatingModuleDao()
        .getDevices()
        .getTDistributorFirstFloor();
  }

  public Ds18b20Result getTGroundSourceSupply() {
    return heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTGroundSourceSupply();
  }

  public void setTGroundSourceSupply(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration
            .getHeatingModuleDao()
            .getDevices()
            .getTGroundSourceSupply()
            .isError());
    heatingModuleConfiguration
        .getHeatingModuleDao()
        .getDevices()
        .setTGroundSourceSupply(ds18b20Result);
  }

  public Ds18b20Result getTGroundSourceReturn() {
    return heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTGroundSourceReturn();
  }

  public void setTGroundSourceReturn(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration
            .getHeatingModuleDao()
            .getDevices()
            .getTGroundSourceReturn()
            .isError());
    heatingModuleConfiguration
        .getHeatingModuleDao()
        .getDevices()
        .setTGroundSourceReturn(ds18b20Result);
  }

  public Ds18b20Result getTFireplace() {
    return heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTFireplace();
  }

  public void setTFireplace(final Ds18b20Result ds18b20Result) {
    ds18b20Result.setError(
        heatingModuleConfiguration.getHeatingModuleDao().getDevices().getTFireplace().isError());
    heatingModuleConfiguration.getHeatingModuleDao().getDevices().setTFireplace(ds18b20Result);
  }
}
