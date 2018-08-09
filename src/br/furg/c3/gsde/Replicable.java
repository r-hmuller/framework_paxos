package br.furg.c3.gsde;

import java.util.List;

public interface Replicable {

    List<String> getCommands();

    void addCommand(String nome, String retorno, List<String> args);
}
