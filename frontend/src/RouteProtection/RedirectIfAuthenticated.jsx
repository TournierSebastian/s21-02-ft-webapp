import { Navigate } from "react-router-dom";

const RedirectIfAuthenticated = ({ children }) => {
  const token = localStorage.getItem("Token");

  if (token) {
    return <Navigate to="/home" replace />;
  }

  return children;
};

export default RedirectIfAuthenticated;
