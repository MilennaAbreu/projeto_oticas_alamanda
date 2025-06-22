function showPage(pageId) {
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    document.getElementById(pageId).classList.add('active');
    if (pageId === 'produtos') loadProdutos();
    if (pageId === 'clientes') loadClientes();
}

document.getElementById('novo-produto').addEventListener('click', () => {
    const form = document.getElementById('form-produto');
    form.reset();
    form.id.value = '';
    showPage('produto-form');
});

document.getElementById('cancelar-produto').addEventListener('click', () => {
    showPage('produtos');
});

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
    fetch('/api/produtos')
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
    fetch('/api/clientes')
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
        id_tipo: parseInt(form.id_tipo.value || 0) || null,
        id_categoria: parseInt(form.id_categoria.value || 0) || null,
        marca: form.marca.value,
        codigo: form.codigo.value,
        unidade_medida: form.unidade_medida.value,
        valor_unitario: parseFloat(form.valor_unitario.value || 0),
        estoque_atual: parseInt(form.estoque_atual.value || 0),
        status: form.status.value
    };
    const method = id ? 'PUT' : 'POST';
    const url = '/api/produtos' + (id ? '/' + id : '');
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => { form.reset(); showPage('produtos'); loadProdutos(); });
});

document.getElementById('lista-produtos').addEventListener('click', e => {
    if (e.target.classList.contains('edit-prod')) {
        const id = e.target.dataset.id;
        fetch('/api/produtos/' + id)
            .then(r => r.json())
            .then(p => {
                const form = document.getElementById('form-produto');
                form.id.value = p.ID;
                form.nome.value = p.NOME;
                form.id_tipo.value = p.ID_TIPO || '';
                form.id_categoria.value = p.ID_CATEGORIA || '';
                form.marca.value = p.MARCA || '';
                form.codigo.value = p.CODIGO || '';
                form.unidade_medida.value = p.UNIDADE_MEDIDA || 'UN';
                form.valor_unitario.value = p.VALOR_UNITARIO;
                form.estoque_atual.value = p.ESTOQUE_ATUAL;
                form.status.value = p.STATUS;
                showPage('produto-form');
            });
    } else if (e.target.classList.contains('del-prod')) {
        const id = e.target.dataset.id;
        fetch('/api/produtos/' + id, { method: 'DELETE' })
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
    const url = '/api/clientes' + (id ? '/' + id : '');
    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => { form.reset(); loadClientes(); });
});

document.getElementById('lista-clientes').addEventListener('click', e => {
    if (e.target.classList.contains('edit-cli')) {
        const id = e.target.dataset.id;
        fetch('/api/clientes/' + id)
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
        fetch('/api/clientes/' + id, { method: 'DELETE' })
            .then(() => loadClientes());
    }
});
