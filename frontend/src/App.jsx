import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Landingpage from './Pages/Landingpage/Landingpage.jsx'
import Home from './Pages/Home/Home.jsx'
import Login from './Pages/Login/Login.jsx'
import Register from './Pages/Register/Register.jsx'
import Transferir from './Pages/Transferir/Transferir.jsx'
import NotFound from './Pages/NotFound/NotFound.jsx'
import TuDinero from './Pages/TuDinero/TuDinero.jsx'
import Actividad from './Pages/Actividad/Actividad.jsx'
import './Styles/Global.css'
import { AuthProvider } from './Context/AuthContext.jsx'
import RedirectIfAuthenticated from './RouteProtection/RedirectIfAuthenticated.jsx'
import ProtectedUser from './RouteProtection/ProtectedUser.jsx'
import TuTarjeta from './Pages/TuTarjeta/TuTarjeta.jsx'
import TuReserva from './Pages/TuReserva/TuReserva.jsx'
import Accounts from './Pages/Accounts/Accounts.jsx'
import ProtectedAccount from './RouteProtection/ProtectedAccount.jsx'
import CvuAlias from './Pages/CvuAlias/CvuAlias.jsx'

const  router = createBrowserRouter([
  {
    path: '/',
    element: <RedirectIfAuthenticated><Landingpage/></RedirectIfAuthenticated>
  },
  {
    path: '/login',
    element: <RedirectIfAuthenticated> <Login/> </RedirectIfAuthenticated>
  },
  {
    path: '/register',
    element: <RedirectIfAuthenticated> <Register/> </RedirectIfAuthenticated>
  }, 
  {
    path: '/account',
    element: <ProtectedUser><Accounts/></ProtectedUser>
  },
  {
    path: '/home',
    element: <ProtectedUser><ProtectedAccount><Home/></ProtectedAccount></ProtectedUser>
  },
  {
    path: '/tudinero',
    element: <ProtectedUser><ProtectedAccount><TuDinero/></ProtectedAccount></ProtectedUser>
  },  {
    path: '/actividad',
    element: <ProtectedUser><ProtectedAccount><Actividad/></ProtectedAccount></ProtectedUser>
  },
  {
    path: '/transferir',
    element: <ProtectedUser><ProtectedAccount><Transferir/></ProtectedAccount></ProtectedUser>
  },
  {
    path: '/tutarjeta',
    element: <ProtectedUser><ProtectedAccount><TuTarjeta/></ProtectedAccount></ProtectedUser>
  },
  ,
  {
    path: '/tureserva',
    element: <ProtectedUser><ProtectedAccount><TuReserva/></ProtectedAccount></ProtectedUser>
  },
  {
    path: '/CVU',
    element: <ProtectedUser><ProtectedAccount><CvuAlias/></ProtectedAccount></ProtectedUser>
  },
  {
    path: '*',
    element: <NotFound/>
  },

  
])
function App() {

  return (
    <div>
      <AuthProvider>
      <RouterProvider router={router}></RouterProvider>
      </AuthProvider>
    </div>
  );
}

export default App
