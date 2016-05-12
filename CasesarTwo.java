/**
 * This class is used to create encrytped and decryted message in the form of
 * a Caesar Chiper determined by a shift.
 * @author Jeremi Frazier
 * @version 1.1
 **/
 
public class CaesarTwo {
  
  private static int theShift = 0;
  public static char original;
  
  /**
   * This aux or helper method determines the length of a string and is used to determine
   * how many parts the message should be broken into.
   * @param s The string to determine.
   * @return int the length of the string s.
   **/
   
   private static int determineLength(String s){
      if(s.length()%5 == 0){
        return s.length()/5;
      }
      else{
        return (s.length()/5) +1;
      }
    }
    
    /**
     * This method is called to break the casear cipher message
     * into 4-5 parts and encrypt it based on the shift.
     * @param s the original unecrpytped message.
     * @param shift the amount to shift each character by.
     * @return List<String> the encrypted message parts.
     **/

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
    /** This method is shifts the string character by character and is hidden...
     *  @param theString -message @param shift -amount to shift by.
     *  @return String -encoded string.
     **/
     
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
    /**
     * This is a private accessor method. It is used to hold the shift value
     * obtained in encrypt for usage in decrypt
     * @return int -the shift amount
     **/
    
    private static int getShift() {
      return theShift;
    }
    
    /**
     * This method is used to decode an encoded message
     * The decoded message is equal to the original messgae.
     * @param s -The encoded message in parts.
     * @return Strin - the original message
     **/

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
