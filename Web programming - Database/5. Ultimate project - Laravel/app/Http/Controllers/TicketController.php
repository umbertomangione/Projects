<?php
    use Illuminate\Routing\Controller;
    use App\Models\Concerts;
    use App\Models\Comments;
    use App\Models\Locations;
    use App\Models\Tickets;

    class TicketController extends Controller{

        public function index(){
            if(!empty(Session::get('username'))){
                return view('tickets');
            } else{
                return redirect("home");
            }
        }

        public function concerts(){
            $con = concerts::where('Artista', Session::get('NomeArtista'))->get();
            foreach($con as $cons){
                $cons->artist->track;
                $cons->location->nation;
            }
            return $con;
        }

        public function comments(){
            $tic = Comments::all();
            return $tic;
        }

        public function createComm(){
            $request = request();
            if($request->Voto){
                if(($request->cdi == null) || ($request->Voto == null) || ($request->Voto < 0.5) || ($request->Voto > 5)){
                    Session::put('error', "Fields empty");
                }else{
                    Comments::create([
                        'Commento' => $request->cdi,
                        'DataCommento' => $request->Data,
                        'Voto' => $request->Voto,
                        'Art' => $request->NomeA
                    ]);
                }
            }
        }

        public function buy(){
            $request = request();
            if($request->ID){
                if($request->Posti < 1){
                    Session::put('error', "Sorry, for the selected concert there aren't places available ");
                    return redirect('tickets');
                }else{
                    Tickets::create([
                        'NumeroBiglietto' => 1,
                        'email' => Session::get('username'),
                        'IC' => $request->ID
                    ]);

                    $posti = $request->Posti - 1;    

                    Concerts::where('idConcerto', $request->ID)->update(array('Posti' => $posti));
                    return redirect('processed');
                }
            }
        }
    }
?>