import axios from 'axios';

//poner api en baseURL
const api = axios.create({
    baseURL: 'http://localhost:5173/',
    headers: {
        'Content-Type': 'application/json',
    }
});

//PARA MANDAR TOKEN X HEADERS
// api.interceptors.request.use(config => {
//     const token = localStorage.getItem('Token');
//     if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
// }, error => {
//     return Promise.reject(error);
// });



export default api;
