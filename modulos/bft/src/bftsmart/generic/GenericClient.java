package bft.src.bftsmart.generic;

import bftsmart.tom.ServiceProxy;


public class GenericClient {
    ServiceProxy counterProxy = null;
    public GenericClient(int id)
    {
        counterProxy = new ServiceProxy(id);
    }

    public byte[] sendCommand(byte[] command)
    {
        return counterProxy.invokeOrdered(command);
    }

}
