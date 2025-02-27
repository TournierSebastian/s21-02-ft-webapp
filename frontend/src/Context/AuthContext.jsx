import { jwtDecode } from "jwt-decode";
import { createContext, useEffect, useState } from "react";
export const AuthContext = createContext();


export const AuthProvider = ({ children }) => {
    const [Token, SetToken] = useState();
    const [Name, SetName] = useState();

    useEffect(() => {
        const storedToken = localStorage.getItem("Token");
        const storedName = localStorage.getItem("Name");
        if (storedToken && storedName) {
          SetToken(storedToken);
          SetName(storedName);
          ValidateToken(storedToken);
        }
      }, [])

      const login = (data) => {
        localStorage.setItem("Token", data.token);
        localStorage.setItem("Name", data.fullName);
        SetToken(data.token);
        SetName(data.fullName);

      }
      const ValidateToken = (token) =>{
        const decoded = jwtDecode(token);
        const { exp } = decoded;
        const expirationTime = exp * 1000 - Date.now();
  
        if (expirationTime > 0) {
          setTimeout(() => {
            logout();
          }, expirationTime);
        } else {
          logout();
        }
      }
      
      const logout = () => {
        localStorage.removeItem("Token");
        SetToken(null)
        SetName('')
        window.location.href = '/'
      };
    

    return(
        <AuthContext.Provider value={{login}}>
        {children}
      </AuthContext.Provider>
    )
}