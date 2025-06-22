<?php
$path = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
if (strpos($path, '/api/') === 0) {
    $_SERVER['REQUEST_URI'] = substr($path, 5); // remove /api/
    require __DIR__ . '/backend/public/index.php';
    return;
}
$file = __DIR__ . '/frontend' . $path;
if ($path !== '/' && file_exists($file)) {
    return false; // serve static file
}
include __DIR__ . '/frontend/index.html';

