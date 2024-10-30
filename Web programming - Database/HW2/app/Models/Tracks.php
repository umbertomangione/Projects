<?php

    namespace App\Models;

    use Illuminate\Database\Eloquent\Model;


    class Tracks extends Model
    {
        protected $table = 'brani';
        protected $primaryKey = 'idBrani';

        public function artist(){
            return $this->belongsTo(Artists::class, 'Autore', 'NomeArtista');
        }
    }
?>