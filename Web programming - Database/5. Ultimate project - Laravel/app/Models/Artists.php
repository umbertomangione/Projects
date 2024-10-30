<?php

    namespace App\Models;

    use Illuminate\Database\Eloquent\Model;


    class Artists extends Model
    {
        protected $table = 'artisti';
        protected $primaryKey = 'NomeArtista';
        public $incrementing = false;

        public function concert(){
            return $this->hasMany(Concerts::class, 'Artista', 'NomeArtista');
        }

        public function track(){
            return $this->hasMany(Tracks::class, 'Autore', 'NomeArtista');
        }
    }
?>