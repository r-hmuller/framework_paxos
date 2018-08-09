package br.furg.c3.gsde.Services;

import br.furg.c3.gsde.Replicar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SegundoTeste {

    private Map<String, String> bd;

    public SegundoTeste()
    {
        this.bd = new ConcurrentHashMap<>();
    }

    @Replicar
    public String get(String key)
    {
        return "get";
    }

    @Replicar
    public String save(String key, String value)
    {
        bd.put(key, value);
        System.out.println("O valor salvo foi: " + bd.get(key));
        return "ok";
    }

}
