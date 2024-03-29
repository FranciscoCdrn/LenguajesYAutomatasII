package lya;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import lya.TablaSimbolos;

public class AnalisisSemantico {
	ArrayList<String> linea = new ArrayList<String>();
	private HashMap<String, TablaSimbolos> tablaSimbolos = new HashMap<String, TablaSimbolos>();
	private ArrayList<String> listaErroresSemanticos = new ArrayList<String>();
	@SuppressWarnings("unused")
	private ArrayList<String> operadoreslogicos = new ArrayList<>(), operadoresaritmeticos = new ArrayList<>();
	private ArrayList<String> variables = new ArrayList<>();
	private ArrayList<String> operaciones = new ArrayList<String>();
	private ArrayList<Triplo> t = new ArrayList<Triplo>();
	private ArrayList<Ensamblador> e = new ArrayList<Ensamblador>();
	char inicial;

	public AnalisisSemantico(String URL) {
		String codigo = LeeArchivo(URL);
		recorreCodigo(codigo);
		LlenaTabla();
		if (listaErroresSemanticos.isEmpty()) {
			triplos(); // si no hay errores semanticos procedemos a hacer los triplos
			ensamblador();
		}
		for(int i=0;i<t.size();i++) {
			System.out.println(t.get(i).getNum() + " = " + t.get(i).getOp());
		}
		System.out.println(inicial+"="+t.get(t.size()-1).getNum());
		for (String error : listaErroresSemanticos) {
			System.out.println(error);
		}
		/*System.out.println("\t" + "TABLA DE SIMBOLOS" + "\t");
		System.out.println("Variable\t" + "Tipo de dato\t" + "Valor\t" + "Posici�n\t" + "Alcance\t");
		imprimeTabla();*/
		
		System.out.println("----------------------");
		for(int i=0;i<e.size();i++) {
			System.out.println(e.get(i).getNum() + " " + e.get(i).getOp());
		}
	}

	public void triplos() { // Generaci�n de triplos
		String operacion, operacionAux;
		int pos = 0, cont = 1;
		String triplo = "";
		for (int i = 0; i < operaciones.size(); i++) {
			operacion = operaciones.get(i).replaceAll("\\s", ""); // quitamos espacios en blanco
			operacionAux = quitaIgual(operacion);
			System.out.println("operaci�n: " + operacion);
			while (!operacionAux.isEmpty()) {
				//verificamos si contiene un numero negativo
				if((operacionAux.contains("-")) && esOperador(operacionAux.charAt(operacionAux.indexOf("-") - 1))) {
							pos=operacionAux.indexOf("-");
							t.add(new Triplo("T"+cont,Character.toString(operacionAux.charAt(pos))+Character.toString(operacionAux.charAt(pos+1))));
							operacionAux = operacionAux.replace(Character.toString(operacionAux.charAt(pos))+Character.toString(operacionAux.charAt(pos+1)), "");
							cont++;
				}
				// Agregamos primero lo que se encuentra en parentesis
				if (operacionAux.contains("(")) {
					pos = operacionAux.indexOf("(") + 1;
					while (operacionAux.charAt(pos) != ')') {
						triplo = triplo + operacionAux.charAt(pos);
						pos++;
					}
					operacionAux = operacionAux.replace("(", "");
					operacionAux = operacionAux.replace(")", "");
					t.add(new Triplo("T" + cont, triplo));
					cont++;
					operacionAux = operacionAux.replace(triplo, "");
				} else {
					// cuando la longitud es de 1, se realiza la operacion con los dos ultimos
					// triplos
					if (operacionAux.length() == 1) {
						triplo = t.get(cont-3).getNum() + operacionAux.charAt(0) + t.get(cont-2).getNum();
						t.add(new Triplo("T" + cont, triplo));
						operacionAux = operacionAux.replace(Character.toString(operacionAux.charAt(0)), "");
					} else {
						// Cuando la longitud es 2, se realiza la operacion con el operando y el ultimo
						// triplo
						if (operacionAux.length() == 2) {
							triplo =Character.toString(operacionAux.charAt(1))+Character.toString(operacionAux.charAt(0))+ t.get(cont - 2).getNum();
							t.add(new Triplo("T" + cont, triplo));
							operacionAux = operacionAux.replace(Character.toString(operacionAux.charAt(0))
									+ Character.toString(operacionAux.charAt(1)), "");
							cont++;
						} else {
							// Si es un operando se hace un triplo
							if (!esOperador(operacionAux.charAt(operacionAux.length() - 1))) {
								triplo = Character.toString(operacionAux.charAt(operacionAux.length() - 1));
								t.add(new Triplo("T" + cont, triplo));
								operacionAux = operacionAux.replace(triplo, "");
								cont++;
							}else { 
								// Si es un operador y su antecesor es un operador se realiza la operacion con
								// los dos ultimos triplos
								if (esOperador(operacionAux.charAt(operacionAux.length() - 2))) {
									triplo = t.get(cont - 3).getNum() + Character.toString(operacionAux.charAt(operacionAux.length() - 1))
											+ t.get(cont - 2).getNum();
									t.add(new Triplo("T" + cont, triplo));
									operacionAux = operacionAux.substring(0, operacionAux.length() - 1);
									/*operacionAux = operacionAux.replace(
											Character.toString(operacionAux.charAt(operacionAux.length() - 1)), "");*/
									cont++;
								} else {
									// Si es un operando entonces se agrega un nuevo triplo
									if (!esOperador(operacionAux.charAt(operacionAux.length() - 2))) {
										triplo = Character.toString(operacionAux.charAt(0))
												+ Character.toString(operacionAux.charAt(1))
												+ Character.toString(operacionAux.charAt(2));
										t.add(new Triplo("T" + cont, triplo));
										operacionAux = operacionAux.replace(triplo, "");
										cont++;
									}
								}

							}

						}
					}
				}
			}
		}
		
	}
	
