package lya;


/** Token Manager. */
public class testTokenManager implements testConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x420L) != 0L)
         {
            jjmatchedKind = 20;
            return 27;
         }
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 20;
            return 8;
         }
         if ((active0 & 0x40000L) != 0L)
            return 19;
         return -1;
      case 1:
         if ((active0 & 0x200L) != 0L)
            return 27;
         if ((active0 & 0x420L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 1;
            return 27;
         }
         return -1;
      case 2:
         if ((active0 & 0x420L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 2;
            return 27;
         }
         return -1;
      case 3:
         if ((active0 & 0x420L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 3;
            return 27;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 15);
      case 41:
         return jjStopAtPos(0, 16);
      case 59:
         return jjStopAtPos(0, 17);
      case 61:
         return jjStartNfaWithStates_0(0, 18, 19);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 123:
         return jjStopAtPos(0, 13);
      case 125:
         return jjStopAtPos(0, 14);
      default :
         return jjMoveNfa_0(6, 0);
   }
}
@SuppressWarnings("static-access")
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 102:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(1, 9, 27);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
@SuppressWarnings("static-access")
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
@SuppressWarnings("static-access")
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x400L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
@SuppressWarnings("static-access")
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(4, 10, 27);
         break;
      case 115:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(4, 5, 27);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
@SuppressWarnings("static-access")
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
@SuppressWarnings("static-access")
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 40;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 19)
                        kind = 19;
                     jjCheckNAdd(25);
                  }
                  else if ((0x280000000000L & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                  }
                  else if ((0x5000000000000000L & l) != 0L)
                  {
                     if (kind > 11)
                        kind = 11;
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 19)
                        kind = 19;
                  }
                  else if (curChar == 33)
                     jjCheckNAdd(19);
                  else if (curChar == 61)
                     jjCheckNAdd(19);
                  if (curChar == 60)
                     jjCheckNAdd(19);
                  else if (curChar == 62)
                     jjCheckNAdd(19);
                  break;
               case 18:
                  if ((0x5000000000000000L & l) != 0L && kind > 11)
                     kind = 11;
                  break;
               case 19:
                  if (curChar == 61 && kind > 11)
                     kind = 11;
                  break;
               case 20:
                  if (curChar == 62)
                     jjCheckNAdd(19);
                  break;
               case 21:
                  if (curChar == 60)
                     jjCheckNAdd(19);
                  break;
               case 22:
                  if (curChar == 61)
                     jjCheckNAdd(19);
                  break;
               case 23:
                  if (curChar == 33)
                     jjCheckNAdd(19);
                  break;
               case 24:
                  if ((0x280000000000L & l) != 0L)
                     kind = 12;
                  break;
               case 25:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  kind = 19;
                  jjCheckNAdd(25);
                  break;
               case 26:
                  if (curChar == 48)
                     kind = 19;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                     jjCheckNAdd(27);
                  }
                  if (curChar == 112)
                     jjAddStates(0, 1);
                  else if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 16;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 12;
                  else if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 8;
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 8:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                     jjCheckNAdd(27);
                  }
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 0:
                  if (curChar == 110 && kind > 7)
                     kind = 7;
                  break;
               case 1:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 2:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 4:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 5:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 7:
                  if (curChar == 116 && kind > 7)
                     kind = 7;
                  break;
               case 9:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 101 && kind > 8)
                     kind = 8;
                  break;
               case 11:
                  if (curChar == 117)
                     jjCheckNAdd(10);
                  break;
               case 12:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 13:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 115)
                     jjCheckNAdd(10);
                  break;
               case 15:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 16:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 27:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjCheckNAdd(27);
                  break;
               case 28:
                  if (curChar == 112)
                     jjAddStates(0, 1);
                  break;
               case 29:
                  if (curChar == 99 && kind > 6)
                     kind = 6;
                  break;
               case 30:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 32:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 33:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 32;
                  break;
               case 34:
                  if (curChar == 101 && kind > 6)
                     kind = 6;
                  break;
               case 35:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 34;
                  break;
               case 36:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 35;
                  break;
               case 37:
                  if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 38;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         @SuppressWarnings("unused")
		int i2 = (curChar & 0xff) >> 6;
         @SuppressWarnings("unused")
		long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 40 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   33, 39, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, "\143\154\141\163\163", null, null, null, 
"\151\146", "\167\150\151\154\145", null, null, "\173", "\175", "\50", "\51", "\73", 
"\75", null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x1fffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[40];
static private final int[] jjstateSet = new int[80];
static protected char curChar;
/** Constructor. */
public testTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public testTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 40; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

@SuppressWarnings("static-access")
static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
@SuppressWarnings("static-access")
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
@SuppressWarnings("unused")
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}