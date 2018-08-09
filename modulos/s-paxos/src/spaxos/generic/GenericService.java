package spaxos.generic;

import lsr.service.SimplifiedService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenericService extends SimplifiedService {

    private String classe;
    Object instanciaBusinnes = null;
    Class business = null;


    public GenericService(String classe)
    {
        this.classe = classe;
        try {
            this.business = Class.forName(this.classe);
            Class annot = Class.forName("br.furg.c3.gsde.Replicar");

            try {
                Method instance = business.getDeclaredMethod("getInstance");
                this.instanciaBusinnes = instance.invoke(null, null);
            } catch (NoSuchMethodException e) {
                try {
                    this.instanciaBusinnes = business.getConstructor().newInstance();
                } catch (InstantiationException |IllegalAccessException | NoSuchMethodException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    protected byte[] execute(byte[] commands) {
        byte[] resposta = new byte[0];
        try {
            GenericCommand genericCommand = new GenericCommand(commands);

            String comando = (String) genericCommand.getKey("name");
            int qtdeArgs = (int) genericCommand.getKey("qtdeArgs");

            Object[] argumentos = new String[qtdeArgs];
            Class[] classArgs = new Class[qtdeArgs];
            for(int i = 0; i< qtdeArgs; i++)
            {
                argumentos[i] = genericCommand.getKey("arg"+i);
                classArgs[i] = Object.class;
            }

            Method method = business.getDeclaredMethod(comando, classArgs);

            Class<?> returnMethod = method.getReturnType();

            String invMethod = (String) method.invoke(instanciaBusinnes, argumentos);

            resposta = invMethod.getBytes();
        } catch (NoSuchMethodException |IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return resposta;
    }

    @Override
    protected byte[] makeSnapshot() {
        return new byte[0];
    }

    @Override
    protected void updateToSnapshot(byte[] snapshot) {

    }
}