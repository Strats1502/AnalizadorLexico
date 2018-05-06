package Analizador;
/**
Authors: Juan José Estrada Valtierra
         Carlos Luna Castillo
Contact: juanjoseesva@gmail.com
         carl_dharius@hotmail.com
Created at: 20 October 2017
Last modification: 20 October 2017
*/

public class Token {
  private String lexem;
  private Type type;

  public void setLexem(String lexem) {
    this.lexem = lexem;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public String getLexem() {
    return this.lexem;
  }

  public Type getType() {
    return this.type;
  }

}
