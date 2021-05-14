package com.galvanize.autosapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class AutosList {

  private List<Auto> autos;

  public AutosList() {
    this.autos = new ArrayList<>();
  }

  public AutosList(List<Auto> autos) {
    this.autos = autos;
  }

  public List<Auto> getAutos() {
    return autos;
  }

  public void setAutos(List<Auto> autos) {
    this.autos = autos;
  }

  @JsonIgnore
  public boolean isEmpty() {
    return autos.isEmpty();
  }

  @Override
  public String toString() {
    return "AutosList{" +
        "autos=" + autos +
        '}';
  }

}
