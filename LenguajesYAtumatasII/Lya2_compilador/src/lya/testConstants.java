package lya;
/*JCC*/

public interface testConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int class_word = 5;
  /** RegularExpression Id. */
  int modifier = 6;
  /** RegularExpression Id. */
  int type_specifier = 7;
  /** RegularExpression Id. */
  int boolean_literal = 8;
  /** RegularExpression Id. */
  int if_declaration = 9;
  /** RegularExpression Id. */
  int while_declaration = 10;
  /** RegularExpression Id. */
  int relational_operator = 11;
  /** RegularExpression Id. */
  int aritmetic_operator = 12;
  /** RegularExpression Id. */
  int open_key = 13;
  /** RegularExpression Id. */
  int close_key = 14;
  /** RegularExpression Id. */
  int open_parenthesis = 15;
  /** RegularExpression Id. */
  int close_parenthesis = 16;
  /** RegularExpression Id. */
  int semicolon = 17;
  /** RegularExpression Id. */
  int equals = 18;
  /** RegularExpression Id. */
  int integer_literal = 19;
  /** RegularExpression Id. */
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
