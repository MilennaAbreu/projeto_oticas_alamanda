Backend em PHP
==============
O projeto original em Java foi substituído por uma API PHP simples.
Os endpoints ficam na pasta `backend/public` e utilizam PDO para conectar
ao MySQL configurado via variáveis de ambiente.
A estrutura completa do banco de dados está em `backend/schema.sql`.

Para rodar localmente:
```
cp backend/config/.env.example backend/config/.env
php -S localhost:8000 router.php
```

Depois, acesse `http://localhost:8000` no navegador. Usar o servidor embutido evita
erros de CORS ao tentar abrir o `index.html` diretamente pelo sistema de arquivos.

Veja `backend/DEPLOY.md` para instruções de deploy na Hostinger.



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

Frontend
========
Uma interface simples em HTML/JS foi adicionada em `frontend/` para gerenciar
produtos e clientes. Abra `frontend/index.html` em um navegador e os dados serao
carregados da API PHP.
