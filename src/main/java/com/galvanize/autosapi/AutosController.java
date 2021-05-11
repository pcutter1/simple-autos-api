package com.galvanize.autosapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping("/api/autos")
    public ResponseEntity<AutosList> getAutos() {
        AutosList autosList = autosService.getAutos();
        return autosList.list.size() == 0 ? ResponseEntity.noContent().build() :
            ResponseEntity.ok(autosList);
    }




}
