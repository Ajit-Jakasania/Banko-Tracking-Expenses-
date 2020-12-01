import React from 'react';
import { Link, Route } from 'react-router-dom';



import styles from './Button.module.css';

function Button({ text, link }) {
    return (
        <div className={styles.Button}>
            <Link to={link}>{text}</Link>

        </ div>
    );
}

export default Button;
