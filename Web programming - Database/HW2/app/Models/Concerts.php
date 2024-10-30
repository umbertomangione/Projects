<?php

    namespace App\Models;
    use Illuminate\Database\Eloquent\Model;

    class Concerts extends Model
    {
        protected $table = 'concerto';
        protected $primaryKey = 'idConcerto';
        public $timestamps = false;

        public function location(){
            return $this->belongsTo(Locations::class, 'iL', 'idLocalita');
        }

        public function artist(){
            return $this->belongsTo(Artists::class, 'Artista', 'NomeArtista');
        }
    }
?>