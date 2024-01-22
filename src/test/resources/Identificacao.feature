# language: pt
Funcionalidade: Identificação

  Cenário: Criar uma nova identificação
    Quando submeter uma nova identificação
    Então a identificação foi registrada com sucesso

  Cenário: Alterar uma identificação existente
    Dada que uma identificação já foi registrada
    Quando requisitar a alteração da identificação
    Então a identificação foi alterada com sucesso

  Cenário: Excluir uma identificação existente
    Dada que uma identificação já foi registrada
    Quando requisitar a exclusão da identificação
    Então a identificação é excluida com sucesso

  Cenário: Busca uma identificação existente
    Dada que uma identificação já foi registrada
    Quando requisitar a busca da identificação
    Então a identificação é exibida com sucesso

  Cenário: Busca identificação por cpf
    Dada que uma identificação já foi registrada
    Quando requisitar a busca por uma identificação pelo cpf
    Entao a identificação é exibida com sucesso

  Cenário: Lista identificacoes existentes
    Dada que uma identificação já foi registrada
    Quando requisitar a lista de identificacoes
    Então as identificações são exibidas com sucesso