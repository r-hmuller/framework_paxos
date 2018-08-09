#README

## Objetivo

O objetivo deste arcabouço é permitir abstrair detalhes de implementações específicas de Paxos (futuramente, talvez outros protocolos), focando-se somente no serviço a ser replicado (por exemplo, um banco de dados chave-valor em memória).


## Bibliotecas suportadas

Atualmente, o arcabouço suporta duas implementações (em Java):

* BFT-SMaRt
* S-Paxos

Porém, a inserção de novas bibliotecas não deve ser tarefa complexa.

## Como rodar

Tenho que fazer o build.xml para fazer a compilação automaticamente.

## Fazendo modificações nos módulos

Caso queira modificar os módulos, sinta-se à vontade. 

Porém, no caso do BFT-SMaRt, alguns arquivos foram modificados (hard-coded) para definir corretamente o caminho dos arquivos de configuração. Portanto, deve-se atualizar os arquivos adequadamente:

Em módulos/bft/src/bftsmart/reconfiguration, modificar os seguintes arquivos: 

*  util/Configuration.java, método loadConfig.
*  util/HostsConfig.java, método HostsConfig.
*  views/DefaultViewStorage.java, método DefaultViewStorage.