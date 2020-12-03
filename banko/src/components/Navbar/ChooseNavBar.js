import React, { useContext, useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import WelcomePage from '../../pages/welcomePage/WelcomePage';

import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import LogInRegister from './navbarButtons/LogInRegister'
import ButtonContainer from './navbarButtons/LogInRegister';
import PageContent from '../../pages/welcomePage/components/PageContent';
import Footer from '../Footer/Footer';
import LoginPage from '../../pages/loginPage/LoginPage';
import RegisterPage from '../../pages/registerPage/RegisterPage';
import { Context } from '../../Store';
import NavbarDefault from './NavbarDefault';
import NavbarLoggedIn from './NavbarLoggedIn';

function ChooseNavBar() {

    const [state, setState] = useState(0);
    const [isUserLoggedIn, setUserLoggedIn] = useContext(Context);




    if (!isUserLoggedIn.id) {
        return (
            <NavbarDefault />
        );
    }
    return (
        <NavbarLoggedIn />
    )
}



export default ChooseNavBar;