function showPage(pageId) {
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    document.getElementById(pageId).classList.add('active');
    if (pageId === 'produtos') loadProdutos();
    if (pageId === 'clientes') loadClientes();
}

document.querySelectorAll('.menu a').forEach(a => {
    a.addEventListener('click', e => {
        e.preventDefault();
        showPage(e.target.dataset.page);
    });
});

document.querySelector('.toggle').addEventListener('click', () => {
    document.querySelector('.sidebar').classList.toggle('collapsed');
});

function loadProdutos() {
    fetch('../backend/public/produtos')
        .then(r => r.json())
        .then(data => {
            const tbody = document.querySelector('#lista-produtos tbody');
            tbody.innerHTML = '';
            data.forEach(p => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<td>${p.ID}</td><td>${p.NOME}</td><td>${p.VALOR_UNITARIO}</td><td>${p.ESTOQUE_ATUAL}</td>` +
                    `<td><button data-id="${p.ID}" class="edit-prod">Editar</button> <button data-id="${p.ID}" class="del-prod">Excluir</button></td>`;
                tbody.appendChild(tr);
            });
        });
}

function loadClientes() {
    fetch('../backend/public/clientes')
        .then(r => r.json())
        .then(data => {
            const tbody = document.querySelector('#lista-clientes tbody');
            tbody.innerHTML = '';
            data.forEach(c => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<td>${c.ID}</td><td>${c.NOME}</td><td>${c.CPF}</td><td>${c.CONTATO}</td>` +
                    `<td><button data-id="${c.ID}" class="edit-cli">Editar</button> <button data-id="${c.ID}" class="del-cli">Excluir</button></td>`;
                tbody.appendChild(tr);
            });
        });
}

document.getElementById('form-produto').addEventListener('submit', e => {
    e.preventDefault();
    const form = e.target;
    const id = form.id.value;
    const data = {
        nome: form.nome.value,
        valor_unitario: parseFloat(form.valor_unitario.value || 0),
        estoque_atual: parseInt(form.estoque_atual.value || 0)
    };
    const method = id ? 'PUT' : 'POST';
    const url = '../backend/public/produtos' + (id ? '/' + id : '');
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => { form.reset(); loadProdutos(); });
});

document.getElementById('lista-produtos').addEventListener('click', e => {
    if (e.target.classList.contains('edit-prod')) {
        const id = e.target.dataset.id;
        fetch('../backend/public/produtos/' + id)
            .then(r => r.json())
            .then(p => {
                const form = document.getElementById('form-produto');
                form.id.value = p.ID;
                form.nome.value = p.NOME;
                form.valor_unitario.value = p.VALOR_UNITARIO;
                form.estoque_atual.value = p.ESTOQUE_ATUAL;
            });
    } else if (e.target.classList.contains('del-prod')) {
        const id = e.target.dataset.id;
        fetch('../backend/public/produtos/' + id, { method: 'DELETE' })
            .then(() => loadProdutos());
    }
});

document.getElementById('form-cliente').addEventListener('submit', e => {
    e.preventDefault();
    const form = e.target;
    const id = form.id.value;
    const data = {
        nome: form.nome.value,
        cpf: form.cpf.value,
        contato: form.contato.value
    };
    const method = id ? 'PUT' : 'POST';
    const url = '../backend/public/clientes' + (id ? '/' + id : '');
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => { form.reset(); loadClientes(); });
});

document.getElementById('lista-clientes').addEventListener('click', e => {
    if (e.target.classList.contains('edit-cli')) {
        const id = e.target.dataset.id;
        fetch('../backend/public/clientes/' + id)
            .then(r => r.json())
            .then(c => {
                const form = document.getElementById('form-cliente');
                form.id.value = c.ID;
                form.nome.value = c.NOME;
                form.cpf.value = c.CPF;
                form.contato.value = c.CONTATO;
            });
    } else if (e.target.classList.contains('del-cli')) {
        const id = e.target.dataset.id;
        fetch('../backend/public/clientes/' + id, { method: 'DELETE' })
            .then(() => loadClientes());
    }
});
