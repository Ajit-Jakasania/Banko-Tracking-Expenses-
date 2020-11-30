import React from 'react';

import Navbar from '../../components/Navbar/Navbar'
import RegisterGroupContainer from './components/LoginContainer';
import Footer from '../../components/Footer/Footer'

function GroupRegisterPage()
{
    return (
        <div>
            <Navbar/>
            <RegisterGroupContainer />
            <Footer/>
        </div>
    )
}

export default GroupRegisterPage;