# language: pt
@regressivo
Funcionalidade: Cadastro de novo usuario
  Quero cadastrar um novo usuario
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido
    Dado que eu tenha os seguintes dados da entrega:
      | campo     | valor         |
      | nome      | ADMIN11        |
      | telefone  | 46999827114   |
      | email     | admin44@admin  |
      | senha     | adminadmin4   |
      | role      | ADMIN         |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios sem auth
    Então o status code da resposta deve ser 201


  @padrão
  Cenário: Cadastro sem sucesso ao passar campo invalido
    Dado que eu tenha os seguintes dados da entrega:
      | campo     | valor         |
      | nome      | ADMIN10        |
      | telefone  | 46999827114   |
      | email     | admin40admin   |
      | senha     | adminadmin4   |
      | role      | ADMIN         |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios sem auth
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "O e-mail do usuário não é valido!"