package Analizador;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Menu {
  private static Menu instance = null;
  Scanner keyboard;
  String entrada;

  Queue<Character> characters = new LinkedList<>();

  List<Token> tokens = new ArrayList<>();

  Type typeAlum = new Type("Identifier", 256);
  Type typeNumber = new Type("Number", 257);
  Type typeFloat = new Type("Float", 258);
  Type typeChar = new Type("Char", 259);

  List<Character> alphabeth = Arrays.asList('$', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', 'á', 'ć', 'é', 'ǵ', 'í', 'ḱ', 'ĺ', 'ḿ', 'ń', 'ó', 'ṕ', 'ŕ',
            'ś', 'ú', 'ǘ', 'ý', 'ź');

  List<Character> numbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

  List<Character> operators = Arrays.asList('=', '+', '-', '*', '/', '%', '^');

  List<Character> symbols = Arrays.asList('!', '"', '#', '%', '&', '/', '(', ')', '=', '?', '\'', '\\', '¿', '¡', '´', '¨', '*', '+', '~', '{',
            '}', '[', ']', '^', '`', ',', ';', '.', ':', '-', '_', '|', '°', '¬');

  String auxiliar = "";

  private Menu() {
    entrada();
  }

  private void entrada() {
    keyboard = new Scanner(System.in);
    System.out.println("Ingresa la linea");
    entrada = keyboard.nextLine();

    entrada = entrada.replaceAll(" ", "");

    for (int i = 0; i < entrada.length() ; i++) {
      characters.add(entrada.charAt(i));
    }

    do {
      makeTokens();
    } while (!characters.isEmpty());

    for (int i = 0; i < tokens.size(); i++) {
      System.out.println(tokens.get(i).getLexem() + " " + tokens.get(i).getType().getName());
    }

  }

  private void makeTokens() {
    char actualChar = characters.peek();

    //Carácter leido es parte del alfabeto
    if (alphabeth.contains(actualChar)) {
      //si ya tiene algo la variable
      if (auxiliar.length() > 0) {
        //Si inicia con una letra del alfabeto manda error, solo los numeros pueden tener punto
          if (numbers.contains(auxiliar.charAt(0))) {
            auxiliar = auxiliar + actualChar;
            System.out.println("La cadena: " + auxiliar + " no es válida");
            System.out.println("No se permite la creación de identificadores con un número al inicio");
            characters.clear();
          } else {
            //Si no tiene ningún error lo agrega al String y quita el elemento de la cola
              auxiliar = auxiliar + actualChar;
              characters.remove();
          }
      } else {
        //Si no tiene nada la variable
        auxiliar = auxiliar + actualChar;
        characters.remove();
      }
    } else if (symbols.contains(actualChar) && auxiliar.length() > 0) {
      //Si el carácter leído pertenece a los simbolos y es igual a un punto
      if (actualChar == '.') {
        auxiliar = auxiliar + actualChar;
        //Si la palabra hasta ahora inicia con una letra manda error
        if (alphabeth.contains(auxiliar.charAt(0))) {
          System.out.println("La cadena: " + auxiliar + " no es permitida");
          System.out.println("No se permite el uso de puntos en identificadores");
          characters.clear();
        } else {
          //Si no, remueve el elemento de la cola y pasa al siguiente
          characters.remove();
        }
      } else {
        //Si es otro caracter que no sea un '.', genera el token
        Token token = new Token();
        token.setLexem(auxiliar);

        //Dicierne entre los tipos
        if (alphabeth.contains(auxiliar.charAt(0))) {
          //Si inicia con letra es alfanumerico
          token.setType(typeAlum);
        } else if (auxiliar.contains(".")) {
          //Si tiene un punto es flotante
          token.setType(typeFloat);
        } else {
          //Si solo tiene números es de tipo númerico
          token.setType(typeNumber);
        }

        //Agrega el token a la lista de tokens
        tokens.add(token);

        //Reinicia la variable
        auxiliar = "";
      }
    } else if (symbols.contains(actualChar) && auxiliar.length() == 0) {
      auxiliar = auxiliar + actualChar;
      //Si el carácter actual es un simbolo y aún no se genera ningún token manda error
      if (tokens.isEmpty()) {
        System.out.println("La cadena: " + auxiliar + " no es valida");
        System.out.println("No se permite la inserción de un carácter al inicio de la expresión");
        characters.clear();
      } else if (actualChar == '.'){
        //Si ya tienen tokens registrados y la entrada es '.', dicierne si es identificador o de tipo numerico
        if (alphabeth.contains(auxiliar.charAt(0))) {
          System.out.println("La cadena: " + auxiliar + " no es valida");
          System.out.println("No se permite el uso de un punto en un identificador");
          characters.clear();
        } else {
          characters.remove();
        }
      } else {
        //Si es otro simbolo que no sea '.', genera el token
        Token token = new Token();
        token.setLexem(auxiliar);
        token.setType(typeChar);

        tokens.add(token);

        auxiliar = "";

        characters.remove();
      }

    } else if (numbers.contains(actualChar) && auxiliar.length() == 0) {
      //Si es de tipo numerico lo agrega al string y elimina de la cola
      auxiliar = auxiliar + actualChar;
      characters.remove();
    } else if (numbers.contains(actualChar) && auxiliar.length() > 0) {
      //Si es de tipo numerico lo agrega al string y elimina de la cola
      auxiliar = auxiliar + actualChar;
      characters.remove();
    }

  }

//Genera una instancia unica de la clase, evita que se creen mas de un objeto
  public static Menu getInstance() {
    if (instance == null) {
      instance = new Menu();
      return instance;
    } else {
      return null;
    }
  }


}
