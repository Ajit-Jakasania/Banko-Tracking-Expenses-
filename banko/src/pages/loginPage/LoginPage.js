import React from 'react';

import Navbar from '../../components/Navbar/Navbar'
import LoginContainer from './components/LoginContainer';
import Footer from '../../components/Footer/Footer'



function LoginPage(props) {
    return (
        <div>
            <p>hi this should be: {props.match.params.number}</p>
            <LoginContainer />
        </div>
    )
}

/*
<p>hi this should be: {props.match.params.number}</p>
            <p>hi this should be: {props.match.params.username}</p>
            */


export default LoginPage;