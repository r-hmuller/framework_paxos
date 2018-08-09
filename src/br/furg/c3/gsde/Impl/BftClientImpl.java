package br.furg.c3.gsde.Impl;


import bft.src.bftsmart.generic.GenericClient;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BftClientImpl implements ClientInterface {
    private int id;
    private GenericClient genericClient;


    public BftClientImpl(int id)
    {
        this.id = id;
        genericClient = new GenericClient(this.id);
    }

    @Override
    public String sendCommand(String nome, List<String> args)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        String resposta;

        try {
            dataOut.writeUTF(nome);
            dataOut.writeInt(args.size());
            for(String arg : args)
            {
                dataOut.writeUTF(arg);
            }

            byte[]retorno = genericClient.sendCommand(out.toByteArray());
            resposta = new String(retorno, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            resposta = "ERRO!";
        }
        return resposta;
    }
}
