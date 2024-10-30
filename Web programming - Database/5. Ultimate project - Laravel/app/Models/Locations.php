<?php

    namespace App\Models;
    use Illuminate\Database\Eloquent\Model;

    class Locations extends Model
    {
        protected $table = 'localita';
        protected $primaryKey = 'idLocalita';

        public function concert(){
            return $this->hasMany(Concerts::class, 'iL', 'idLocalita');
        }

        public function nation(){
            return $this->belongsTo(Nations::class, 'S', 'Sigla');
        }
    }
?>