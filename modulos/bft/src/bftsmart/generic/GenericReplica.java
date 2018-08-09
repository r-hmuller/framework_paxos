package bftsmart.generic;

import bftsmart.tom.ServiceReplica;
import bftsmart.tom.MessageContext;
import bftsmart.tom.server.defaultservices.DefaultRecoverable;

import java.io.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenericReplica extends DefaultRecoverable {

    ServiceReplica replica = null;
    Object instanciaBusinnes = null;
    Class business = null;

    public GenericReplica(int id, String classe) throws IllegalAccessException, InstantiationException {
        try {
            this.business = Class.forName(classe);
            Class annot = Class.forName("br.furg.c3.gsde.Replicar");


            try {
                Method instance = business.getDeclaredMethod("getInstance");
                this.instanciaBusinnes = instance.invoke(null, null);
            } catch (NoSuchMethodException e) {
                this.instanciaBusinnes = business.newInstance();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //Aqui é pra registrar automaticamente numa tabela
            for (Method method : business.getMethods())
            {
                if (method.isAnnotationPresent(annot))
                {
                    System.out.println(method.getParameterCount());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        replica = new ServiceReplica(id, this, this);
    }

    private byte[] executeSingle(byte[] command, MessageContext msgCtx)
    {
        ByteArrayInputStream in = new ByteArrayInputStream(command);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataInputStream dIn = new DataInputStream(in);
        DataOutputStream dOut = new DataOutputStream(out);
        byte[] reply = null;

        try {
            String comando = dIn.readUTF();
            int qtdeArgs = dIn.readInt();

            String[] argumentos = new String[qtdeArgs];
            Class[] classArgs = new Class[qtdeArgs];
            for(int i = 0; i< qtdeArgs; i++)
            {
                argumentos[i] = dIn.readUTF();
                classArgs[i] = String.class;
            }

            Method method = business.getDeclaredMethod(comando, classArgs);
            Class<?> returnMethod = method.getReturnType();

            String invMethod = (String) method.invoke(instanciaBusinnes, argumentos);

            //dOut.writeUTF(returnMethod.getName());
            System.out.println("Retorno método: " +invMethod);
            dOut.writeUTF(invMethod);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Cheguei aqui");

        return out.toByteArray();
    }


    @Override
    public void installSnapshot(byte[] state) {
        //TODO
    }

    @Override
    public byte[] getSnapshot() {
        return new byte[0];
    }

    @Override
    public byte[] appExecuteUnordered(byte[] command, MessageContext msgCtx) {
        return new byte[0];
    }

    @Override
    public byte[][] appExecuteBatch(byte[][] commands, MessageContext[] msgCtxs) {
        byte [][] replies = new byte[commands.length][];
        for (int i = 0; i < commands.length; i++) {
            if(msgCtxs != null && msgCtxs[i] != null) {
                replies[i] = executeSingle(commands[i],msgCtxs[i]);
            }
            else executeSingle(commands[i],null);
        }

        return replies;
    }

}
