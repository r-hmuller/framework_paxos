package br.furg.c3.gsde.Impl;

import java.lang.reflect.InvocationTargetException;
import bftsmart.generic.GenericReplica;

public class BftReplicaImpl implements ReplInterface {
    @Override
    public void init(int id, String classe)
    {
        try {
            GenericReplica genericReplica = new GenericReplica(id, classe);

            /*//ORDEM: <Classe> <id>
            String repli = "bftsmart.generic.GenericReplica";


            Class<?> replicaGenerica = Class.forName(repli);
            Class[] cArg = new Class[2];
            cArg[0] = int.class;
            cArg[1] = String.class;
            replicaGenerica.getDeclaredConstructor(cArg).newInstance(id, classe);
*/
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
