import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import ButtonContainer from './navbarButtons/LogInRegister';
import LogoutButton from './navbarButtons/LogoutButton';
import LoggedInPage from '../../pages/loggedInPage/LoggedInPage';

function NavbarLoggedIn() {
    return (
        <Router>
            <div className={styles.Navbar}>
                <Button text="Home" link="/logIn" />
                <Logo />
                <LogoutButton text="Log out" />
            </ div>
            <Route exact path="/logIn" component={TestHomePage} />
            <Route path="/logOut" component={TestLogoutPage} />
        </Router>
    );
}

const TestHomePage = () => (
    <div>

        <LoggedInPage />

    </div>
)

const TestLogoutPage = () => (
    <div>
        <p>Logout test page</p>
    </div>
)

export default NavbarLoggedIn;