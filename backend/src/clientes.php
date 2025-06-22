<?php
function handle_clientes($method, $segments) {
    $db = Database::getConnection();
    $id = $segments[1] ?? null;
    switch ($method) {
        case 'GET':
            if ($id) {
                $stmt = $db->prepare('SELECT * FROM CLIENTE WHERE ID = ?');
                $stmt->execute([$id]);
                echo json_encode($stmt->fetch());
            } else {
                $stmt = $db->query('SELECT * FROM CLIENTE');
                echo json_encode($stmt->fetchAll());
            }
            break;
        case 'POST':
            $data = json_decode(file_get_contents('php://input'), true);
            $stmt = $db->prepare('INSERT INTO CLIENTE (NOME,CPF,DATA_NASCIMENTO,CEP,RUA,BAIRRO,ID_CIDADE,CONTATO,STATUS) VALUES (?,?,?,?,?,?,?,?,?)');
            $stmt->execute([
                $data['nome'],
                $data['cpf'],
                $data['data_nascimento'] ?? null,
                $data['cep'] ?? null,
                $data['rua'] ?? null,
                $data['bairro'] ?? null,
                $data['id_cidade'] ?? null,
                $data['contato'] ?? null,
                $data['status'] ?? 'ATIVO'
            ]);
            $data['id'] = $db->lastInsertId();
            echo json_encode($data);
            break;
        case 'PUT':
            if (!$id) { http_response_code(400); echo json_encode(['error'=>'Missing ID']); break; }
            $data = json_decode(file_get_contents('php://input'), true);
            $stmt = $db->prepare('UPDATE CLIENTE SET NOME=?,CPF=?,DATA_NASCIMENTO=?,CEP=?,RUA=?,BAIRRO=?,ID_CIDADE=?,CONTATO=?,STATUS=? WHERE ID=?');
            $stmt->execute([
                $data['nome'],
                $data['cpf'],
                $data['data_nascimento'] ?? null,
                $data['cep'] ?? null,
                $data['rua'] ?? null,
                $data['bairro'] ?? null,
                $data['id_cidade'] ?? null,
                $data['contato'] ?? null,
                $data['status'] ?? 'ATIVO',
                $id
            ]);
            $data['id'] = $id;
            echo json_encode($data);
            break;
        case 'DELETE':
            if (!$id) { http_response_code(400); echo json_encode(['error'=>'Missing ID']); break; }
            $stmt = $db->prepare('DELETE FROM CLIENTE WHERE ID = ?');
            $stmt->execute([$id]);
            echo json_encode(['deleted'=>true]);
            break;
        default:
            http_response_code(405);
    }
}
?>
