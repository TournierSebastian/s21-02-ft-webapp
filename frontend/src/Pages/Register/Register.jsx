import React from 'react'
import './Register.css'
import Navbar from '../../components/navbar/userNavbar'
import logo from '../../assets/Icons/Logo.png'

const Register = () => {
  return (
    <div className='Contenido'>
      <nav><Navbar /></nav>

      <main>
        <div className='flex justify-center items-center mt-10'>
        <div className='bg-BlackBlue max-w-lg w-full p-8 rounded-xl shadow-lg'>
          <div className='flex justify-center items-center mb-5'>
            <img src={logo} className="w-20 mx-3"></img>
            <h3 className='text-LightGolden text-4xl font-bold'>Crea tu cuenta</h3>
            </div>

            <div className='space-y-4 w-4/5 mx-auto'>
              <div>
                <label htmlFor='email' className='text-white text-lg'>Correo Electrónico</label>
                <input id='email' type='email' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese correo electrónico' />
              </div>
              
              <div>
                <label htmlFor='name' className='text-white text-lg'>Nombre Completo</label>
                <input id='name' type='text' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese nombre completo' />
              </div>

              <div>
                <label htmlFor='phone' className='text-white text-lg'>Número Telefónico</label>
                <input id='phone' type='tel' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese número telefónico' />
              </div>

              <div>
                <label htmlFor='password' className='text-white text-lg'>Contraseña</label>
                <input id='password' type='password' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese contraseña' />
              </div>

              <div>
                <label htmlFor='confirm-password' className='text-white text-lg'>Confirmar contraseña</label>
                <input id='confirm-password' type='password' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Confirmar contraseña' />
              </div>

              <button className='w-full bg-LightGolden text-black font-bold py-3 rounded-md hover:bg-opacity-80 transition'>Crear cuenta</button>
            </div>
        
        </div>
        </div>
      </main>
    </div>
  )
}

export default Register