package com.caiobraz.servidorapi.util;

import java.time.LocalDate;
import java.time.Period;

public class IdadeUtils {

    private IdadeUtils() {
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