	public void ensamblador() { // Generaci�n de triplos
		String operacion, operacionAux;
		int pos = 0, cont = 1;
		String ope2="";
		String ope1="";
		String triplo = "";
		for (int i = 0; i < operaciones.size(); i++) {
			operacion = operaciones.get(i).replaceAll("\\s", ""); // quitamos espacios en blanco
			operacionAux = quitaIgual(operacion);
			while (!operacionAux.isEmpty()) {
				//verificamos si contiene un numero negativo
				if((operacionAux.contains("-")) && esOperador(operacionAux.charAt(operacionAux.indexOf("-") - 1))) {
							pos=operacionAux.indexOf("-");
							e.add(new Ensamblador("MOV ","AL, "+Character.toString(operacionAux.charAt(pos))+Character.toString(operacionAux.charAt(pos+1))));
							operacionAux = operacionAux.replace(Character.toString(operacionAux.charAt(pos))+Character.toString(operacionAux.charAt(pos+1)), "");
							cont++;
				}
				// Agregamos primero lo que se encuentra en parentesis
				if (operacionAux.contains("(")) {
					pos = operacionAux.indexOf("(") + 1;
					
					while (operacionAux.charAt(pos) != ')') {
						triplo = triplo + operacionAux.charAt(pos);
						pos++;
					}
					ope1=triplo.substring(0,1);
					ope2=triplo.substring(2,3);
					e.add(new Ensamblador("MOV ","BL, "+ope1));
					e.add(new Ensamblador("MOV ","CL, "+ope2));
					if (triplo.contains("+")) {
						e.add(new Ensamblador("SUM ","BL, CL"));
					}
					if (triplo.contains("-")) {
						e.add(new Ensamblador("SUB ","BL, CL"));
					}
					if (triplo.contains("*")) {
						e.add(new Ensamblador("MUL ","BL, CL"));
					}
					if (triplo.contains("/")) {
						e.add(new Ensamblador("DIV ","BL, CL"));
					}
					operacionAux = operacionAux.replace("(", "");
					operacionAux = operacionAux.replace(")", "");
					cont++;
					operacionAux = operacionAux.replace(triplo, "");
				} else {
					// cuando la longitud es de 1, se realiza la operacion con los dos ultimos
					// triplos
					//MULTIPLICACION
					if (operacionAux.length() == 1) {
						triplo = e.get(cont-3).getNum() + operacionAux.charAt(0) + e.get(cont-2).getNum();
						if (triplo.contains("+")) {
							e.add(new Ensamblador("SUM ", "BL, AL"));
						}
						if (triplo.contains("-")) {
							e.add(new Ensamblador("SUB ", "BL, AL"));
						}
						if (triplo.contains("*")) {
							e.add(new Ensamblador("MUL ", "BL, AL"));
						}
						if (triplo.contains("/")) {
							e.add(new Ensamblador("DIV ", "BL, AL"));
						}		
						operacionAux = operacionAux.replace(Character.toString(operacionAux.charAt(0)), "");
					} else {
						// Cuando la longitud es 2, se realiza la operacion con el operando y el ultimo
						// triplo
						if (operacionAux.length() == 2) {
							triplo =Character.toString(operacionAux.charAt(1))+Character.toString(operacionAux.charAt(0))+ e.get(cont - 2).getNum();
							
							ope1=triplo.substring(0,1);
							e.add(new Ensamblador("MOV ","CL, "+ope1));
							if (triplo.contains("+")) {
								e.add(new Ensamblador("SUM ","BL, CL"));
							}
							if (triplo.contains("-")) {
								e.add(new Ensamblador("SUB ","BL, CL"));
							}
							if (triplo.contains("*")) {
								e.add(new Ensamblador("MUL ","BL, CL"));
							}
							if (triplo.contains("/")) {
								e.add(new Ensamblador("DIV ","BL, CL"));
							}
							
							operacionAux = operacionAux.replace(Character.toString(operacionAux.charAt(0))
									+ Character.toString(operacionAux.charAt(1)), "");
							cont++;
						} else {
							// Si es un operando se hace un triplo
							if (!esOperador(operacionAux.charAt(operacionAux.length() - 1))) {
								triplo = Character.toString(operacionAux.charAt(operacionAux.length() - 1));
								e.add(new Ensamblador("T" + cont, triplo));
								operacionAux = operacionAux.replace(triplo, "");
								cont++;
							} else {
								// Si es un operador y su antecesor es un operador se realiza la operacion con
								// los dos ultimos triplos
								if (esOperador(operacionAux.charAt(operacionAux.length() - 2))) {
									triplo = e.get(cont - 3).getNum() + Character.toString(operacionAux.charAt(operacionAux.length() - 1))
											+ e.get(cont - 2).getNum();
									if (triplo.contains("+")) {
										e.add(new Ensamblador("SUM ", "AL, BL"));
									}
									if (triplo.contains("-")) {
										e.add(new Ensamblador("SUB ", "AL, BL"));
									}
									if (triplo.contains("*")) {
										e.add(new Ensamblador("MUL ", "AL, BL"));
									}
									if (triplo.contains("/")) {
										e.add(new Ensamblador("DIV ", "AL, BL"));
									}
									operacionAux = operacionAux.substring(0, operacionAux.length() - 1);
									/*operacionAux = operacionAux.replace(
											Character.toString(operacionAux.charAt(operacionAux.length() - 1)), "");*/
									cont++;
								} else {
									// Si es un operando entonces se agrega un nuevo triplo
									if (!esOperador(operacionAux.charAt(operacionAux.length() - 2))) {
										triplo = Character.toString(operacionAux.charAt(0))
												+ Character.toString(operacionAux.charAt(1))
												+ Character.toString(operacionAux.charAt(2));
										ope1=triplo.substring(0,1);
										ope2=triplo.substring(2,3);
										e.add(new Ensamblador("MOV ","BL, "+ope1));
										e.add(new Ensamblador("MOV ","CL, "+ope2));
										if (triplo.contains("+")) {
											e.add(new Ensamblador("SUM ","DL, CL"));
										}
										if (triplo.contains("-")) {
											e.add(new Ensamblador("SUB ","DL, CL"));
										}
										if (triplo.contains("*")) {
											e.add(new Ensamblador("MUL ","DL, CL"));
										}
										if (triplo.contains("/")) {
											e.add(new Ensamblador("DIV ","DL, CL"));
										}
										operacionAux = operacionAux.replace(triplo, "");
										cont++;
									}
								}

							}

						}
					}
				}
			}
		}
		
	}
	

