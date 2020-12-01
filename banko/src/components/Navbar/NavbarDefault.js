import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import ButtonContainer from './navbarButtons/LogInRegister';
import LoginPage from '../../pages/loginPage/LoginPage';
import RegisterPage from '../../pages/registerPage/RegisterPage';
import DefaultPage from '../../pages/welcomePage/DefaultPage';

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
        <DefaultPage />
    </div>
)


export default NavbarDefault;
