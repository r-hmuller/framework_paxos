package br.furg.c3.gsde.Impl;

import java.util.List;

public interface ClientInterface {

    String sendCommand(String nome, List<String> args);
}
