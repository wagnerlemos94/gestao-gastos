package com.digitadasistemas.gestaogastos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Ultil {

    public static Date formataData(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.parse(data);
    }
}
