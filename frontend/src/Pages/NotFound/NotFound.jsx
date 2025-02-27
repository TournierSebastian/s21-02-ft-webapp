import React from 'react'
import './NotFound.css'
import Navbar from '../../components/navbar/userNavbar'
import notfoundicon from '../../assets/General/Notfound.png'
const NotFound = () => {
  return (
    <div className='Contenido'>
      <nav><Navbar /></nav>
      <main>
        <div className="flex flex-col items-center justify-center h-screen text-center">
          <img src={notfoundicon} alt="Página no encontrada" className="mb-4" />
          <p className="mb-4 text-4xl font-semibold">Página no encontrada</p>
          <a href='/'>
          <button className="px-4 py-2 text-lg bg-LightGolden text-black rounded-lg hover:bg-DarkGolden" >
            Volver
          </button>
          </a>
        </div>


      </main>

    </div>
  )
}

export default NotFound