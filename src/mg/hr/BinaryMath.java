//============================================================================
// BinaryMath
//============================================================================
//
// THIS CLASS IS USED TO REALIZE CALCUL BETWEEN TWO BINARY NUMBER OR TWO DOUBLE NUMBER
// RESULT WILL ALLWAYS IN BINARY MODE
//============================================================================

package mg.hr;

import java.math.BigDecimal;

import mg.hr.exception.NotABinaryNumberException;

public abstract class BinaryMath
{

/**
 * add _firstNumber to _secondNumber and returns result in binary.
 *  
 * @param _firstNumber  {@code double}
 * @param _secondNumber {@code double}
 * @param _bitNumber    {@code int} number of bit to be returned
 * 
 * @return byte[]
 * @see mg.hr.BinaryMath#_subtractBinary(byte[], byte[], int)
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[], int)
 * 
 * @see mg.hr.BinaryMath#_subtractBinary(double, double, int)
 * @see mg.hr.BinaryMath#_multiplyBinary(double, double, int)
 * @see mg.hr.BinaryMath#_divideBinary(double, double, int)
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _addBinary(BigDecimal _firstNumber, BigDecimal _secondNumber, int _bitNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.add(_secondNumber)), _bitNumber);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================

/**
 * Same as {@link mg.hr.BinaryMath#_addBinary(double, double, int)}, but,
 * <p>the number of bit returned is automatically setted
 * 
 * @param _firstNumber  {@code double}
 * @param _secondNumber {@code double}
 * 
 * @author {@see https://github.com/Herra-dev}
 * 
 */
    public static byte[] _addBinary(BigDecimal _firstNumber, BigDecimal _secondNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.add(_secondNumber)));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================

/**
 * substract _firstNumber to _secondNumber and returns result in binary.
 *  
 * @param _firstNumber {@code double}
 * @param _secondNumber {@code double}
 * @param _bitNumber {@code int}
 * 
 * @return byte[]
 * @see mg.hr.BinaryMath#_subtractBinary(byte[], byte[], int)
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[], int)
 * 
 * @see mg.hr.BinaryMath#_addBinary(double, double, int)
 * @see mg.hr.BinaryMath#_multiplyBinary(double, double, int)
 * @see mg.hr.BinaryMath#_divideBinary(double, double, int)
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _subtractBinary(BigDecimal _firstNumber, BigDecimal _secondNumber, int _bitNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.subtract(_secondNumber)), _bitNumber);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }
    
//============================================================================

/**
 * Same as {@link mg.hr.BinaryMath#_subtractBinary(double, double, int)}, but,
 * <p>the number of bit returned is automatically setted
 * 
 * @param _firstNumber  {@code double}
 * @param _secondNumber {@code double}
 * 
 * @author {@see https://github.com/Herra-dev}
 * 
 */
    public static byte[] _subtractBinary(BigDecimal _firstNumber, BigDecimal _secondNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.subtract(_secondNumber)));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================
    
/**
 * multiply _firstNumber to _secondNumber and returns result in binary.
 *  
 * @param _firstNumber {@code double}
 * @param _secondNumber {@code double}
 * @param _bitNumber {@code int}
 * 
 * @return byte[]
 * @see mg.hr.BinaryMath#_subtractBinary(byte[], byte[], int)
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[], int)
 * 
 * @see mg.hr.BinaryMath#_subtractBinary(double, double, int)
 * @see mg.hr.BinaryMath#_addBinary(double, double, int)
 * @see mg.hr.BinaryMath#_divideBinary(double, double, int)
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _multiplyBinary(BigDecimal _firstNumber, BigDecimal _secondNumber, int _bitNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.multiply(_secondNumber)), _bitNumber);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================

/**
 * Same as {@link mg.hr.BinaryMath#_multiplyBinary(double, double, int)}, but,
 * <p>the number of bit returned is automatically setted
 * 
 * @param _firstNumber  {@code double}
 * @param _secondNumber {@code double}
 * 
 * @author {@see https://github.com/Herra-dev}
 * 
 */
    public static byte[] _multiplyBinary(BigDecimal _firstNumber, BigDecimal _secondNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.multiply(_secondNumber)));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================

/**
 * multiply _firstNumber to _secondNumber and returns result in binary.
 *  
 * @param _firstNumber {@code double}
 * @param _secondNumber {@code double}
 * @param _bitNumber {@code int}
 * 
 * @return byte[]
 * @see mg.hr.BinaryMath#_subtractBinary(byte[], byte[], int)
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[], int)
 * 
 * @see mg.hr.BinaryMath#_subtractBinary(double, double, int)
 * @see mg.hr.BinaryMath#_addBinary(double, double, int)
 * @see mg.hr.BinaryMath#_multiplyBinary(double, double, int)
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _divideBinary(BigDecimal _firstNumber, BigDecimal _secondNumber, int _bitNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.divide(_secondNumber)), _bitNumber);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================

/**
 * Same as {@link mg.hr.BinaryMath#_divideBinary(double, double, int)}, but,
 * <p>the number of bit returned is automatically setted
 * 
 * @param _firstNumber  {@code double}
 * @param _secondNumber {@code double}
 * 
 * @author {@see https://github.com/Herra-dev}
 * 
 */
    public static byte[] _divideBinary(BigDecimal _firstNumber, BigDecimal _secondNumber)
    {
        byte _binary[] = null;
        try 
        {
            _binary = mg.hr.Binary.toBinary((_firstNumber.divide(_secondNumber)));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return _binary;
    }

//============================================================================

/**
 * Add parameters {@code _firstBinaryNumber} to {@code _secondBinaryNumber}, 
 * <p>Returns an array of byte with length defined in the param {@code _bitNumber}
 * 
 * if {@code _bitNumber} is inferior of {@code _firstBinaryNumber.length} or {@code _secondBinaryNumber.length},
 * <p>it will be set to the superior between _firstBinaryNumber.length and _secondBinaryNumber.length
 * 
 * @param _firstBinaryNumber  {@code byte[]}
 * @param _secondBinaryNumber {@code byte[]}
 * @param _bitNumber          {@code int}
 * 
 * @return byte[]
 * 
 * @see mg.hr.BinaryMath#_completeBinaryNumberInLeft(byte[], int)
 * @see mg.hr.Binary#_reverseBinary(byte[])
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[])
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _addBinary(byte[] _firstBinaryNumber, byte[] _secondBinaryNumber, int _bitNumber)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _firstBinaryNumber)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        for(byte b: _secondBinaryNumber) if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        
        byte a, b;
        int j;
        int up = (_firstBinaryNumber.length < _secondBinaryNumber.length) ? _secondBinaryNumber.length : _firstBinaryNumber.length;
        _bitNumber = (_bitNumber > up) ? _bitNumber : up;

        _firstBinaryNumber = _completeBinaryNumberInLeft(_firstBinaryNumber, _bitNumber);
        _secondBinaryNumber = _completeBinaryNumberInLeft(_secondBinaryNumber, _bitNumber);

        boolean _thereIsZero = false;
        boolean _addOneBit = false;

        if(_bitNumber <= up + 1)
        {
            for(short i = 0; i < _firstBinaryNumber.length; i++)
                {
                    if(_firstBinaryNumber[i] == 0) _thereIsZero = true;
                    if(_firstBinaryNumber[i] == 1 &&  _firstBinaryNumber[i] == _secondBinaryNumber[i]) {
                        _addOneBit = true; 
                        break; 
                    }
                }
        }

        if(!_thereIsZero && _addOneBit) ++_bitNumber;

        byte result[] = new byte[_bitNumber];
        _firstBinaryNumber = _completeBinaryNumberInLeft(_firstBinaryNumber, _bitNumber);
        _secondBinaryNumber = _completeBinaryNumberInLeft(_secondBinaryNumber, _bitNumber);

        for(int i = _bitNumber - 1; i >= 0; i--)
        {
            a = _firstBinaryNumber[i];
            b = _secondBinaryNumber[i];

            if((a == b)) 
            {
                if(a == 0) 
                    result[i] = 0;
                else if(a == 1)
                {
                    j = i;
                    result[i] = 0;
                    while(--j >= 0)
                    {
                        if(_firstBinaryNumber[j] == 1)
                            _firstBinaryNumber[j] = 0;
                        else
                        {
                            _firstBinaryNumber[j] = 1;
                            break;
                        }
                    }
                }
            }
            else result[i] = 1;
        }
        return result;
    }

//============================================================================

/**
 * Same as {@link mg.hr.BinaryMath#_addBinary(byte[], byte[], int)} but without the last parameter {@code _bitNumber}  
 * 
 * @param _firstBinaryNumber        {@code byte[]}
 * @param _secondBinaryNumber       {@code byte[]}
 * 
 * @return {@code byte[]}
 * 
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[], int)
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _addBinary(byte[] _firstBinaryNumber, byte[] _secondBinaryNumber)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _firstBinaryNumber)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        for(byte b: _secondBinaryNumber) if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        
        String message = "Message: number of bit not given, so its automatically setted to: ";
        message += (_firstBinaryNumber.length > _secondBinaryNumber.length) 
                    ? "" + _firstBinaryNumber.length + ", length of _firstBinaryNumber"
                    : "" + _secondBinaryNumber.length + ", length of _secondBinaryNumber";
        System.out.println(message);
        try {
            return (_firstBinaryNumber.length > _secondBinaryNumber.length)
                ? _addBinary(_firstBinaryNumber, _secondBinaryNumber, _firstBinaryNumber.length)
                : _addBinary(_firstBinaryNumber, _secondBinaryNumber, _secondBinaryNumber.length);
        } catch (NotABinaryNumberException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

//============================================================================    

/**
 * Subract parameter {@code _firstBinaryNumber} to parameter {@code _secondBinaryNumber}, 
 * <p>returns an array of byte with length defined in the param {@code _bitNumber}
 * 
 * if {@code _bitNumber} is inferior of {@code _firstBinaryNumber.length} or {@code _secondBinaryNumber.length},
 * <p>it will be set to the superior between _firstBinaryNumber.length and _secondBinaryNumber.length
 *
 * 
 * @param _firstBinaryNumber  {@code byte[]}
 * @param _secondBinaryNumber {@code byte[]}
 * @param _bitNumber          {@code int}
 * @return byte[]
 * 
 * @see mg.hr.BinaryMath#_completeBinaryNumberInLeft(byte[], int)
 * @see mg.hr.Binary#_reverseBinary(byte[])
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _subtractBinary(byte[] _firstBinaryNumber, byte[] _secondBinaryNumber, int _bitNumber)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _firstBinaryNumber)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        for(byte b: _secondBinaryNumber) if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);

        //transform first and second number in decimal
        //if first or second number is a floating number:
        //------------- use _subtractBinary(double, double, int)
        //else continue

        boolean retain = false;
        byte a, b;
        byte result[] = new byte[_bitNumber];
        int _originalBitNumber = _bitNumber;
        int up = (_firstBinaryNumber.length < _secondBinaryNumber.length) ? _secondBinaryNumber.length : _firstBinaryNumber.length;
        _bitNumber = (_bitNumber > up) ? _bitNumber : up;
        _firstBinaryNumber = _completeBinaryNumberInLeft(_firstBinaryNumber, _bitNumber);
        _secondBinaryNumber = _completeBinaryNumberInLeft(_secondBinaryNumber, _bitNumber);

        
        for(int i = _bitNumber - 1; i >= 0; i--)
        {
            a = _firstBinaryNumber[i];
            b = _secondBinaryNumber[i];

            if(retain)
            {
                if(a == b)
                {
                    retain = true;
                    result[i] = 1;
                    continue;
                }
                else
                {
                    retain = (a == 0) ? true : false;
                    result[i] = 0;
                    continue;
                }
            }

            if(a == b)  result[i] = 0;
            else if(a == 1 && b == 0) result[i] = 1;
            else if(a != b)
            {
                if(b == 1)
                {
                    result[i] = 1;
                    retain = true;
                }   
            }
        }
        
        if(_bitNumber == _originalBitNumber) return result;
        else
        {
            result = mg.hr.Binary._reverseBinary(result);
            byte result1[] = new byte[_originalBitNumber];
            for(int k = 0; k < _originalBitNumber; k++)
                result1[k] = result[k];
            return result1;
        }
    }

//============================================================================

/**
 * multiply parameter {@code _firstBinaryNumber} to parameter {@code _secondBinaryNumber}, 
 * <p>returns an array of byte with length defined in the param {@code _bitNumber}
 * 
 * @param _firstBinaryNumber    {@code byte[]}
 * @param _secondBinaryNumber   {@code byte[]}
 * @param _bitNumber            {@code int}
 * 
 * @return                      {@code byte[]}
 * 
 * @throws mg.hr.exception.NotABinaryNumberException if {@code _firstBinaryNumber} and {@code _secomdBinaryNumber} contains another number than 0 or 1
 */
    public static byte[] _multiplyBinary(byte[] _firstBinaryNumber, byte[] _secondBinaryNumber, int _bitNumber)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _firstBinaryNumber)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        for(byte b: _secondBinaryNumber) if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        

        byte[] finalResult = new byte[_firstBinaryNumber.length + _secondBinaryNumber.length];
        for(short i = 0; i < finalResult.length; i++)
            finalResult[i] = 0;

        byte[] _fitstBinaryNumberCopy = _secondBinaryNumber;
        short j = 0;
        for(short i = (short)(_secondBinaryNumber.length - 1); i >= 0; i--)
        {
            // if((_secondBinaryNumber[i] != 1 && _secondBinaryNumber[i] != 0) ||
            //     (_firstBinaryNumber[i] != 1 && _secondBinaryNumber[i] != 0)) {System.out.println("Unaccepted binary number"); return new byte[0];}
            
            if(_secondBinaryNumber[i] == 1)
            {
                _fitstBinaryNumberCopy = mg.hr.BinaryMath._completeBinaryNumberInRight(_firstBinaryNumber, _firstBinaryNumber.length+j);
                finalResult = mg.hr.BinaryMath._addBinary(finalResult, _fitstBinaryNumberCopy);
            }
            j++;
        }
        mg.hr.Binary._displayBinaryNumber(finalResult);

        return finalResult;
    }

