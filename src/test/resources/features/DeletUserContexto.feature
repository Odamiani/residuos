# language: pt
Funcionalidade: Deletar um usuario
  Como usuário da API
  Quero conseguir deletar um usuario
  Para que o registro seja salvo corretamente no sistema
  Contexto: Cadastro bem-sucedido
    Dado que eu tenha os seguintes dados da entrega:
      | campo     | valor         |
      | nome      | ADMIN16        |
      | telefone  | 46999827114   |
      | email     | admin16@admin  |
      | senha     | adminadmin4   |
      | role      | ADMIN         |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios sem auth
    Então o status code da resposta deve ser 201

  Cenário: Deve ser possível deletar um usuario
    Dado que eu recupere o ID do usuario criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/usuarios" de deleção de entrega
    Então o status code da resposta deve ser 204