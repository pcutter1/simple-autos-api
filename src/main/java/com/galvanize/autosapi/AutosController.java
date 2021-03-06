package com.galvanize.autosapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autos")
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping
    public ResponseEntity<AutosList> getAutos(@RequestParam(required = false) String color,
        @RequestParam(required = false) String make) {
        AutosList autosList;
        if(color == null && make == null) {
            autosList = autosService.getAutos();
        } else {
            autosList = autosService.getAutos(color, make);
        }
        if (autosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autosList);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Auto> getAutoByVin(@PathVariable String vin) {
        Auto auto = autosService.getAutoByVin(vin);
        return auto == null ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(auto);
    }

    @PostMapping
    public Auto addAuto(@RequestBody Auto auto) {
        return autosService.addAuto(auto);
    }

    @PatchMapping("/{vin}")
    public ResponseEntity<Auto> updateAuto(@PathVariable String vin, @RequestBody UpdateRequest updateRequest) {
        Auto auto = autosService.getAutoByVin(vin);
        if(auto == null){
            return ResponseEntity.noContent().build();
        }
        auto.setOwner(updateRequest.getOwner());
        auto.setColor(updateRequest.getColor());
        auto = autosService.saveAuto(auto);
        return ResponseEntity.ok(auto);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<String> deleteAuto(@PathVariable String vin) {
        Auto auto = autosService.getAutoByVin(vin);
        if (auto != null){
            autosService.deleteAuto(vin);
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }


//        return getAutoByVin(vin) == null ? ResponseEntity.noContent().build() : ResponseEntity.accepted().build();
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void InvalidAutoExceptionHandler(InvalidAutoException invalidAutoException) {

    }



}
