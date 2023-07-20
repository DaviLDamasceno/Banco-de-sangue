# Banco-de-sangue

## Descrição

Este repositório contém um programa Java que implementa uma tela de login e uma interface para o gestor central de bancos de sangue. O programa utiliza a biblioteca Swing para a criação da interface gráfica.

## Requisitos

- Java Development Kit (JDK) 8 ou superior
- IDE de desenvolvimento Java (Eclipse, IntelliJ, NetBeans, etc.)

## Instruções de Uso

1. Faça o clone ou o download deste repositório.
2. Abra o projeto em sua IDE de desenvolvimento Java.
3. Compile e execute a classe `TelaLogin` para iniciar a tela de login.

## Funcionalidades

### Tela de Login

A Tela de Login é a interface de autenticação para acesso ao sistema. Existem dois tipos de usuários:

1. Usuário normal: Pode acessar as funções específicas de cada banco de sangue.
2. Gestor Central: Pode gerenciar os bancos de sangue.

Ao iniciar o programa, a Tela de Login é exibida, onde é possível inserir o nome de usuário e senha. Os seguintes usuários estão disponíveis:

- Usuário Normal: Informe o nome de um banco de sangue cadastrado no arquivo "bancos_de_sangue.txt" e insira o mesmo valor para a senha.
- Gestor Central: Informe "admin" tanto para o nome de usuário quanto para a senha.

Após fazer o login, o programa permite que o usuário normal visualize as doações do banco selecionado e solicite novas doações. O Gestor Central tem acesso a funcionalidades adicionais para adicionar, modificar e excluir bancos de sangue, além de transferir doações entre bancos.

### Funcionalidades do Gestor Central

O Gestor Central tem acesso a várias funcionalidades para gerenciar os bancos de sangue:

- Adicionar Banco de Sangue: Permite adicionar um novo banco de sangue ao sistema. Após a inclusão, ele estará disponível para os usuários normais.
- Excluir Banco de Sangue: Permite excluir um banco de sangue do sistema. As doações associadas a esse banco também serão excluídas.
- Modificar Banco de Sangue: Permite modificar o nome de um banco de sangue já cadastrado no sistema.
- Transferir Doação: Permite transferir uma doação de sangue de um banco para outro.
- Visualizar Solicitações: Exibe uma lista de solicitações de doação feitas pelos usuários normais.

## Arquivos de Dados

O programa utiliza dois arquivos de dados:

1. "bancos_de_sangue.txt": Armazena os nomes dos bancos de sangue cadastrados.
2. "solicitacoes.txt": Armazena as solicitações de doações feitas pelos usuários normais.

## Notas

- O programa foi desenvolvido com o objetivo didático e pode conter simplificações e limitações.
- Para o correto funcionamento, certifique-se de que os arquivos de dados "bancos_de_sangue.txt" e "solicitacoes.txt" estejam no mesmo diretório do executável.

## Autor
E-mail de Contato: ddavidamasceno@gmail.com