//============================================================================

/**
 * Sometimes, user want to represent the result's number of bit superior or inferior of 
 * necessary bit to represent it. This function ask user to change number of bits as recommended<p>
 * Returns {@code true} if user 
 * 
 * @param _originalBitNumber    {@code int}
 * @param _bitNumber            {@code int}
 * @return {@code boolean}
 */
@SuppressWarnings("unused")
    private static boolean _askUserNbrOfBit(int _originalBitNumber, int _bitNumber) {

        java.util.Scanner sc = new java.util.Scanner(java.lang.System.in);
        if(_originalBitNumber < _bitNumber)
            System.out.println("It's recommended to represent the result in " + _bitNumber + " bit(s)");
        if(_originalBitNumber > _bitNumber)
            System.out.println("The result can be reprensented in " + _bitNumber + " bit(s)");
        System.out.println("Do you  want to represent result in = " + _bitNumber + " bit(s) as recommanded?");
        
        char response = 'n';
        do
        {
            System.out.print("\t(y/n) -> ");
            response = sc.nextLine().charAt(0);
        }while(response != 'n' && response != 'y');
        sc.nextLine();
        sc.close();

        return (response == 'y');
    }

//============================================================================

/**
 * used to complete a binary number with 0 in the left, example:<p>
 * for a number in {@code 8} bits:
 * <p> - {@code 1011} becomes {@code 00001011}, 
 * <p> - {@code 1} becomes {@code 00000001}
 * 
 * @param _number {@code byte}
 * @param length {@code int} number of bit
 * @return array of byte with length defined by param length
 * @author {@see https://github.com/Herra-dev}
 * @Deprecated
 */
    public static byte[] _completeBinaryNumber(byte[] _number, int length)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _number)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        
        if(_number.length > length)
        {
            System.out.println("Cannot complete _number because: _number.length > length");
            return _number;
        }

        byte tab[] = new byte[length]; // array to stock the reverse of the  parameter binary _number to complete
        byte tab1[] = new byte[length]; // array to stock the binary number completed

        int j = 0, k = 0;
        for(int i = _number.length -1; i >= 0; i--) tab[j++] = _number[i]; // reversing the binary number and stock it into tab
        for(int i = _number.length; i < length; i++) tab[i] = 0; // adding 0 in tab starting from last bit reversed index until length - 1 
        for(int i = tab.length - 1; i >= 0; i--) tab1[k++] = tab[i]; // reversing tab and stock it to tab1

        return tab1;
    }

