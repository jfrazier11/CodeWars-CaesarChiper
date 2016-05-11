
public class CaesarTwo {
  private static int theShift = 0;
  public static char original;
  private static int determineLength(String s){
      if(s.length()%5 == 0){
        return s.length()/5;
      }
      else{
        return (s.length()/5) +1;
      }
    }

    public static List<String> encodeStr(String s, int shift) {
    original = s.charAt(0);
      if (s.length() < 4) {
        return null;
      }
      theShift = shift;
      s = shiftString(s, shift);
      int lengthPerPiece = determineLength(s);
      List<String> myCodes = new ArrayList<String>();
      int offSet = 0;
      while (s.length() > lengthPerPiece) {
        myCodes.add(s.substring(offSet, lengthPerPiece));
        s = s.substring(lengthPerPiece, s.length());
      }
      if (!s.equals("")) {
        myCodes.add(s);
      }
      return myCodes;
    }

    private static String shiftString(String theString, int shift) {
      StringBuilder strb = new StringBuilder();
      for (int i = 0; i < theString.length(); i++) {
        char c = theString.charAt(i);
        if (Character.isLetter(c)) {
          if (i == 0) {
            String tmp = "" + Character.toLowerCase(c) + (char) Character.toLowerCase(c + shift)
                + (char) Character.toUpperCase(c + shift);
            strb.append(tmp);
          } else {
            String tmp = "" + (char) (c + shift);
            strb.append(tmp);
          }
        } else {
          strb.append(theString.charAt(i));
        }
      }
          if(!Character.isUpperCase(original)){
        strb.setCharAt(2,Character.toLowerCase(strb.charAt(2)));
      }
      return strb.toString();
    }

    private static int getShift() {
      return theShift;
    }

    public static String decode(List<String> s) {
      int shift = getShift();
      StringBuilder strb = new StringBuilder();
      for (String str : s) {
        strb.append(str);
      }
      strb.deleteCharAt(0);
      strb.deleteCharAt(1);
      for (int i = 0; i < strb.toString().length(); i++) {
        if (Character.isLetter((char)(strb.charAt(i)-shift))) {
            strb.setCharAt(i, (char) (strb.charAt(i) - shift));          
        }
      }
      strb.setCharAt(0, original);
      return strb.toString();
    }
}
