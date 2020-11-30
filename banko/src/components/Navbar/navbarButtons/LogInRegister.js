import React from 'react';

import styles from './LogInRegister.module.css'
import Button from './Button'

function ButtonContainer({ text, link }) {
    return (
        <div className={styles.container}>
            <Button text={text} link={link} />

        </ div>
    );
}

export default ButtonContainer;
