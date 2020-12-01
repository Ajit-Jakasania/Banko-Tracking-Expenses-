import React, { useContext, useState } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import WelcomePage from '../../pages/welcomePage/WelcomePage';

import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import LogInRegister from './navbarButtons/LogInRegister'
import ButtonContainer from './navbarButtons/LogInRegister';
import PageContent from '../../pages/welcomePage/components/PageContent';
import LoginPage from '../../pages/loginPage/LoginPage';
import RegisterPage from '../../pages/registerPage/RegisterPage';

function NavbarDefault() {

    return (
        <Router>
            <div className={styles.Navbar}>
                <Button text="Home" link="/" />
                <Logo />
                <ButtonContainer text="Log In" link='/logIn' /> 
                <ButtonContainer text="Register" link='/Register' />
            </ div>
            <Route exact path="/" component={TestHomePage} /> 
            <Route path="/logIn" component={LoginPage} /> 
            <Route path="/Register" component={RegisterPage} /> 
        </Router>
    );
}

const TestHomePage = () => (
    <div>
            <PageContent />
    </div>
)


export default NavbarDefault;
