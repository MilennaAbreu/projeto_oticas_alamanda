<?php
function handle_produtos($method, $segments) {
    $db = Database::getConnection();
    $id = $segments[1] ?? null;
    switch ($method) {
        case 'GET':
            if ($id) {
                $stmt = $db->prepare('SELECT * FROM PRODUTO WHERE ID = ?');
                $stmt->execute([$id]);
                echo json_encode($stmt->fetch());
            } else {
                $stmt = $db->query('SELECT * FROM PRODUTO');
                echo json_encode($stmt->fetchAll());
            }
            break;
        case 'POST':
            $data = json_decode(file_get_contents('php://input'), true);
            $stmt = $db->prepare('INSERT INTO PRODUTO (NOME,ID_TIPO,ID_CATEGORIA,MARCA,CODIGO,UNIDADE_MEDIDA,VALOR_UNITARIO,ESTOQUE_ATUAL,STATUS) VALUES (?,?,?,?,?,?,?,?,?)');
            $stmt->execute([
                $data['nome'],
                $data['id_tipo'] ?? null,
                $data['id_categoria'] ?? null,
                $data['marca'] ?? null,
                $data['codigo'] ?? null,
                $data['unidade_medida'] ?? null,
                $data['valor_unitario'] ?? 0,
                $data['estoque_atual'] ?? 0,
                $data['status'] ?? 'ATIVO'
            ]);
            $data['id'] = $db->lastInsertId();
            echo json_encode($data);
            break;
        case 'PUT':
            if (!$id) { http_response_code(400); echo json_encode(['error'=>'Missing ID']); break; }
            $data = json_decode(file_get_contents('php://input'), true);
            $stmt = $db->prepare('UPDATE PRODUTO SET NOME=?,ID_TIPO=?,ID_CATEGORIA=?,MARCA=?,CODIGO=?,UNIDADE_MEDIDA=?,VALOR_UNITARIO=?,ESTOQUE_ATUAL=?,STATUS=? WHERE ID=?');
            $stmt->execute([
                $data['nome'],
                $data['id_tipo'] ?? null,
                $data['id_categoria'] ?? null,
                $data['marca'] ?? null,
                $data['codigo'] ?? null,
                $data['unidade_medida'] ?? null,
                $data['valor_unitario'] ?? 0,
                $data['estoque_atual'] ?? 0,
                $data['status'] ?? 'ATIVO',
                $id
            ]);
            $data['id'] = $id;
            echo json_encode($data);
            break;
        case 'DELETE':
            if (!$id) { http_response_code(400); echo json_encode(['error'=>'Missing ID']); break; }
            $stmt = $db->prepare('DELETE FROM PRODUTO WHERE ID = ?');
            $stmt->execute([$id]);
            echo json_encode(['deleted'=>true]);
            break;
        default:
            http_response_code(405);
    }
}
?>
