import React, { useEffect } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import ButtonContainer from './navbarButtons/LogInRegister';
import LogoutButton from './navbarButtons/LogoutButton';
import LoggedInPage from '../../pages/loggedInPage/LoggedInPage';
import GroupRegisterPage from '../../pages/groupRegisterPage/GroupRegisterPage';
import JoinGroupPage from '../../pages/joinGroupPage/JoinGroupPage'
import AddTransactionPage from '../../pages/createTransactionPage/AddTransactionPage';

function NavbarLoggedIn() {

    useEffect(() => {
        TestHomePage();
    })

    return (
        <Router>
            <div className={styles.Navbar}>
                <Button text="Home" link="/logIn" />
                <Logo />

                <ButtonContainer text="Join Group" link="/joinGroup" />
                <ButtonContainer text="Create Group" link="/createGroup" />
                <ButtonContainer text="Create Transaction" link="/createTransaction" />
                <LogoutButton text="Log out" /> 
            </ div>
            <Route exact path="/logIn" component={TestHomePage} /> 
            <Route path='/joinGroup' component={JoinGroupPage} />
            <Route path='/createGroup' component={GroupRegisterPage} />
            <Route path='/createTransaction' component={AddTransactionPage} />
        </Router>
    );
}

const TestHomePage = () => (
    <div>

        <LoggedInPage />

    </div>
)


export default NavbarLoggedIn;