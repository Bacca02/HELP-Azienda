<?php
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'jeanmonnetlucamarco');
define('DB_PASSWORD', '');
define('DB_NAME', 'my_jeanmonnetlucamarco');

$link = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);

if ($link === false) {
    die("ERROR: could not connect." . mysqli_connect_error());
}
