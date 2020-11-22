import React from 'react';

import Navbar from '../../components/Navbar/Navbar'
import RegisterContainer from './components/RegisterContainer';
import Footer from '../../components/Footer/Footer'

function RegisterPage()
{
    return (
        <div>
            <Navbar/>
            <RegisterContainer/>
            <Footer/>
        </div>
    )
}

export default RegisterPage;