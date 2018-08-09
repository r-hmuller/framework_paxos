package br.furg.c3.gsde.Impl;

import spaxos.generic.GenericReplica;
import lsr.paxos.ReplicationException;

import java.io.IOException;

public class SPaxosReplImpl implements ReplInterface {
    @Override
    public void init(int id, String classe) {
        GenericReplica genericReplica = new GenericReplica();
        try {
            genericReplica.init(id, classe);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ReplicationException e) {
            e.printStackTrace();
        }

    }
}
