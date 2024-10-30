<?php

    namespace App\Models;

    use Illuminate\Database\Eloquent\Model;


    class Tickets extends Model
    {
        protected $table = 'biglietti';
        public $timestamps = false;
        
        protected $fillable = [
            'idBiglietto',
            'NumeroBiglietto',
            'email',
            'IC',
        ];
    }
?>