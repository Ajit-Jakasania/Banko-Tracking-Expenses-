import React, { useEffect } from 'react';

import Navbar from '../../components/Navbar/Navbar'
import PageContent from './components/PageContent';
import Footer from '../../components/Footer/Footer'
import $ from "jquery"




function WelcomePage() {

    useEffect(() => {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/user',
            type: 'get',
            dataType: 'json',
            data: { username: "osama10000" },
            success: function (data) {

                console.log(data);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
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
