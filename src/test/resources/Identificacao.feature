# language: pt
Funcionalidade: Identificação

  Cenário: Criar um novo cliente
    Quando submeter um novo cliente
    Então o cliente foi registrado com sucesso

  Cenário: Alterar um cliente existente
    Dada que um cliente já foi registrado
    Quando requisitar a alteração do cliente
    Então o cliente foi alterado com sucesso

  Cenário: Excluir um cliente existente
    Dada que um cliente já foi registrado
    Quando requisitar a exclusão do cliente
    Então o cliente é excluido com sucesso

  Cenário: Busca uma identificação existente
    Dada que um cliente já foi registrado
    Quando requisitar a busca do cliente
    Então o cliente é exibido com sucesso

  Cenário: Busca identificação por cpf
    Dada que um cliente já foi registrado
    Quando requisitar a busca de um cliente pelo cpf
    Entao o cliente é exibido com sucesso

  Cenário: Lista identificacoes existentes
    Dada que um cliente já foi registrado
    Quando requisitar a lista de clientes
    Então os clientes são exibidos com sucesso