<?php
    require_once 'dbconfig.php';
    session_start();

    function checkAuth() {
        if(isset($_SESSION['username'])) {
            return $_SESSION['username'];
        } else 
            return 0;
    }

    function comprato(){
        if(isset($_SESSION['comprato'])) {
            return $_SESSION['comprato'];
        } else 
            return 0;
    }

    function artista(){
        if(isset($_SESSION['artista'])) {
            return $_SESSION['artista'];
        } else 
            return 0;
    }
?>