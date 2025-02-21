import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();


export const AuthProvider = ({ children }) => {
    const [Token, SetToken] = useState();
    useEffect(() => {
        const storedToken = localStorage.getItem("Token");
        if (storedToken) {
          login(storedToken);
        }
      }, [])

      const login = (token) => {

        localStorage.setItem("Token", token);
        SetToken(token);



        // const decoded = jwtDecode(token);
        // const {  } = decoded;
        // const expirationTime = exp * 1000 - Date.now();

        // if (expirationTime > 0) {
        //   setTimeout(() => {
        //     logout();
        //   }, expirationTime);
        // } else {
        //   logout();
        // }

      }


    return(
        <AuthContext.Provider value={{login}}>
        {children}
      </AuthContext.Provider>
    )
}