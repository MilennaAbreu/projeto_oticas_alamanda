Para startar o Projeto:
=======================
mvn clean install
mvn spring-boot:run



Exemplo JSON de insert do /api/auth/register:
=============================================

{
  "login": "admin",
  "senha": "asdf@321",
  "nome": "Administrador",
  "email": "admin@otica.com",
  "perfil": "ADMIN",
  "cpf": "12345678900"
}



Regras de Perfis:
=================
🟢 1. ADMINISTRADOR
✔ Pode criar, atualizar, deletar e consultar tudo
✔ Pode gerenciar usuários de todos os perfis
✔ Acesso total aos módulos de faturamento, estoque, financeiro, compras, vendas, relatórios e configurações


🟡 2. DIRETORIA
✔ Igual ao ADMINISTRADOR com restrições pontuais:
❌ Sugestões de limitações:
❌ Não pode criar/editar usuários com perfil ADMINISTRADOR
❌ Não pode excluir usuários de qualquer perfil
❌ Não pode alterar configurações de sistema (como parâmetros globais)
✔ Pode acessar tudo relacionado a faturamento, financeiro, estoque e relatórios


🟠 3. ADMINISTRATIVO

✔ Pode:
Cadastrar/editar clientes e fornecedores
Lançar compras, vendas, contas a pagar/receber
Consultar e atualizar estoque

❌ Não pode:
Visualizar relatórios de faturamento da empresa
Deletar qualquer item (compra, venda, cliente, etc.)
Criar usuários
Alterar configurações ou permissões


🔵 4. VENDEDOR

✔ Pode:
Cadastrar/editar clientes e fornecedores
Lançar compras e vendas
Consultar estoque

❌ Não pode:
Atualizar estoque
Lançar contas a pagar/receber
Ver faturamento ou relatórios financeiros
Criar/editar/deletar usuários
