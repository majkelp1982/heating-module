package pl.smarthouse.heatingmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.smarthouse.heatingmodule.service.HeatingModuleService;
import pl.smarthouse.sharedobjects.dto.heating.HeatingModuleDto;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class HeatingModuleController {
  private final HeatingModuleService heatingModuleService;

  @GetMapping("/heating")
  public Mono<HeatingModuleDto> getHeatingModule() {
    return Mono.just(heatingModuleService.getHeatingModule());
  }
}
