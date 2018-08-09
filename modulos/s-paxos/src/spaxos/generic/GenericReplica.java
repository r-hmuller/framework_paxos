package spaxos.generic;

import lsr.common.Configuration;
import lsr.paxos.ReplicationException;
import lsr.paxos.replica.Replica;

import java.io.IOException;

public class GenericReplica {
    public void init(int id, String classe) throws IOException, ReplicationException
    {
        GenericService genericService = new GenericService(classe);

        Replica replica = new Replica(new Configuration(), id, genericService);

        replica.start();
    }
}