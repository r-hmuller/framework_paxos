package br.furg.c3.gsde;

import br.furg.c3.gsde.Impl.ClientFactory;
import br.furg.c3.gsde.Impl.ClientInterface;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        Setup setup = Setup.getInstancia();
        String lib = setup.setConfig();

        int idProcesso = Integer.parseInt(args[1]);

        ClientInterface client = ClientFactory.getClient(lib, idProcesso);

        String nome = "get";
        List<String> argumentos = new ArrayList<>();
        argumentos.add("Teste 1");
        //argumentos.add("Teste 2");
        String retorno = client.sendCommand(nome, argumentos);
        System.out.println("Retorno: "+retorno);

        System.exit(0);
    }
}
