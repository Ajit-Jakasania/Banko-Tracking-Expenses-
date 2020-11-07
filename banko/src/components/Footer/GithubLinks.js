import React from 'react';

import styles from './GithubLinks.module.css'
import FooterDiv from './FooterDiv'

function GithubLinks() {
    return (
        <div className={styles.container}>

            <FooterDiv text="Gabe Github" />
            <FooterDiv text="Ajit Github" />
            <FooterDiv text="Osama Github" />



        </div>
    );
}

export default GithubLinks;