//============================================================================

/**
 * used to complete a binary number with 0 in the left, example:<p>
 * for a number in {@code 8} bits:
 * <p> - {@code 1011} becomes {@code 00001011}, 
 * <p> - {@code 1} becomes {@code 00000001}
 * 
 * @param _number {@code byte}
 * @param length {@code int} number of bit
 * 
 * @return array of byte with length defined by param length
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _completeBinaryNumberInLeft(byte[] _number, int length)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _number)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        
        if(_number.length > length)
        {
            System.out.println("Cannot complete _number because: _number.length > length");
            return _number;
        }
        byte tab[] = new byte[length]; // array to stock the reverse of the  parameter binary _number to complete
        byte tab1[] = new byte[length]; // array to stock the binary number completed

        int j = 0, k = 0;
        for(int i = _number.length -1; i >= 0; i--)     tab[j++] = _number[i]; // reversing the binary number and stock it into tab
        for(int i = _number.length; i < length; i++)    tab[i] = 0; // adding 0 in tab starting from last bit reversed index until length - 1 
        for(int i = tab.length - 1; i >= 0; i--)        tab1[k++] = tab[i]; // reversing tab and stock it to tab1

        return tab1;
    }

//============================================================================

/**
 * used to complete a binary number with 0 in the right, example:<p>
 * for a number in {@code 8} bits:
 * <p> - {@code 1011} becomes {@code 10110000}, 
 * <p> - {@code 1} becomes {@code 10000000}
 * 
 * @param _number {@code byte[]}
 * @param length  {@code int} 
 * 
 * @return {@code byte[]}
 * 
 * @throws mg.hr.exception.NotABinaryNumberException if {@code _number} contains something else that 0 or 1
 * 
 * @authro {@see https://github.com/Herra-dev}
 */
    public static byte[] _completeBinaryNumberInRight(byte[] _number, int length)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _number)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        if(_number.length > length)
        {
            System.out.println("Cannot complete _number because: _number.length > length");
            return _number;
        }

        byte[] tab = new byte[length];
        for(short i = 0; i < _number.length; i++)               tab[i] = _number[i];
        for(short i = (short)_number.length; i < length; i++)   tab[i] = 0;

        return tab;
    }

