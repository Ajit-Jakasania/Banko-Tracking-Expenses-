import React from 'react';

import styles from './LogInRegister.module.css'
import Button from './Button'

function LogInRegister({ text, link }) {
    return (
        <div className={styles.container}>
            <Button text="Log In" link="" />
            <Button text="Register" link="" />

        </ div>
    );
}

export default LogInRegister;
