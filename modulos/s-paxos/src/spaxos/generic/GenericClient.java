package spaxos.generic;

import lsr.paxos.ReplicationException;
import lsr.paxos.client.Client;

import java.io.IOException;

public class GenericClient {

    Client client;
    GenericCommand genericCommand;

    public GenericClient() throws IOException {
        this.client = new Client();
        this.genericCommand = new GenericCommand();
    }

    public byte[] sendCommand(Object... args) throws IOException, ReplicationException {
        this.client.connect();
        byte[] request = this.genericCommand.toByteArray(args);

        byte[] response = client.execute(request);

        return response;
    }
}
