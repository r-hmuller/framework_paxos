package spaxos.generic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GenericCommand {

    private Map<String, Object> map;

    public GenericCommand(){ }

    public GenericCommand(byte[] commands)
    {
        map = new HashMap<>();

        ObjectInputStream dIn;
        try {
            dIn = new ObjectInputStream(new ByteArrayInputStream(commands));

            String name = dIn.readUTF();
            map.put("name", name);

            int qtdeArgs = dIn.readInt();
            map.put("qtdeArgs", qtdeArgs);

            Object[] args = new String[qtdeArgs];
            for(int i = 0; i < qtdeArgs; i++)
            {
                args[i] = dIn.readObject();
                map.put("arg"+i, args[i]);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public byte[] toByteArray(Object... args) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);

        objOut.writeUTF((String) args[0]);
        //Pegar automaticamente o número de args. Cliente não precisa informar
        objOut.writeInt((args.length - 1)); //Um é o nome
        for(int i = 1; i < args.length; i++)
        {
            objOut.writeObject(args[i]);
        }

        return out.toByteArray();
    }

    public Object getKey(String key)
    {
        return map.get(key);
    }
}