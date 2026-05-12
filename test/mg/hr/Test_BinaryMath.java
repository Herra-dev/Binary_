package test.mg.hr;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import mg.hr.exception.NotABinaryNumberException;


public class Test_BinaryMath
{
    @Test
    public void _test_addBinaryNoBitNumber()
    {
        try {
            byte[] _bit00_ = {0, 0, 0, 0, 0, 1};
            byte[] _bit01_ = {0, 0, 0, 0, 0, 1};
            byte[] _excepted0 = {0, 0, 0, 0, 1, 0};
            assertArrayEquals(_excepted0, mg.hr.BinaryMath._addBinary(_bit00_, _bit01_));
            _bit00_ = null;
            _bit01_ = null;
            _excepted0 = null;

            byte[] _bit10_ = {0, 0, 0, 0, 1, 1};
            byte[] _bit11_ = {0, 0, 0, 0, 0, 1};
            byte[] _excepted1 = {0, 0, 0, 1, 0, 0};
            assertArrayEquals(_excepted1, mg.hr.BinaryMath._addBinary(_bit10_, _bit11_));
            _bit00_ = null;
            _bit01_ = null;
            _excepted1 = null;

            byte[] _bit20_ = {1, 1, 0, 1, 0, 1};
            byte[] _bit21_ = {0, 0, 0, 0, 1, 1};
            byte[] _excepted2 = {1, 1, 1, 0, 0, 0};
            assertArrayEquals(_excepted2, mg.hr.BinaryMath._addBinary(_bit20_, _bit21_));
            _bit00_ = null;
            _bit01_ = null;
            _excepted2 = null;



        } catch (NotABinaryNumberException e) {
            e.printStackTrace();
        }
    }
}