package lya;

public class Token implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  public int kind;

  /** The line number of the first character of this Token. */
  public int beginLine;
  /** The column number of the first character of this Token. */
  public int beginColumn;
  /** The line number of the last character of this Token. */
  public int endLine;
  /** The column number of the last character of this Token. */
  public int endColumn;

  public String image;

  public Token next;

  public Token specialToken;

  public Object getValue() {
    return null;
  }

  public Token() {}

  public Token(int kind)
  {
    this(kind, null);
  }

  public Token(int kind, String image)
  {
    this.kind = kind;
    this.image = image;
  }


  public String toString()
  {
    return image;
  }

  public static Token newToken(int ofKind, String image)
  {
    switch(ofKind)
    {
      default : return new Token(ofKind, image);
    }
  }

  public static Token newToken(int ofKind)
  {
    return newToken(ofKind, null);
  }

}
