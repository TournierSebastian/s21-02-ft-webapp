import { Navigate } from "react-router-dom";

const ProtectedAccount = ({ children }) => {
  const Account = localStorage.getItem("Account");

  if (!Account) {
    return <Navigate to="/account" replace />;
  }

  return children;
};

export default ProtectedAccount;
