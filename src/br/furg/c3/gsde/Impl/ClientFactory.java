package br.furg.c3.gsde.Impl;


public class ClientFactory {
    public static ClientInterface getClient(String lib, int idProcesso)
    {
        if(lib.equals("bft"))
        {
            return new BftClientImpl(idProcesso);

        }
        else if (lib.equals("spaxos"))
        {
            return new SPaxosClientImpl();
        }
        else
        {
            return null;
        }
    }
}
