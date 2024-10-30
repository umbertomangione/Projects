<?php

    namespace App\Models;
    use Illuminate\Database\Eloquent\Model;

    class Nations extends Model
    {
        protected $table = 'nazioni';
        protected $primaryKey = 'Sigla';

        public function location(){
            return $this->hasMany(Locations::class, 'S', 'Sigla');
        }
    }
?>