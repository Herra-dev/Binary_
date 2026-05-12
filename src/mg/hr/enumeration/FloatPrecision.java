//ENUMERATION USED TO PRECISE FLOAT REPRESENTATION

package mg.hr.enumeration;

public enum FloatPrecision
{
    _HALF_PRECISION("_HALF_PRECISION", 16, 5),
    _SIMPLE_PRECISION("_SIMPLE_PRECISION",32, 8),
    _EXTENDED_SIMPLE_PRECISION("_EXTENDED_SIMPLE_PRECISION",48, 11),
    _DUAL_PRECISION("_DUAL_PRECISION",64, 11),
    _EXTENDED_DUAL_PRECISION("_EXTENDED_DUAL_PRECISION",79, 15),
    _QUADRUPLE_PRECISION("_QUADRUPLE_PRECISION",128, 15),
    _OCTUPLE_PRECISION("_OCTUPLE_PRECISION",256, 19);

    protected String _name;
    protected int _precision;
    protected int _expNumber;
    protected int _biais;

    FloatPrecision(String name, int precision, int expNumber)
    {
        this._name = name;
        this._precision = precision;
        this._expNumber = expNumber;
        this._biais = (int)(java.lang.StrictMath.pow(2, this._expNumber - 1) - 1);
    }

    public String getName() { return this._name; }

    public int getPrecision() { return this._precision; }

    public int getExpNumber() { return this._expNumber; }

    public int getBiais() { return this._biais; }

    public static FloatPrecision[] getAvailablePrecision()
    {
        FloatPrecision[] _available = new FloatPrecision[7];
        _available[0] = _HALF_PRECISION;
        _available[1] = _SIMPLE_PRECISION;
        _available[2] = _EXTENDED_SIMPLE_PRECISION;
        _available[3] = _DUAL_PRECISION;
        _available[4] = _EXTENDED_DUAL_PRECISION;
        _available[5] = _QUADRUPLE_PRECISION;
        _available[6] = _OCTUPLE_PRECISION;
        return _available;
    }

    public static void seeAvailablePrecision()
    {
        for(mg.hr.enumeration.FloatPrecision fp : getAvailablePrecision())
            System.out.println(fp.getPrecision() + " - " + fp.getName());
    }
}