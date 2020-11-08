import React from 'react';

import Navbar from '../../components/Navbar/Navbar'
import PageContent from './components/PageContent';
import Footer from '../../components/Footer/Footer'

function WelcomePage() {
    return (
        <div>
            <Navbar />
            <PageContent />
            <Footer />
        </div>
    );
}

export default WelcomePage;
