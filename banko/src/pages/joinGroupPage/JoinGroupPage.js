import React from 'react';

import Navbar from '../../components/Navbar/Navbar'
import JoinGroupContainer from './components/LoginContainer';
import Footer from '../../components/Footer/Footer'


function GroupRegisterPage()
{
    return (
        <div>
            <Navbar/>
            <JoinGroupContainer />
            <Footer/>
        </div>
    )
}

export default GroupRegisterPage;