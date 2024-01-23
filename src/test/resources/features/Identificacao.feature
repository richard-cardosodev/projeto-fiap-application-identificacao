# language: pt
Funcionalidade: Identificacao

  Cenario: Criar um novo cliente
    Quando submeter um novo cliente
    Entao o cliente foi registrado com sucesso

  Cenario: Alterar um cliente existente
    Dada que um cliente ja foi registrado
    Quando requisitar a alteracao do cliente
    Entao o cliente foi alterado com sucesso

  Cenario: Excluir um cliente existente
    Dada que um cliente ja foi registrado
    Quando requisitar a exclusao do cliente
    Entao o cliente eh excluido com sucesso

  Cenario: Busca uma identificacao existente
    Dada que um cliente ja foi registrado
    Quando requisitar a busca do cliente
    Entao o cliente eh exibido com sucesso

  Cenario: Busca identificacao por cpf
    Dada que um cliente ja foi registrado
    Quando requisitar a busca de um cliente pelo cpf
    Entao o cliente eh exibido com sucesso

  Cenario: Lista identificacoes existentes
    Dada que um cliente ja foi registrado
    Quando requisitar a lista de clientes
    Entao os clientes sao exibidos com sucesso
