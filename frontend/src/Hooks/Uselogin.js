import { useNavigate } from "react-router-dom"
import { LoginUserService } from "../Services/LoginUserService"
import Toastify from "toastify-js"; 


const Uselogin = () =>{
    const navigate = useNavigate();
    const UseloginUser = async(email, password) =>{

        try{
            const response = await LoginUserService(email, password)

             if (response.status == 200){
                const data  = await response.data;
                navigate('/Home')
            
                return;

             }

        }catch{

        }

    }



    return {UseloginUser}
}

export default Uselogin;
