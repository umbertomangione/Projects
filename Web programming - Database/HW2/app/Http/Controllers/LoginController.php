<?php
    use Illuminate\Routing\Controller;
    use App\Models\User;

    class LoginController extends Controller{

        public function checkSession(){
            if(!empty(Session::get('username'))){
                return redirect("home");
            }else{
                return view('login');
            }
        }

        public function Login(){
            $request = request();
            $user = User::where(function ($query){
                            $query->where('username', request('username'))
                                  ->orwhere('email', request('username'));
                            })
                          ->where('password', base64_encode(hash("sha256", request('password'), True)))
                          ->first();
            
            if(isset($user)){
                Session::put('username', $user->email);
                return redirect('home');
            }else if((request('username') && request('password')) == null){
                Session::put('error', "Fields empty");
                return redirect('login');
            }else{
                Session::put('error', "Username or Password wrong");
                return redirect('login');
            }
        }

        public function Logout(){
            Session::flush();
            return redirect('login');
        }
    }
?>