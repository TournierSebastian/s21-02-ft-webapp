import api from "./api";

export const LoginUserService = async (email, password) => {
    try {
        const formData = {
            email: email,
            password:  password
        };

        //llamar api
        alert("Email: " + email + ", Password: " + password);
        // const response = await api.post();
        // return response

        //Hardcodeado de prueba
        return {
            status: 200,
            data: { message: "Login exitoso" }
        };
    } catch (error) {
      
        throw error;
    }
};
