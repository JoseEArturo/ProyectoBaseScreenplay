package com.youtube.models;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NombreCancion {
    String nombreCancion;

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public static List<NombreCancion> setData(DataTable dataTable) {
        List<NombreCancion> datos = new ArrayList<>();
        List<Map<String,String>> mapList = dataTable.asMaps(String.class, String.class);
        ObjectMapper mapper = new ObjectMapper();

        for (Map<String, String> map : mapList){
            datos.add(mapper.convertValue(map, NombreCancion.class));
        }
        return datos;
    }
}
