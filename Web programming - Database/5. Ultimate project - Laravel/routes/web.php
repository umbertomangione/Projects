<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function()
{
    return view("welcome");
});

Route::options('/{path}', function(){ 
    return '';
})->where('path', '.*');

Route::get('home', 'HomeController@index');
Route::get('artists', 'HomeController@artists');
Route::post('home', 'HomeController@redirect');

Route::get('login', 'LoginController@checkSession');
Route::post('login', 'LoginController@Login');
Route::get('logout', 'LoginController@logout');

Route::get('signin', 'SigninController@checkSession');
Route::post('signin', 'SigninController@create');

Route::get('tickets', 'TicketController@index');
Route::post('tickets', 'TicketController@buy');
Route::get('DWconcerts', 'TicketController@concerts');
Route::post('Upcomments', 'TicketController@createComm');
Route::get('DWcomments', 'TicketController@comments');

Route::get('processed', 'ProcessedController@index');