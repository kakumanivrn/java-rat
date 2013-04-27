<?php
  $file = 'ip.txt';
  $data = $_SERVER['REMOTE_ADDR']."\n";
  file_put_contents($file, $data, FILE_APPEND | LOCK_EX);
?>
