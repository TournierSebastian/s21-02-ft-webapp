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

const  router = createBrowserRouter([
  {
    path: '/',
    element: <Landingpage/>
  },
  {
    path: '/home',
    element: <Home/>
  },
  {
    path: '/login',
    element: <Login/>
  },
  {
    path: '/register',
    element: <Register/>
  },
  {
    path: '/tudinero',
    element: <TuDinero/>
  },  {
    path: '/actividad',
    element: <Actividad/>
  },
  {
    path: '/transferir',
    element: <Transferir/>
  },
  {
    path: '/tutarjeta',
    element: <Register/>
  },
  {
    path: '*',
    element: <NotFound/>
  },

  
])
function App() {

  return (
    <div>
      <RouterProvider router={router}></RouterProvider>
    </div>
  );
}

export default App