	String quitaIgual(String operacion) {
		int indice;
		indice = operacion.indexOf("=");
		inicial= operacion.charAt(indice-1);
		operacion = operacion.replace(Character.toString(operacion.charAt(indice - 1)), "");
		operacion = operacion.replace("=", "");
		return operacion;
	}

	boolean esOperador(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/')
			return true;
		return false;
	}//Fin triplos

	@SuppressWarnings("resource")
	public static String LeeArchivo(String URL) {
		String parrafo = "", texto = "";
		try {
			FileReader archivo = new FileReader(URL);
			BufferedReader leeLinea = new BufferedReader(archivo);
			parrafo = leeLinea.readLine();
			while (parrafo != null) {
				texto += parrafo + "\n";
				parrafo = leeLinea.readLine();
			}
		} catch (FileNotFoundException exception) {
			System.out.println(exception);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return texto;
	}

	public void recorreCodigo(String codigo) {
		String parrafo = "";
		for (int i = 0; i < codigo.length(); i++) {
			if (codigo.charAt(i) == '{' || codigo.charAt(i) == '}' || codigo.charAt(i) == ';') {
				linea.add(parrafo);
				parrafo = "";
			} else
				parrafo += Character.toString(codigo.charAt(i));
		}
	}

	public void LlenaTabla() {
		String parrafo;
		CharSequence sInt = "int", sIgual = "=";
		CharSequence sString = "String";
		CharSequence sDouble = "double";
		CharSequence sBool = "boolean";
		for (int i = 0; i < linea.size(); i++) {
			parrafo = linea.get(i);
			if (parrafo.contains(sInt) || parrafo.contains(sDouble) || parrafo.contains(sBool)
					|| parrafo.contains(sString)) {
				AgregaVariable(parrafo, sInt, i + 1);
				AgregaVariable(parrafo, sString, i + 1);
				AgregaVariable(parrafo, sDouble, i + 1);
				AgregaVariable(parrafo, sBool, i + 1);
			} else {
				if (parrafo.contains(sIgual)) {
					/* Si contiene igual y algun operador entonces es una operacion */
					if (parrafo.contains("+") || parrafo.contains("-") || parrafo.contains("/")
							|| parrafo.contains("+"))
						operaciones.add(parrafo);
					// Si contiene parentesis los omitimos, ya que estos solo los ocupamos en las
					// operaciones
					if (parrafo.contains("(") && parrafo.contains(")")) {
						parrafo = parrafo.replaceAll("\\(", "");
						parrafo = parrafo.replaceAll("\\)", "");
					}
					Operaciones(parrafo, i + 1);
				}
			}
		}
	}

	public void AgregaVariable(String parrafo, CharSequence Tipo, int pos) {
		String parrafoAux = "", variable = "", valor = "";
		CharSequence sIgual = "=", sPublic = "public", sPrivate = "private";
		// Llenado de la declaraci�n de las variables
		if (parrafo.contains(Tipo)) {
			for (int j = 0; j < parrafo.length(); j++) {
				if (parrafoAux.contains(Tipo)) {
					if (parrafoAux.contains(sIgual)) {
						valor += Character.toString(parrafo.charAt(j));
					} else {
						if (parrafo.charAt(j) == ' ') {
							variable = Character.toString(parrafo.charAt(j + 1));
						}
					}
				}
				parrafoAux += Character.toString(parrafo.charAt(j));
			}
			// Validar si la variable ya esta declarada
			if (tablaSimbolos.containsKey(variable))
				listaErroresSemanticos
						.add("La variable " + "'" + variable + "'" + " ya se encuentra declarada en la posici�n "
								+ tablaSimbolos.get(variable).getPosicion() + ".");
			else {
				// Verificando el valor asignado con el tipo
				if (!verificaTipoConValor(Tipo.toString(), valor)) {
					listaErroresSemanticos.add("Imposible asignar a variable " + "'" + variable + "' Tipo ("
							+ Tipo.toString() + ") valor:  " + valor + " posicion " + pos + ".");
					return;
				}
				if (parrafo.contains(sPublic) || parrafo.contains(sPrivate)) {
					tablaSimbolos.put(variable, new TablaSimbolos(Tipo.toString(), pos, valor, "global"));
					variables.add(variable);
				} else {
					tablaSimbolos.put(variable, new TablaSimbolos(Tipo.toString(), pos, valor, "local"));
					variables.add(variable);
				}
			}
		}

	}

	private boolean verificaTipoConValor(String tipo, String valor) {
		if (valor.length() == 0) {
			return true;
		}
		switch (tipo) {
		case "boolean":
			return (valor.equals("true") || valor.equals("false"));
		case "int":
			try {
				Integer.parseInt(valor);
				return true;
			} catch (Exception e) {
				return false;
			}
		default:
			return false;
		}

	}

	@SuppressWarnings("unused")
	public void Operaciones(String parrafo, int pos) {
		String parrafoAux = "", variable = "", operandoAux = "";
		boolean variableEncontrada = false;
		// Variables usadas y no defindas
		// Se recorre el parrafo para obtener la variable de la operaci�n
		for (int j = 0; j < parrafo.length(); j++) {
			if (!variableEncontrada && parrafo.charAt(j) == '=' && (parrafo.charAt(j + 1) != '=')) {// Encontramos la
																									// variable, lo que
																									// sigue es la
																									// operacion
				variable = parrafoAux;
				parrafoAux = "";
				variableEncontrada = true;
				if (!tablaSimbolos.containsKey(variable)) {
					listaErroresSemanticos.add(
							"La variable " + "'" + variable + "' en la posici�n " + pos + " no ha sido definida.");
					break;
				}
			} else { // se eliminan los espacios en blanco

				if (Character.isWhitespace(parrafo.charAt(j)))
					continue;
				if (!Character.isWhitespace(parrafo.charAt(j))
						&& (!esOperador(parrafo.charAt(j) + "") && !esOperador(parrafoAux))) {
					parrafoAux += Character.toString(parrafo.charAt(j));

				} else {
					// Verificamos si es una variable
					if (tablaSimbolos.containsKey(parrafoAux)) { // Es variable
						if (!tablaSimbolos.get(parrafoAux).getTipoDato()
								.equals(tablaSimbolos.get(variable).getTipoDato())) {
							listaErroresSemanticos.add("Tipo de operacion incorrecta en l variable " + variable + " Tipo ("
									+ tablaSimbolos.get(variable).getTipoDato() + ") con variable " + parrafoAux
									+ " Tipo ( " + tablaSimbolos.get(parrafoAux) + ")");
							break;
						}
					} else {// Es operando u constante
						if (esOperador(parrafoAux)) {// Es un operador
							if (!verificarTipoConOperando(tablaSimbolos.get(variable).getTipoDato(), parrafoAux)) {// No
																													// es
																													// un
																													// tipo
																													// de
																													// operador
																													// correco
								listaErroresSemanticos.add("Tipo de operacion incorrecta en l variable " + variable
										+ " Tipo (" + tablaSimbolos.get(variable).getTipoDato() + ") con operador "
										+ parrafoAux);
								break;
							}
						} else {// Es una constante
							if (!verificaTipoConValor(tablaSimbolos.get(variable).getTipoDato(), parrafoAux)) {
								listaErroresSemanticos
										.add("Tipo de operacion incorrecta variable " + variable + " Tipo ("
												+ tablaSimbolos.get(variable).getTipoDato() + ") con " + parrafoAux);
								break;
							}
						}
					}
					parrafoAux = "";
					if (parrafoAux.length() == 0) {// verificamos si necesitamos meter el operador
						parrafoAux += parrafo.charAt(j);
					}
				}
			}
		}
		if (parrafoAux.length() > 0) {// Quedo algo en los operandos/operadores
			// Verificamos si es una variable
			if (tablaSimbolos.containsKey(parrafoAux)) { // Es variable
				if (!tablaSimbolos.get(parrafoAux).getTipoDato().equals(tablaSimbolos.get(variable).getTipoDato())) {
					listaErroresSemanticos.add("Tipo de operacion incorrecta  en l variable " + variable + " Tipo ("
							+ tablaSimbolos.get(variable).getTipoDato() + ") con variable " + parrafoAux + " Tipo ( "
							+ tablaSimbolos.get(parrafoAux) + ")");
				}
			} else {// Es operando u constante
				if (esOperador(parrafoAux)) {// Es un operador
					if (!verificarTipoConOperando(tablaSimbolos.get(variable).getTipoDato(), parrafoAux)) {// No es un
																											// tipo de
																											// operador
																											// correco
						listaErroresSemanticos.add("Tipo de operacion incorrecta en l variable " + variable + " Tipo ("
								+ tablaSimbolos.get(variable).getTipoDato() + ") con operador " + parrafoAux);
/************/	}
				} else {// Es una constante
					if (!verificaTipoConValor(tablaSimbolos.get(variable).getTipoDato(), parrafoAux)) {
						listaErroresSemanticos.add("Tipo de operacion incorrecta en la variable " + variable + " Tipo ("
								+ tablaSimbolos.get(variable).getTipoDato() + ") con (" + parrafoAux+") en la posici�n "+pos);
					}
				}
			}
			parrafoAux = "";

		}
	}

	public boolean verificarTipoConOperando(String tipo, String operando) {
		switch (tipo) {
		case "int":
			return (operando.equals("+") || operando.equals("*") || operando.equals("/") || operando.equals("-"));
		case "boolean":
			return (operando.equals("&") || operando.equals("&&") || operando.equals("|") || operando.equals("||")
					|| operando.equals("!=") || operando.equals("=="));
		}
		return false;
	}

	public boolean esOperador(String operador) {
		return (operador.equals("+") || operador.equals("*") || operador.equals("/") || operador.equals("-"))
				|| (operador.equals("&") || (operador.equals("&&") || operador.equals("|") || operador.equals("||")
						|| operador.equals("!=") || operador.equals("==")));
	}

	public void imprimeTabla() {
		for (int i = 0; i < variables.size(); i++) {
			System.out.println(variables.get(i) + "\t\t" + tablaSimbolos.get(variables.get(i)).getTipoDato() + "\t\t"
					+ tablaSimbolos.get(variables.get(i)).getValor() + "\t\t"
					+ tablaSimbolos.get(variables.get(i)).getPosicion() + "\t"
					+ tablaSimbolos.get(variables.get(i)).getAlcance());
		}

	}
}
