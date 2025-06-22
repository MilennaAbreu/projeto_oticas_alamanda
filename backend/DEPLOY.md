# Deploy na Hostinger

1. **Banco MySQL**
   - Crie o banco e execute `schema.sql` para gerar as tabelas.
   - Configure um usuário com acesso e defina as variáveis no arquivo `.env` baseado em `.env.example`.

2. **Arquivos PHP**
   - Envie o conteúdo da pasta `backend/public` para o diretório público (ex.: `public_html`).
   - Coloque as pastas `src` e `config` fora do diretório público por segurança.

3. **Variáveis de Ambiente**
   - Na Hostinger, crie o arquivo `.env` em `config` com as credenciais do banco.
   - Exemplo:
     ```
     DB_HOST=127.0.0.1
     DB_PORT=3306
     DB_NAME=seu_banco
     DB_USER=usuario
     DB_PASS=senha
     ```

4. **Uploads**
   - A rota `/upload` salva arquivos na pasta `uploads/` dentro do diretório público.
   - Certifique-se de que o PHP tenha permissão de escrita nesse diretório.
