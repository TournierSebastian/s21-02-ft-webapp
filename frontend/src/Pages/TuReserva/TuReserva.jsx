import React, { useEffect, useState } from 'react'
import Navbar from '../../components/navbar/userNavbar'
import UseFecthReserve from '../../Hooks/Reserve/UseFecthReserve'

const TuReserva = () => {
  const {FecthReserve} = UseFecthReserve();
  const [Reserve, SetReserve] = useState()

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await FecthReserve(); 
        console.log(data);
        SetReserve(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
  
    fetchData();
  }, []);
  
  return (
    <div>
        <nav><Navbar/></nav>
        
        TuReserva

        
        </div>
  )
}

export default TuReserva