//============================================================================

/**
 * used to complete a binary number with 0 in the right, example:<p>
 * <p> - {@code 1011}(number of bits < 16) becomes {@code 1011_0000_0000_0000}, 
 * <p> - {@code 1}(number of bits < 16) becomes {@code 1000_0000_0000_0000}
 * 
 * @param _number {@code byte[]}
 * 
 * @return {@code byte[]} array of byte with length: 16, 32, 48, 64, 79, 128 or 256 
 * 
 * @author {@see https://github.com/Herra-dev}
 */
    public static byte[] _autoCompleteBinaryNumberInRight(byte[] _number)
    throws mg.hr.exception.NotABinaryNumberException {
        for(byte b: _number)  if(b != 1 && b != 0) throw new mg.hr.exception.NotABinaryNumberException(b);
        int _length = 0;

        // search for length close of _number.length
        int _listPrecision[] = {
            mg.hr.enumeration.FloatPrecision._HALF_PRECISION.getPrecision(),
            mg.hr.enumeration.FloatPrecision._SIMPLE_PRECISION.getPrecision(),
            mg.hr.enumeration.FloatPrecision._EXTENDED_DUAL_PRECISION.getPrecision(),
            mg.hr.enumeration.FloatPrecision._DUAL_PRECISION.getPrecision(),
            mg.hr.enumeration.FloatPrecision._EXTENDED_DUAL_PRECISION.getPrecision(),
            mg.hr.enumeration.FloatPrecision._QUADRUPLE_PRECISION.getPrecision(),
            mg.hr.enumeration.FloatPrecision._OCTUPLE_PRECISION.getPrecision()
        };

        if(_number.length < _listPrecision[0])                                              _length = _listPrecision[0];
        else if(_number.length > _listPrecision[0] && _number.length <= _listPrecision[1])  _length = _listPrecision[1];
        else if(_number.length > _listPrecision[1] && _number.length <= _listPrecision[2])  _length = _listPrecision[2];
        else if(_number.length > _listPrecision[2] && _number.length <= _listPrecision[3])  _length = _listPrecision[3];
        else if(_number.length > _listPrecision[3] && _number.length <= _listPrecision[4])  _length = _listPrecision[4];
        else if(_number.length > _listPrecision[4] && _number.length <= _listPrecision[5])  _length = _listPrecision[5];
        else if(_number.length > _listPrecision[5] && _number.length <= _listPrecision[6])  _length = _listPrecision[6];
        else                                                                                _length = _listPrecision[6];

        System.out.println(_length + " bits...");
        
        byte[] _bit = new byte[_length];
        for(short i = 0; i < _number.length; i++)                _bit[i] = _number[i];
        for(short i = (short)_number.length; i < _length; i++)   _bit[i] = 0;

        return _bit;
    }

//============================================================================

}