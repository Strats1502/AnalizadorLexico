package Analizador;
/**
Authors: Juan José Estrada Valtierra
         Carlos Luna Castillo
Contact: juanjoseesva@gmail.com
         carl_dharius@hotmail.com
Created at: 20 October 2017
Last modification: 20 October 2017
*/

public class Type {
  private String name;
  private int value;

  public Type(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public int getValue() {
    return this.value;
  }

}
