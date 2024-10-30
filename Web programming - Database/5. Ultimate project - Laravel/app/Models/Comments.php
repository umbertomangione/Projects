<?php

    namespace App\Models;

    use Illuminate\Database\Eloquent\Model;


    class Comments extends Model
    {
        protected $table = 'commenti';
        public $timestamps = false;
        
        protected $fillable = [
            'idCommento',
            'Commento',
            'DataCommento',
            'Voto',
            'Art',
        ];
    }
?>