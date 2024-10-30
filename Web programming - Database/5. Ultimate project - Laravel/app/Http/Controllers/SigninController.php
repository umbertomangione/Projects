<?php
    use Illuminate\Routing\Controller;
    use App\Models\User;

    class SigninController extends Controller{

        public function checkSession(){
            if(!empty(Session::get('username'))){
                return redirect("home");
            } else{
                return view('signin');
            }
        }

        public function create(){
            $request = request();

            $user = User::where('email', request('email'))->first();

            if(($request->username && $request->email && $request->password) == null){
                Session::put('error', "Fields empty");
                return redirect('signin');
            }else if(isset($user)){
                Session::put('error', "Email already registered");
                return redirect('signin');
            }else{
                User::create([
                    'username' => $request->username,
                    'email' => $request->email,
                    'password' => base64_encode(hash("sha256", ($request->password), true))
                ]);
                return redirect('login');
            }
        }
    }
?>