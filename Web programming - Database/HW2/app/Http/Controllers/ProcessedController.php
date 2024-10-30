<?php
    use Illuminate\Routing\Controller;

    class ProcessedController extends Controller{

        public function index(){
            if(!empty(Session::get('username'))){
                return view('processed');
            } else{
                return redirect("home");
            }
        }
    }
?>