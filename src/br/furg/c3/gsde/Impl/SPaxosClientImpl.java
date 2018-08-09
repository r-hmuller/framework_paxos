package br.furg.c3.gsde.Impl;

import spaxos.generic.GenericClient;

import java.io.IOException;
import java.util.List;

public class SPaxosClientImpl implements ClientInterface {

    private GenericClient genericClient;

    public SPaxosClientImpl()
    {
        try {
            this.genericClient = new GenericClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sendCommand(String nome, List<String> args) {
        return "";
    }
}
