# language: pt
Funcionalidade: Cadastro de novo usuario
  Quero cadastrar um novo usuario
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido
    Dado que eu tenha os seguintes dados da entrega:
      | campo     | valor         |
      | nome      | ADMIN4        |
      | telefone  | 46999827114   |
      | email     | admin4@admin  |
      | senha     | adminadmin4   |
      | role      | ADMIN         |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios sem auth
    Então o status code da resposta deve ser 201