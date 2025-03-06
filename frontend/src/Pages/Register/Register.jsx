import React, { useState } from 'react'
import './Register.css'
import logo from '../../assets/Icons/Logo.png'
import UseRegister from '../../Hooks/Authentication/UseRegister'
import Navbar from '../../components/navbar/Navbar'

const Register = () => {
  const [Email, SetEmail] = useState('');
  const [FullName, SetFullname] = useState('');
  const [Telephone, SetTelephone] = useState('');
  const [Password, SetPassword] = useState('');
  const [Dni, SetDni] = useState(null);
  const [ConfirmPassword, SetConfirmPassword] = useState('');
  const [Validate, SetValidate] = useState(false)

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const telephoneRegex = /^\+54\d{10}$/;
  const { UseRegisterUser } = UseRegister();
  const HandlerRegistar = (e) => {
    e.preventDefault();

    if (
      !emailRegex.test(Email) ||
      FullName === '' ||
      !telephoneRegex.test(Telephone) ||
      Password.length < 8 ||
      Password !== ConfirmPassword ||
      Dni == null||
      Dni.length !== 8
    ) {
      SetValidate(true);
      return;
    }
    UseRegisterUser(FullName, Dni, Email, Telephone, Password)
    return
  }
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

            <form className='space-y-4 w-4/5 mx-auto' onSubmit={HandlerRegistar}>
              <div>
                <label htmlFor='email' className='text-white text-lg'>Correo Electrónico</label>
                <input id='email' type='email' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese correo electrónico' onChange={(e) => (SetEmail(e.target.value))} />
                {(!emailRegex.test(Email) && Validate) &&<p className="text-red-500 text-sm font-bold mt-1">Ingrese un correo electrónico válido</p>}

              </div>

              <div>
                <label htmlFor='name' className='text-white text-lg'>Nombre Completo</label>
                <input id='name' type='text' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese nombre completo' onChange={(e) => (SetFullname(e.target.value))} />
                {(FullName == '' && Validate) && <p className=' text-red-500 text-sm font-bold mt-1'>Ingrese nombre completo</p>}
              </div>

              <div>
                <label htmlFor='name' className='text-white text-lg'>Documento (DNI)</label>
                <input id='name' type='number' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese documento' onChange={(e) => (SetDni(e.target.value))} />
                {((Dni == null || Dni.length !== 8) && Validate) && <p className=' text-red-500 text-sm font-bold mt-1'>El documento debe tener 8 caracteres</p>}
              </div>
              <div>
                <label htmlFor='phone' className='text-white text-lg'>Número Telefónico</label>
                <input id='phone' type='tel' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese número telefónico' onChange={(e) => (SetTelephone(e.target.value))} />
                {(!telephoneRegex.test(Telephone) && Validate) && <p className="text-red-500 text-sm font-bold mt-1">Número de teléfono inválido. +54xxxxxxxxxx</p>}
                </div>

              <div>
                <label htmlFor='password' className='text-white text-lg'>Contraseña</label>
                <input id='password' type='password' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Ingrese contraseña' onChange={(e) => (SetPassword(e.target.value))} />
                {(Password.length < 8 && Validate) && <p className="text-red-500 text-sm font-bold mt-1">La contraseña debe tener al menos 8 caracteres</p>}
                </div>

              <div>
                <label htmlFor='confirm-password' className='text-white text-lg'>Confirmar contraseña</label>
                <input id='confirm-password' type='password' className='w-full bg-WhiteBlack text-Gray p-3 rounded-md border border-LightGolden focus:outline-none focus:ring-2 focus:ring-LightGolden' placeholder='Confirmar contraseña' onChange={(e) => (SetConfirmPassword(e.target.value))} />
                {(ConfirmPassword != Password && Validate) && <p className=' text-red-500 text-sm font-bold mt-1'>Las contraseñas deben coincidir</p>}
              </div>

              <button className='w-full bg-LightGolden  hover:bg-DarkGolden text-black font-bold py-3 rounded-md hover:bg-opacity-80 transition' onClick={HandlerRegistar}>Crear cuenta</button>
            </form>

          </div>
        </div>
      </main>
    </div>
  )
}

export default Register