import React from 'react';

import styles from './GithubLinks.module.css'
import FooterDiv from './FooterDiv'


function GithubLinks() {
    return (
        <div className={styles.container}>

            <FooterDiv gitLink="https://github.com/irock194" text="Gabe Github " />
            <FooterDiv gitLink="https://github.com/Ajit-Jakasania" text="Ajit Github" />
            <FooterDiv gitLink="https://github.com/osamahan999/" text="Osama Github" />



        </div>
    );
}

export default GithubLinks;
