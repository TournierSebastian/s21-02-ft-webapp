import { useState } from 'react'
import Navbar from '../../components/navbar/Navbar'
import ActivityItem from '../../components/Activity/ActivityItem'

function Actividad() {
  const [activities] = useState([
    {
      date: '15 de Febrero, 2025',
      username: 'Carlos Rodriguez',
      reason: 'Transferencia de dinero',
      amount: '-150.00',
      time: '14:30',
      icon: "🔃",
    },
    {
      date: '14 de Febrero, 2025',
      username: 'María González',
      reason: 'Pago de servicios',
      amount: '-75.50',
      time: '11:20',
      icon: "🍔",
    },
    {
      date: '14 de Febrero, 2025',
      username: 'Juan Pérez',
      reason: 'Transferencia recibida',
      amount: '+200.00',
      time: '09:45',
      icon: "🔃",
    },
    {
      date: '13 de Febrero, 2025',
      username: 'Ana Silva',
      reason: 'Pago de alquiler',
      amount: '-300.00',
      time: '16:15',
      icon: "💵",
    },
    {
      date: '13 de Febrero, 2025',
      username: 'Luis Torres',
      reason: 'Depósito',
      amount: '+450.00',
      time: '10:00',
      icon: "💰",
    },
  ])

  return (
    <div className='Contenido'>
      <nav> <Navbar />   </nav>
      <main>
        <div className='flex justify-center items-center mt-10'>
        <div className="w-full max-w-md bg-BlackBlue rounded-3xl shadow-lg p-6 space-y-6 text-black ">
          <h2 className="text-xl font-semibold text-white">Tu última actividad</h2>

          <div className="space-y-2 ">
            {activities.map((activity, index) => (
              <ActivityItem key={index} {...activity} />
            ))}
          </div>

          <button className="w-full text-xl py-3 text-gray-300 text-left px-2 rounded-lg cursor-pointer hover:text-white transition-colors font-medium">
            Ver toda la actividad
          </button>
        </div>
        </div>
      </main>
    </div>

  )
}

export default Actividad
