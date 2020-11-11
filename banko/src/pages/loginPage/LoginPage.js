import React from 'react';

import Navbar from '../../components/Navbar/Navbar'
import LoginContainer from './components/LoginContainer';
import Footer from '../../components/Footer/Footer'

function LoginPage()
{
    return (
        <div>
            <Navbar/>
            <LoginContainer/>
            <Footer/>
        </div>
    )
}

export default LoginPage;