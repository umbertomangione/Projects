<?php
    use Illuminate\Routing\Controller;
    use App\Models\Artists;

    class HomeController extends Controller{

        public function index(){
            session()->forget('error');
            return view('home');
        }

        public function artists(){
            $art = Artists::all();
            return $art;
        }

        public function redirect(){
            $request = request();
            if(!empty(Session::get('username'))){
                Session::put('NomeArtista', $request->artista);
                return redirect('tickets');
            }else{
                Session::put('error', "Please Login to buy tickets.");
                return view('login');
            }
        }
    }
?>