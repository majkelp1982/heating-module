package pl.smarthouse.heatingmodule.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.smarthouse.heatingmodule.configurations.HeatingModuleConfiguration;

@Service
@RequiredArgsConstructor
public class HeatingModuleService {
  private final HeatingModuleConfiguration heatingModuleConfiguration;
  private final ModelMapper modelMapper = new ModelMapper();
}
