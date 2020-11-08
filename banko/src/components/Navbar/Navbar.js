import React from 'react';


import styles from './Navbar.module.css';
import Button from './navbarButtons/Button'
import Logo from '../Logo/Logo'
import LogInRegister from './navbarButtons/LogInRegister'

function Navbar() {
    return (
        <div className={styles.Navbar}>

            <Button text="Home" link="" />
            <Logo />
            <LogInRegister />

        </ div>
    );
}

export default Navbar;
