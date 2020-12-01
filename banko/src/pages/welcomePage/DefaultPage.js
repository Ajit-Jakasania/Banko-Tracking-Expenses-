import React, { useEffect } from 'react';


import PageContent from './components/PageContent';
import styles from './components/PageContent.module.css';
import logo from './components/happyMan.jpg';
import imageStyles from './components/image.module.css';



function DefaultPage() {

    return (
        <div>

            <div className={styles.container}>
                <img src={logo} alt="Logo" />
            </div>
            <PageContent />
        </div>
    );
}

export default DefaultPage;