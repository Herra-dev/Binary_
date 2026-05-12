package herra.string;

import herra.exception.UnacceptableParameterException;

public class HString {
/**
 * <h2>occ</h2> {@link herra.string.HString#occ(String, char)}<p>
 * Search the occurence of {@code which} in {@code from}
 * 
 * @param from 
 * @param which
 * @return {@code int} occurence of {@code which} in {@code from}
 */
    public static int occ(String from, char which) {
        int occurence = 0;

        for(int i = 0; i < from.length(); i++) {
            if(from.charAt(i) == which)
                occurence++;
        }

        return occurence;
    }

//====================================================================================================

    public static String byteArrayToString(byte[] array) {
        String result = new String();

        for(byte b: array) result += b;

        return result;
    }

//====================================================================================================

    public static char[] separeString(String str) {
        char[] result = new char[str.length()];

        for(int i = 0; i < str.length(); i++) result[i] = str.charAt(i);

        return result;
    }

//====================================================================================================

    public static String joinChar(char[] charArray) {
        String result = new String();

        for(char c: charArray) result += c;

        return result;
    }

//====================================================================================================

    public static byte[] stringToByteArray(String str) throws UnacceptableParameterException {
        if(!(str.matches("[0-1]++"))) throw new UnacceptableParameterException(str) ;
        
        byte[] result = new byte[str.length()];
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '0') result[i] = 0;
            if(str.charAt(i) == '1') result[i] = 1;
        }

        return result;
    }

}