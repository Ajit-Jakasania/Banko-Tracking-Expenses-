import React from 'react';
import { Component } from 'react';

import Navbar from '../../components/Navbar/Navbar'
import LoginContainer from './components/LoginContainer';
import Footer from '../../components/Footer/Footer'

class LoginPage2 extends Component
{
    constructor() {
        super();
        console.log('In constructor');
    }



    render() {
        return (
            <div>
                <Navbar/>
                <LoginContainer/>
                <Footer/>
            </div>
        )
    }
}

export default LoginPage2;