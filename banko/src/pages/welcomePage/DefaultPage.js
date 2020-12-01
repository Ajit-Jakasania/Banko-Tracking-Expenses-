import React, { useEffect } from 'react';


import PageContent from './components/PageContent';
import styles from './components/PageContent.module.css';
import logo from './components/happyMan.jpg';




function DefaultPage() {

    return (
        <div className={styles.container}>
            <img src={logo} alt="Logo" />
        </div>
    );
}

export default DefaultPage;