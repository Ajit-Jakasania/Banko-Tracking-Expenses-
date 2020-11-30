import React, { useEffect } from 'react';

import Navbar from '../../components/Navbar/Navbar'
import PageContent from './components/PageContent';
import Footer from '../../components/Footer/Footer'
import $ from "jquery"




function WelcomePage() {

    useEffect(() => {
        $.ajax({
            url: 'http://localhost:8080/user',
            type: 'get',
            dataType: 'json',
            data: { username: "user1" },
            success: function (dingke) {

                console.log(dingke);
            },
            error: function (request, status, error) {
                console.log(request.responseText + "hi");
            }

        });
    })

    return (
        <div>
            <Navbar />
            <PageContent />
            <Footer />
        </div>
    );
}


export default WelcomePage;
