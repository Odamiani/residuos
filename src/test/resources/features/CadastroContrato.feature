# language: pt
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido
  Como usuário da API
  Quero cadastrar um novo usuario bem-sucedido
  Para que eu cosniga validar se o contrato esta conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido
    Dado que eu tenha os seguintes dados da entrega:
      | campo     | valor               |
      | nome      | sou apenas um teste |
      | telefone  | 46999827114         |
      | email     | teste38@gmail.com    |
      | senha     | 12345678            |
      | role      | USER                |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios sem auth
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado