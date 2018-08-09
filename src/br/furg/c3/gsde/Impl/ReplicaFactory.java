package br.furg.c3.gsde.Impl;

import br.furg.c3.gsde.Setup;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

public class ReplicaFactory {
    @Nullable
    public static ReplInterface getReplica(String lib)
    {
        String classe = Setup.getInstancia().getReplicaImpl(lib);

        try {
            ReplInterface repl = (ReplInterface) Class.forName(classe).getConstructor().newInstance();

            return repl;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
