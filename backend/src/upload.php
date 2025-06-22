<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

$targetDir = __DIR__ . '/../public/uploads/';
if (!is_dir($targetDir)) {
    mkdir($targetDir, 0777, true);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['file'])) {
    $fileName = basename($_FILES['file']['name']);
    $path = $targetDir . $fileName;
    if (move_uploaded_file($_FILES['file']['tmp_name'], $path)) {
        echo json_encode(['path' => 'uploads/' . $fileName]);
    } else {
        http_response_code(500);
        echo json_encode(['error' => 'Upload failed']);
    }
}
?>
