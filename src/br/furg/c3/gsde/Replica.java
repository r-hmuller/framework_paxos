package br.furg.c3.gsde;


import br.furg.c3.gsde.Impl.BftReplicaImpl;
import br.furg.c3.gsde.Impl.ReplInterface;
import br.furg.c3.gsde.Impl.ReplicaFactory;
import br.furg.c3.gsde.Impl.SPaxosReplImpl;

public class Replica {
    public static void main(String[] args) {
        Setup setup = Setup.getInstancia();
        String lib = setup.setConfig();

        String classe = args[0];
        int id = Integer.parseInt(args[1]);

        ReplInterface replica = ReplicaFactory.getReplica(lib);
        replica.init(id, classe);

    }
}
