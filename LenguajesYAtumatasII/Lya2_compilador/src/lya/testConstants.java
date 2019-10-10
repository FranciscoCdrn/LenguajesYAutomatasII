package lya;

public interface testConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int class_word = 5;
  int modifier = 6;
  int type_specifier = 7;
  int boolean_literal = 8;
  int if_declaration = 9;
  int while_declaration = 10;
  int relational_operator = 11;
  int aritmetic_operator = 12;
  int open_key = 13;
  int close_key = 14;
  int open_parenthesis = 15;
  int close_parenthesis = 16;
  int semicolon = 17;
  int equals = 18;
  int integer_literal = 19;
  int identifier = 20;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\n\"",
    "\"\\t\"",
    "\"class\"",
    "<modifier>",
    "<type_specifier>",
    "<boolean_literal>",
    "\"if\"",
    "\"while\"",
    "<relational_operator>",
    "<aritmetic_operator>",
    "\"{\"",
    "\"}\"",
    "\"(\"",
    "\")\"",
    "\";\"",
    "\"=\"",
    "<integer_literal>",
    "<identifier>",
  };

}
