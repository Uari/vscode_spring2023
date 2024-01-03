package pk.gym.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GymController {
  Logger logger = LoggerFactory.getLogger(GymController.class);
  @GetMapping("/gym/gymList")
  public String gymList(){
    logger.info("gymList");
    return "gym";
  }
}
