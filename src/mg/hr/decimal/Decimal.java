//========================================================================================
// Decimal
//========================================================================================
//
// THIS CLASS IS USED TO TRANSFORM A NUMBER IN BINARY INTO DECIMAL
//========================================================================================

package mg.hr.decimal;

import mg.hr.exception.NotABinaryNumberException;

public abstract class Decimal 
{

/**
 * {@code NB:} The parameter {@code _bit} will be {@code treated} as an {@code unsigned integer}<p>
 * even if its a floating number or signed number
 * 
 * @param _bit {@code byte[]} the number in binary to transform
 * @return {@code double} the representation of {@code _bit} in decimal
 * @see mg.hr.Binary#_reverseBinary(byte[])
 * @see mg.hr.decimal.Decimal#_toDecimalSignedInteger(byte[])
 * @author {@see https://github.com/Herra-dev}
 */
    public static double _toDecimalUnsignedInteger(byte[] _bit)
    {
        byte[] _bitUnsignedInteger = mg.hr.Binary._reverseBinary(_bit);
        int _UInteger = 0;
        for(int i = 0; i < _bitUnsignedInteger.length; i++)
        {
            if(_bitUnsignedInteger[i] == 1)
                _UInteger += java.lang.StrictMath.pow(2, i);
        }

        return _UInteger;
    }

//========================================================================================

/**
 * {@code NB:} The parameter {@code _bit} will be {@code treated} as an {@code signed integer}<p>
 * even if its a floating number or unsigned integer
 * 
 * @param _bit {@code byte[]} the number in binary to transform
 * @return {@code double} the representation of {@code _bit} in decimal
 * @see mg.hr.Binary#_complementBinary(byte[])
 * @see mg.hr.BinaryMath#_addBinary(byte[], byte[], int)
 * @see mg.hr.decimal.Decimal#_toDecimalUnsignedInteger(byte[])
 * @author {@see https://github.com/Herra-dev}
 */
    public static double _toDecimalSignedInteger(byte[] _bit)
    {

        byte[] _bitOne = new byte[_bit.length];

        for(byte i = 0; i < _bitOne.length; i++)
            _bitOne[i] = 0;
        _bitOne[_bitOne.length - 1] = 1;

        _bit = mg.hr.Binary._complementBinary(_bit);
        try {
            _bit = mg.hr.BinaryMath._addBinary(_bit, _bitOne, _bit.length);
        } catch (NotABinaryNumberException e) {
            e.printStackTrace();
        }

        double _SInteger = _toDecimalUnsignedInteger(_bit);
        
        return -_SInteger;
    }

//========================================================================================

/**
 * {@code NB:} The parameter {@code _bit} will be {@code treated} as an {@code floating point number}<p>
 * even if its an unsigned or signed integer 
 * 
 * <p>this function works in 4 steps:
 * <p>{@code first:} searching for the sign
 * <p>{@code second:} searching for the floor
 * <p>{@code third:} searching for decimal part(number after comma)
 * <p>{@code fourth:} assembling all data gathered
 * 
 * @param _bit {@code byte[]}
 * @return {@code double} the representation of {@code _bit} in decimal
 * @see mg.hr.decimal.Decimal#_getFloorNumber(byte[])
 * @see mg.hr.decimal.Decimal#_getDecimalPart(byte[])
 * @author {@see https://github.com/Herra-dev}
 */
    public static double _toDecimalFloat(byte[] _bit)
    {
        int _bitNumber = _bit.length;
        short[] availableRepresentation = {16, 32, 48, 64, 79, 128, 256};
        boolean proceed = false;

        for(short i: availableRepresentation)
            if(i == _bitNumber)
            {
                proceed = true;     break;
            }
                

        //IF _bit.length IS AVAILABLE: PROCEED TRANSFORMATION
        if(proceed)
        {
            boolean positive = (_bit[0] == 0) ? true : false; //==== SIGN
            double floor = _getFloorNumber(_bit);             //==== FLOOR
            double decimalPart = _getDecimalPart(_bit);       //==== DECIMAL PART

            return (positive) ? (floor+decimalPart) : -(floor+decimalPart);
        }
        else
        {
            System.err.println("Representation not available, due to:\n" +
                "\t- length of _bit = " + _bit.length + ", could not be represented"
            );
            System.out.println("_bit.length must be: ");
            for(short s: availableRepresentation)
                System.out.println("\t- " + s);
            System.out.println("trying to resolve problem...");
            System.out.print("trying to apply solution: transform _bit into bit of : ");
            
            try {
                // trying to complete binary
                return _toDecimalFloat(mg.hr.BinaryMath._autoCompleteBinaryNumberInRight(_bit));
            } catch (NotABinaryNumberException e) {
                e.printStackTrace();
                return 0.0d;
            }
        }
    }

//========================================================================================

/**
 * Returns the precision of {@code _bit} 
 * 
 * @param _bit {@code byte[]}
 * @return {@link mg.hr.enumeration.FloatPrecision}
 * @author {@see https://github.com/Herra-dev}
 */
    private static mg.hr.enumeration.FloatPrecision _getBinaryNumberPrecision(byte[] _bit)
    {
        short _bitNumber = (short)_bit.length;
        mg.hr.enumeration.FloatPrecision _nbrPrecision = mg.hr.enumeration.FloatPrecision._HALF_PRECISION;
        switch (_bitNumber) {
            case 16:  _nbrPrecision = mg.hr.enumeration.FloatPrecision._HALF_PRECISION;              break;
            case 32:  _nbrPrecision = mg.hr.enumeration.FloatPrecision._SIMPLE_PRECISION;            break;
            case 48:  _nbrPrecision = mg.hr.enumeration.FloatPrecision._EXTENDED_SIMPLE_PRECISION;   break;
            case 64:  _nbrPrecision = mg.hr.enumeration.FloatPrecision._DUAL_PRECISION;              break;
            case 79:  _nbrPrecision = mg.hr.enumeration.FloatPrecision._EXTENDED_DUAL_PRECISION;     break;
            case 128: _nbrPrecision = mg.hr.enumeration.FloatPrecision._QUADRUPLE_PRECISION;         break;
            case 256: _nbrPrecision = mg.hr.enumeration.FloatPrecision._OCTUPLE_PRECISION;           break;
            default: break;
        }

        return _nbrPrecision;
    }

//========================================================================================

/**
 * Returns the exponent of _bit
 * 
 * @param _bit {@code byte[]}
 * @return {@code double}
 * @author {@see https://github.com/Herra-dev}
 */
    private static double _getBinaryNumberExponent(byte[] _bit)
    {
        mg.hr.enumeration.FloatPrecision _nbrPrecision = _getBinaryNumberPrecision(_bit);
        byte[] _exponent = new byte[_nbrPrecision.getExpNumber()];
        for(int i = 0; i < _exponent.length; i++)
            _exponent[i] = _bit[i+1];
        
        return _toDecimalUnsignedInteger(_exponent);
    }

//========================================================================================

/**
 * Returns the floor number of {@code _bit}
 * 
 * @param _bit {@code byte[]}
 * @return {@code double}
 * @author {@see https://github.com/Herra-dev}
 */
    private static double _getFloorNumber(byte[] _bit)
    {
        mg.hr.enumeration.FloatPrecision _precision = _getBinaryNumberPrecision(_bit);
        double _exponent = mg.hr.decimal.Decimal._getBinaryNumberExponent(_bit);
        boolean _expPositive = (_exponent >= _precision.getBiais()) ? true : false;
        _exponent -= _precision.getBiais();
        _exponent = (_expPositive) ? java.lang.StrictMath.abs(_exponent) : -(java.lang.StrictMath.abs(_exponent));

        if(!_expPositive) return 0; // RETURN 0, IF EXPONENT IS A NEGATIVE VALUE

        byte[] _floor = new byte[(int)_exponent+1];
        _floor[0] = 1;
        int start = _precision.getExpNumber() + 1;
        for(int i = 0; i < _exponent; i++)
            _floor[i + 1] = _bit[start++];

        return _toDecimalUnsignedInteger(_floor);
    }

//========================================================================================

/**
 * Returns _bit's decimal part in decimal
 * 
 * @param _bit {@code byte[]}
 * @return {@code double}
 * @author {@see https://github.com/Herra-dev}
 */
    private static double _getDecimalPart(byte[] _bit)
    {
        mg.hr.enumeration.FloatPrecision _precision = _getBinaryNumberPrecision(_bit);
        double _exponent = _getBinaryNumberExponent(_bit);
        _exponent -= _precision.getBiais();
        byte _decimalPart[] = null;

        if(_exponent >= 0) // floor number, not null
        {
            short start = (short)(1 + _precision.getExpNumber() + _exponent);
            short _decimalPartBitNumber = 0;
            for(int i = start; i < _bit.length; i++) _decimalPartBitNumber++;
            _decimalPart = new byte[_decimalPartBitNumber];

            for(short i = 0; i < _decimalPart.length; i++)
                _decimalPart[i] = _bit[start++];

            double nbr = 0;
            for(short j = 0; j < _decimalPart.length; j++)
            {
                if(_decimalPart[j] == 1)
                    nbr += java.lang.StrictMath.pow(2, -(j+1));
            }

            return nbr;
            
        }
        else // floor number, null
        {
            short start = (short)(1 + _precision.getExpNumber() + _exponent);
            short _decimalPartBitNumber = 0;
            for(int i = start; i < _bit.length; i++) _decimalPartBitNumber++;
            _decimalPart = new byte[_decimalPartBitNumber];

            _exponent = java.lang.StrictMath.abs(_exponent);
            for(short i = 0; i < _exponent - 1; i++)
            {
                _decimalPart[i] = 0;
            }
            _decimalPart[(short)_exponent -1] = 1;

            short st = (short)((_precision.getExpNumber()));
            for(short i = (short)_exponent; i < _decimalPart.length; i++)
            {
                if(st++ > _bit.length)  break;
                _decimalPart[i] = _bit[st];
            }

            double nbr = 0;
            for(short j = 0; j < _decimalPart.length; j++)
            {
                if(_decimalPart[j] == 1)
                    nbr += java.lang.StrictMath.pow(2, -(j+1));
            }

            return nbr;
        }
    }
}