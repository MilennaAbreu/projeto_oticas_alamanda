<?php
require_once __DIR__ . '/../src/db.php';

header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

$method = $_SERVER['REQUEST_METHOD'];
$path = trim(parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH), '/');
$segments = explode('/', $path);

if ($segments[0] === 'produtos') {
    require_once __DIR__ . '/../src/produtos.php';
    handle_produtos($method, $segments);
    exit;
} elseif ($segments[0] === 'clientes') {
    require_once __DIR__ . '/../src/clientes.php';
    handle_clientes($method, $segments);
    exit;
} elseif ($segments[0] === 'upload') {
    require_once __DIR__ . '/../src/upload.php';
    exit;
} else {
    echo json_encode(['message' => 'API running']);
}
?>
