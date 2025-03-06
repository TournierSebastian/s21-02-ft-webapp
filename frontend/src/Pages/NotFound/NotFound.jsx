import React from 'react'
import './NotFound.css'
import notfoundicon from '../../assets/General/Notfound.png'
import logo from '../../assets/Icons/Logo.png'
const NotFound = () => {
  return (
    <div className='Contenido'>
      <nav>
        <div className='flex justify-end bg-BlackBlue'>
          <a className='flex items-center' href='/'>
            <p className='text-LightGolden text-2xl mr-2'>Wallex</p>
            <img src={logo} className='w-20' />
          </a>
        </div>


      </nav>
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