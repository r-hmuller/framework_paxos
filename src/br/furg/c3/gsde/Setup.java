package br.furg.c3.gsde;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by rodrigo on 22/10/17.
 */
public class Setup {

    private volatile static Setup instancia;

    public static Setup getInstancia() {
        if (instancia == null) {
            synchronized (Setup.class) {
                if (instancia == null) instancia = new Setup();
            }
        }
        return instancia;
    }

    private String lib;

    public String setConfig()
    {
        Properties prop = new Properties();
        InputStream input = null;
        int numServers;

        try {

            input = new FileInputStream("/home/rodrigo/TCC/arc_smr/config/config.properties");

            // load a properties file
            prop.load(input);

            this.lib = prop.getProperty("biblioteca");

            numServers = Integer.parseInt(prop.getProperty("numServers"));

            List<String> enderecos = new ArrayList<>();

            for (int i = 0; i < numServers; i++)
            {
                enderecos.add(prop.getProperty("replica."+i));
            }

            //setEspecificProperties(lib, enderecos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this.lib;
    }

    private void setEspecificProperties(String lib, List<String> enderecos)
    {
        int numServers = enderecos.size();

        Properties prop = new Properties();
        InputStream input = null;

        String pathFile = "";

        if(lib.equals("spaxos"))
        {
            pathFile = "/home/rodrigo/TCC/arcabouco2/config/paxos.properties";
            try
            {
                input = new FileInputStream(pathFile);

                prop.load(input);

                int currentServer = 0;
                for(String endereco : enderecos)
                {
                    int porta = Integer.parseInt(endereco.split(":")[1]);
                    int portaCliente = porta + 1000;
                    endereco = endereco + ":"+portaCliente;
                    prop.setProperty("process."+currentServer, endereco);
                }

            }catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        else if(lib.equals("bft"))
        {
            pathFile = "/home/rodrigo/TCC/arcabouco2/config/hosts.config";
            String systemFile = "/home/rodrigo/TCC/arcabouco2/config/system.config";


        }
    }

    public String getReplicaImpl(String lib)
    {
        Properties propReplicas = new Properties();
        InputStream input;

        try {

            input = new FileInputStream("/home/rodrigo/TCC/arc_smr/config/modulosReplica.properties");

            // load a properties file
            propReplicas.load(input);

            return propReplicas.getProperty(lib);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getClientImpl(String lib)
    {
        Properties propReplicas = new Properties();
        InputStream input;

        try {

            input = new FileInputStream("/home/rodrigo/TCC/arc_smr/config/modulosClient.properties");

            // load a properties file
            propReplicas.load(input);

            return propReplicas.getProperty(lib);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
