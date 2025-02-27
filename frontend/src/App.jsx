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
    path: '/home',
    element: <ProtectedUser><Home/></ProtectedUser>
  },
  {
    path: '/tudinero',
    element: <ProtectedUser><TuDinero/></ProtectedUser>
  },  {
    path: '/actividad',
    element: <ProtectedUser><Actividad/></ProtectedUser>
  },
  {
    path: '/transferir',
    element: <ProtectedUser><Transferir/></ProtectedUser>
  },
  {
    path: '/tutarjeta',
    element: <ProtectedUser><TuTarjeta/></ProtectedUser>
  },
  {
    path: '*',
    element: <ProtectedUser><NotFound/></ProtectedUser>
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
