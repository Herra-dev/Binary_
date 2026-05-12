//============================================================================
// Decimal
//============================================================================
//
// THIS CLASS IS USED TO TRANSFORM A NUMBER IN DECIMAL REPRESENTATION INTO BINARY REPRESENTATION
// 
// ____ FOR A FLOATING POINT NUMBER(METHOD :_toBinaryFloat(double, FloatPrecision)): FloatPrecision WILL BE ASKED AFTER IF IT'S EQUAL TO NULL
//     |____ REPRESENTATION: IEEE 754

package mg.hr;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public abstract class Binary {

/**
 * returns array of byte with length defined in parameter {@code _bitNumber}
 * <p>if _bitNumber is equals to 0, return immediately an array of byte with length 0.
 *    
 * @param _number {@code double}
 * @param _bitNumber {@code int}
 * 
 * @return {@code byte[] }
 * 
 * @throws mg.hr.exception.BinaryException if {@code _bitNumber} is a negative value
 * 
 * @see {@link mg.hr.Binary#toBinary(double)}
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] toBinary(BigDecimal _number, double _bitNumber) throws mg.hr.exception.BinaryException
    {
        if(_bitNumber < 0) throw new mg.hr.exception.BinaryException((int)_bitNumber);
        if(_bitNumber == 0) return new byte[0];

        byte tab[] = new byte[(int)_bitNumber];
        
        //================
        // initialization of the array to stock binary number with 0
        //================
        for(int i = 0; i < _bitNumber; i++)
            tab[i] = 0;
        
        if(_number.compareTo(new BigDecimal("0")) == 0) return tab;

        boolean _signed = (_number.doubleValue() < 0) ? true : false; 
        double i = (_number.subtract(new BigDecimal(_number.toBigInteger()))).doubleValue();

        // if i(result of the previous calcul) is between -1 and 1(both excluded) but not 0, _number is a floating-point number
        boolean _isFloat = (i > -1 && i < 1 && i != 0) ? true : false; 

        if(!_isFloat) // if _number is not a floating-point number
        {
            if(!_signed) {
                try {
                    return _toBinaryUnsignedInteger(_number, _bitNumber); // unsigned integer 
                } catch (mg.hr.exception.BinaryException | 
                            mg.hr.exception.NotAnIntegerException | 
                                mg.hr.exception.NotAnUnsignedIntegerException e) {
                    e.printStackTrace();
                }   
            }   
            else {
                try {
                    return _toBinarySignedInteger(_number, _bitNumber); // signed long
                } catch (mg.hr.exception.BinaryException | 
                            mg.hr.exception.NotAnIntegerException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            return _toBinaryFloat(_number, _askForPrecision(_number));  // floating number
        }
    
        return tab; // if there is an error occured during before operations, return tab
    }

//============================================================================

/**
 * ask user for precision to represent number in {@code the parameter _number}<p>
 * 
 * @param _number {@code double}
 * 
 * @return {@link mg.hr.enumeration.FloatPrecision}
 * 
 * @see mg.hr.enumeration.FloatPrecision
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    private static mg.hr.enumeration.FloatPrecision _askForPrecision(BigDecimal _number)
    {
        mg.hr.enumeration.FloatPrecision[] _available = mg.hr.enumeration.FloatPrecision.getAvailablePrecision();

        mg.hr.enumeration.FloatPrecision _Precision = mg.hr.enumeration.FloatPrecision._HALF_PRECISION;
        short _choosenPrecision = 0;
        System.out.println(_number + " is a floating point number, choose how to represent it = \n");
        
        mg.hr.enumeration.FloatPrecision.seeAvailablePrecision();

        try (java.util.Scanner sc = new java.util.Scanner(java.lang.System.in)) {
            _choosenPrecision = sc.nextShort();
            
            // clean line before reading another one
            sc.nextLine();

            boolean in = false;
            for(short i = 0; i < _available.length; i++) {
                if(_choosenPrecision == _available[i].getPrecision()) {
                    in = true;
                    break;
                }
            }

            while(!in) {
                clearScreen();
                System.out.println("Please enter: ");
                mg.hr.enumeration.FloatPrecision.seeAvailablePrecision();
                _choosenPrecision = sc.nextShort();

                for(short i = 0; i < _available.length; i++) {
                    if(_choosenPrecision == _available[i].getPrecision()) {
                        in = true;
                        break;
                    }
                }
            }
        }
        
        for(short i = 0; i < _available.length; i++) {
            if(_choosenPrecision == _available[i].getPrecision())
                _Precision = _available[i];
        }

        return _Precision;
    }

//============================================================================

/**
 * Same as {@link #toBinary(double, int)} but the second parameter {@code _bitNumber} is automatically setted according to parameter {@code _number}'s value
 * 
 * @param _number {@code double}
 * @return an array - {@code byte[]} sequence of bit representing {@code _number} in binary 
 * @throws mg.hr.exception.BinaryException
 * @see mg.hr.Binary#toBinary(double, int)
 * @see mg.hr.Binary#_powerOfTwoCloseBottom(long)
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] toBinary(BigDecimal _number) throws mg.hr.exception.BinaryException
    {
        return (_number.doubleValue() > 0) 
            ? toBinary(_number, mg.hr.Binary._powerOfTwoCloseBottom(_number) + 1)
            : toBinary(_number, mg.hr.Binary._powerOfTwoCloseBottom(_number.negate()) + 1);
    }

//============================================================================

/**
 * returns power of two close or equal to _number 
 * 
 * @param _number {@code long}
 * @return {@code int} 
 * @author {@see https://github.com/Herra-dev}
 */
    public static double _powerOfTwoCloseBottom(BigDecimal _number)
    {
        double i = 1;
        double pow = 0;

        while(_number.compareTo(new BigDecimal(i *= 2)) >= 0) pow++;

        return pow;
    }

//============================================================================

/**
 * returns the complement of {@code _binary}, eg:<p>
 * * {@code 100011110} becomes {@code 011100001}<p> 
 * * {@code 1010101000} becomes {@code 0101010111}
 * 
 * @param _binary {@code byte[]}
 * @return {@code byte[]}
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _complementBinary(byte[] _binary)
    {
        byte[] b = new byte[_binary.length];
        for(int i = 0; i < b.length; i++)
        {
            b[i] = (_binary[i] == 0) ? (byte)1 : (byte)0;
        }
        return b;
    }

//============================================================================    

/**
 * returns {@code _number} in reverse.
 * <p>eg:
 * <p> - {@code 10111} becomes {@code 11101}
 * <p> - {@code 10001111} becomes {@code 11110001}
 * 
 * @param _number {@code byte[]}
 * @return byte[]
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _reverseBinary(byte[] _number)
    {
        byte[] reversedBinary = new byte[_number.length];

        int j = _number.length;
        for(int i = 0; i < _number.length; i++)
        {
            reversedBinary[--j] = _number[i];
        }

        return reversedBinary;
    }

//============================================================================

/**
 * returns {@code 0} if {@code _number} is positive, returns {@code -1} otherwise
 * 
 * @param _number {@code double}
 * @return {@code byte}
 * @author {@see https://github.com/Herra-dev}
 */
    private static byte _binarySign(BigDecimal _number)
    {
        return (_number.compareTo(new BigDecimal(0)) >= 0) ? (byte)0 : (byte)1;
    }

//============================================================================

/**
 * returns an array of byte representing the {@code floor} of a real number
 * 
 * @param _number {@code double}
 * @return byte[]
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _floor(BigDecimal _number)
    {
        byte _floorBinary[] = null;
        try
        {
            BigDecimal _numberAbsValue = new BigDecimal(_number.toBigInteger().abs());
            _floorBinary = mg.hr.Binary.toBinary(_numberAbsValue); // _floorBinary of _number in binary mode
        } 
        catch (mg.hr.exception.BinaryException e)
        {
            e.printStackTrace();
        }

        System.out.println("displayint floor in floor method: ******************");
        _displayBinaryNumber(_floorBinary);
        return _floorBinary;
    }

//============================================================================

/**
 * returns an array of byte representing the decimal part of a floating-point number
 * 
 * @param _decimalPart {@code double}
 * @param _precision {@link mg.hr.enumeration.FloatPrecision}
 * @return byte[]
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _decimal(BigDecimal _decimalPart, mg.hr.enumeration.FloatPrecision _precision)
    {
        BigDecimal limit = BigDecimal.ZERO;

        byte[] _decimalPartBinary = new byte[_precision.getPrecision()];
        for(int i = 0; i < _decimalPartBinary.length; i++) _decimalPartBinary[i] = 0;

        int i = 0;
        String s = new String();
        
        while(_decimalPart.compareTo(limit) != 0.0 && i < _decimalPartBinary.length)
        {
            _decimalPart = _decimalPart.multiply(new BigDecimal(2));   
            _decimalPartBinary[i++] = (byte)_decimalPart.round(MathContext.DECIMAL128).intValue();
            
            s = _decimalPart.intValue() + "";
            _decimalPart = _decimalPart.subtract(new java.math.BigDecimal(s), java.math.MathContext.DECIMAL128);
        }

        
        return _decimalPartBinary;
    }

//============================================================================    

/**
 * returns an array of short exponent information is stored
 * 
 * @param _number {@code double}
 * @param _floorBinary {@code byte[]}
 * @param _decimalPartBinary {@code byte[]}
 * @return short[]
 * @author {@see https://github.com/Herra-dev}
 */
    private static short[] _exp(BigDecimal _number, byte[] _floorBinary, byte[] _decimalPartBinary)
    {
        System.out.println("Displaying floor ===========");
        _displayBinaryNumber(_floorBinary);
        System.out.println("Displaying dec part ===========");
        _displayBinaryNumber(_decimalPartBinary);

        short exp[] = new short[2];

        int exp_d = 0;
        int expIndex = 0;
        _number = _number.abs();
        System.out.println("number is = " + _number);
        if((_number.toBigInteger().compareTo(BigInteger.ZERO)) > 0)
        {
            
            for(exp_d = 0; exp_d < _floorBinary.length; exp_d++)
            {
                if(_floorBinary[exp_d] == 1) // search first bit 1
                {
                    ++exp_d; // if 1 was found in index 0 for example, the comma place is in the next index
                    System.out.println("find it");
                    break;
                }
            }
            expIndex = exp_d;
            exp_d = _floorBinary.length - exp_d; // distance between _floorBinary and exp_d
        }
        else if((_number.toBigInteger().compareTo(BigInteger.ZERO)) == 0)
        {
            for(exp_d = 0; exp_d < _decimalPartBinary.length; exp_d++)
            {
                if(_decimalPartBinary[exp_d] == 1)
                    break;
            }
            expIndex = exp_d;
            exp_d = -(exp_d + 1);
        }

        exp[0] = (short)exp_d; System.out.println("----- " + exp[0]);
        exp[1] = (short)expIndex; System.out.println("----- " + exp[1]);

        return exp;
    }

//============================================================================    

/**
 * {@code IEEE 754}<p>
 * returns an array of byte representing {@code _number} in {@code binary}
 * <p><p>
 * For floating number, number of bit is usually represented in:
 *      <p>- half precision            = {@code 16 bits}
 *      <p>- simple precision          = {@code 32 bits}
 *      <p>- extended simple precision = {@code 48 bits}
 *      <p>- dual precision            = {@code 64 bits}
 *      <p>- extended dual precision   = {@code 79 bits}
 *      <p>- quadruple precision       = {@code 128 bits}
 *      <p>- octuple precision         = {@code 256 bits}
 * <p>{@code if _precision is null, user will be asked to set it before proceeding to transformation}
 * 
 * @param _number {@code double}
 * @param _precision {@code mg.hr.enumeration.FloatPrecision}
 * @return byte[] {@code the floating-point number in binary}
 * @see mg.hr.enumeration.FloatPrecision
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _toBinaryFloat(BigDecimal _number, mg.hr.enumeration.FloatPrecision _Precision)
    {
        if(_Precision == null) _Precision = _askForPrecision(_number);
        byte tab[] = new byte[_Precision.getPrecision()];

        byte _sign = _binarySign(_number); // SIGN
        byte _floorBinary[] = _floor(_number); // FLOOR
        byte[] _decimalPartBinary = _decimal((_number.subtract(new BigDecimal(_number.toBigInteger()))).abs(), _Precision);
        
        //----------------------------------------------------------------------
        
        short[] __ = _exp(_number, _floorBinary, _decimalPartBinary);
        short exp = __[0];
        short expIndex = __[1];

        byte E[]  = null;
        try
        {
            int l = exp + _Precision.getBiais();
            E = toBinary(new BigDecimal(l), _Precision.getExpNumber());
        }
        catch(mg.hr.exception.BinaryException e)
        {
            e.printStackTrace();
        }
        //----------------------------------------------------------------------

        int j = 0;
        tab[j] = _sign; // ============================== SIGN =============

        for(int k = 0; k < E.length; k++)
            tab[++j] = E[k]; // ========================= EXPONENT =========
        if(_number.toBigInteger().compareTo(new BigInteger("0")) != 0)
        {
            for(int k = expIndex; k < _floorBinary.length; k++){
                if(++j >= tab.length) break;
                tab[j] = _floorBinary[k]; // ============== MANTISSA =========
            }
            int k = 0;
            while (++j < tab.length)
            {
                tab[j] = _decimalPartBinary[k++]; // ==== MANTISSA =========
            }
        }
        else
        {
            int k = expIndex;
            while (++j < tab.length && ++k < _decimalPartBinary.length) 
            {
                tab[j] = _decimalPartBinary[k];
            }
        }
        
        return tab;
    }

//============================================================================    

/**
 * return an array of byte representing {@code _number} with length equal to {@code _bit_number}: 
 * <p> - {@code signed}
 * <p> - {@code not a floating number}
 * 
 * @param _number {@code long}
 * @param _bitNumber {@code int}
 * 
 * @return byte[]
 * 
 * @see mg.hr.Binary#_toBinaryUnsignedInteger(long, int)
 * 
 * @throws mg.hr.exception.BinaryException when {@code _bitNumber} is a negative number
 * @throws mg.hr.exception.NotAnIntegerException when {@code _number} is a floating-point number
 * 
 * @author {@see https://github.com/Herra-dev} 
 */
    public static byte[] _toBinarySignedInteger(BigDecimal _number, double _bitNumber) 
        throws  mg.hr.exception.BinaryException, 
                    mg.hr.exception.NotAnIntegerException {
        //-----------------------------------------------------------------------
        //          EXCEPTIONS

        if(_bitNumber < 0) throw new mg.hr.exception.BinaryException((int)_bitNumber);
        
        double i = (_number.subtract(new BigDecimal(_number.toBigInteger()))).doubleValue();
        boolean _isFloat = (i > -1 && i < 1 && i != 0) ? true : false; 
        if(_isFloat) throw new mg.hr.exception.NotAnIntegerException(_number.doubleValue());

        //-----------------------------------------------------------------------
        
        // if _number is a positive number, transform it with method _toBinaryUnsignedInteger
        try {
            if(_number.doubleValue() >= 0)
                return mg.hr.Binary._toBinaryUnsignedInteger(_number, _bitNumber);
        } catch (mg.hr.exception.BinaryException |
                    mg.hr.exception.NotAnIntegerException |
                        mg.hr.exception.NotAnUnsignedIntegerException e) {
            e.printStackTrace();
        }

        byte tab[] = new byte[(int)_bitNumber];
        
        byte tab1[] = new byte[(int)_bitNumber];
        for(short j = 0; j < _bitNumber; j++) // Stock 0 in every index in tab1
            tab1[j] = 0;
        tab1[(int)_bitNumber - 1] = 1; // Stock 1 in last index of tab1

        try {
            // IF _number IS A NEGATIVE VALUE, TRANSFORM THE ABS OF THIS LAST INTO BINARY
            tab = _toBinaryUnsignedInteger(_number.negate(), _bitNumber);
        } catch (mg.hr.exception.BinaryException | 
                    mg.hr.exception.NotAnIntegerException | 
                        mg.hr.exception.NotAnUnsignedIntegerException e) {
            e.printStackTrace();
        }
        tab  = mg.hr.Binary._complementBinary(tab);

        try {
            tab = mg.hr.BinaryMath._addBinary(tab, tab1, (int)_bitNumber);
        } catch (mg.hr.exception.NotABinaryNumberException e) {
            e.printStackTrace();
        }

        return tab;
    }

//============================================================================    

/**
 * returns array of byte with length defined in the param _bitNumber
 * 
 * <p>exemple: 
 *      <p>* for 98 in decimal, 
 *      <p>* 98, 64 < 98 < 128, so 98 contains {@code 64} = pow(2, 6),    {@code [keep 6]}
 *      <p>* 98 - 64 = 34, 32 < 34 < 64,  ---> {@code 32} = pow(2, 5),    {@code [keep 5]}
 *      <p>* 34 - 32 = 2, --->                 {@code 2}  = pow(2, 1),    {@code [keep 1]}
 * <p><p>
 *      <p>** {@code result: 01100010 = 98}
 * 
 * @param _number       {@code long}
 * @param _bitNumber    {@code int}
 * 
 * @return byte[]
 * 
 * @see mg.hr.Binary#_powerOfTwoCloseBottom(long)
 * @see mg.hr.Binary#_reverseBinary(byte[])
 * 
 * @throws mg.hr.exception.BinaryException                  when {@code _bitNumber} is a negative number
 * @throws mg.hr.exception.NotAnIntegerException            when {@code _number} is not an integer valuee
 * @throws mg.hr.exception.NotAnUnsignedIntegerException    when {@code _number} is a signed number
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _toBinaryUnsignedInteger(BigDecimal _number, double _bitNumber) 
        throws  mg.hr.exception.BinaryException, 
                    mg.hr.exception.NotAnIntegerException,
                        mg.hr.exception.NotAnUnsignedIntegerException {
        //-----------------------------------------------------------------------
        //          EXCEPTIONS

        if(_bitNumber < 0) throw new mg.hr.exception.BinaryException((int)_bitNumber);
        
        BigDecimal _i = _number.subtract(new BigDecimal(_number.toBigIntegerExact()));
        double i = _i.doubleValue();
        boolean _isFloat = (i > -1 && i < 1 && i != 0) ? true : false; 
        if(_isFloat) throw new mg.hr.exception.NotAnIntegerException(_number.doubleValue());

        if(_number.compareTo(new BigDecimal(0)) < 0) throw new mg.hr.exception.NotAnUnsignedIntegerException(_number.doubleValue());

        //-----------------------------------------------------------------------

        BigDecimal _numberCopy = _number;
        double j = 0;
        byte binaryReversed[] = new byte[(int)_bitNumber];

        while(_numberCopy.compareTo(new BigDecimal(0)) != 0.0) {
            j = _powerOfTwoCloseBottom(_numberCopy);
            System.out.println("j = " + j);
            if(j < binaryReversed.length)
                binaryReversed[(int)j] = 1;
            
            _numberCopy = _numberCopy.subtract(new BigDecimal(java.lang.StrictMath.pow(2, j)));
        }

        return _reverseBinary(binaryReversed);
    }

//============================================================================

/**
 * display all values in the parameter {@code _bit}
 * 
 * @param _bit {@code byte[]}
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static void _displayBinaryNumber(byte[] _bit) {
        
        if(_bit == null)
        {
            System.out.println("Cannot display _bit, because it's null");
            return;
        }
            
        for(byte b: _bit)
            System.out.print(b);
        System.out.println();
    }

//============================================================================

/**
 * function used to clear terminal
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static void clearScreen()
    {
        try
        {
            if(java.lang.System.getProperty("os.name").startsWith("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch(java.lang.SecurityException |
                java.lang.NullPointerException |
                    java.lang.IllegalArgumentException |
                        java.io.IOException |
                            java.lang.InterruptedException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

//============================================================================

}
