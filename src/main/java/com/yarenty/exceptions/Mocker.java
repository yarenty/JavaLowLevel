package com.yarenty.exceptions;

import java.sql.SQLException;

/**
 * Created by yarenty on 19/07/2016.
 * (C)2015 SkyCorp Ltd.
 */
public class Mocker<T extends Exception> {

    private void pleaseThrow(final Exception t) throws T {
        throw (T) t;
    }

    public static void main(final String[] args) {

        try {
            new Mocker<RuntimeException>().pleaseThrow(new SQLException());

//        }   catch (SQLException ex) {  //- even if SQLException will be thrown
            //compiler cannot get it - so this code will not compile ;-(

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}


