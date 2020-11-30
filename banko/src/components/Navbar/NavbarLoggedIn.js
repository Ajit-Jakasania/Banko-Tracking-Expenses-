import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import ButtonContainer from './navbarButtons/LogInRegister';

function NavbarLoggedIn() {
    return (
        <Router>
            <div className={styles.Navbar}>
                <Button text="Home" link="/" />
                <Logo />
                <ButtonContainer text="Log Out" link='/logOut' /> 
            </ div>
            <Route exact path="/" component={TestHomePage} /> 
            <Route exact path="/logOut" component={TestLogoutPage} /> 
        </Router>
    );
}

const TestHomePage = () => (
    <div>
            <p> You are Logged In!</p>
    </div>
)

const TestLogoutPage = () => (
    <div>
            <p>Logout test page</p>
    </div>
)

export default NavbarLoggedIn;
